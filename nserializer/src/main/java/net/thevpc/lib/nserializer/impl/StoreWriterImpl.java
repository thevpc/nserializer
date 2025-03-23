/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.thevpc.lib.nserializer.impl;

import net.thevpc.lib.nserializer.api.StoreOutputStream;
import net.thevpc.lib.nserializer.api.StoreRows;
import net.thevpc.lib.nserializer.api.StoreWriter;
import net.thevpc.lib.nserializer.api.StoreWriterModel;
import net.thevpc.nuts.util.NMsg;
import net.thevpc.lib.nserializer.model.StoreStructDefinition;
import net.thevpc.lib.nserializer.model.StoreStructId;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.zip.GZIPOutputStream;

/**
 * @author vpc
 */
public class StoreWriterImpl extends AbstractStoreWriter {

    private StoreOutputStream dos;
    private Sers sers;
    private StoreWriterModel db;
    private boolean closeOut = false;
    private OutputStream out0;
    private Supplier<NMsg> logger;

    public StoreWriterImpl(OutputStream out, StoreWriterModel db, long version) {
        this.db = db;
        this.out0 = out;
        this.sers = StoreReaderConf.get(version);
    }


    public StoreWriterImpl(File out, StoreWriterModel db, long version) {
        this.closeOut = true;
        this.db = db;
        this.sers = StoreReaderConf.get(version);
        try {
            doLog(NMsg.ofC("writing to %s ...", out).asFine());
            this.out0 = new FileOutputStream(out);
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
    }


    private void writeHeader() {
        try {
            boolean compress = isCompress();
            doLog(NMsg.ofC("write header (compress=%s)",compress).asFine());
            StoreOutputStream hos = new StoreOutputStreamImpl(out0, sers);
            hos.writeNonNullableLong(NSerializerProtocol.BURST);
            hos.writeNonNullableLong(NSerializerProtocol.V1);
            hos.writeNonNullableLong(System.currentTimeMillis());
            hos.writeNonNullableBoolean(compress);
            if (compress) {
                hos.writeUTF("gzip");
            }
            hos.flush();
            if (compress) {
                this.dos = new StoreOutputStreamImpl(new GZIPOutputStream(out0), sers);
            } else {
                this.dos = new StoreOutputStreamImpl(out0, sers);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public StoreWriter write() {
        long maxProgress = 0;
        long[] currProgress = {0};
        writeHeader();
        doLog(NMsg.ofC("[SECTION_SCHEMA] write section schema").asFine());
        this.startSection(NSerializerProtocol.SECTION_SCHEMA);
        List<StoreStructDefinition> tablesMd = new ArrayList<>();
        for (StoreStructId table : getStructs()) {
            StoreStructDefinition definition = db.getDefinition(table);
            if (definition == null) {
                throw new IllegalArgumentException("unable to resolve " + table);
            }
            tablesMd.add(definition);
        }
        maxProgress = (isData() ? (1 + tablesMd.size()) : 0) + 3;
        incProgress(currProgress, maxProgress, NMsg.ofC("[SECTION_SCHEMA] write schema for %s tables",tablesMd.size()).asFine());
        for (StoreStructDefinition storeTableDefinition : tablesMd) {
            doLog(NMsg.ofC("[SECTION_SCHEMA] %s (%s columns)", storeTableDefinition.toStoreStructId().getFullName(),storeTableDefinition.getColumns().size()).asFine());
        }
        incProgress(currProgress, maxProgress, NMsg.ofC("Write Definitions"));
        dos.writeNonNullableStruct(StoreStructDefinition[].class, tablesMd.toArray(new StoreStructDefinition[0]));
        if (isData()) {
            incProgress(currProgress, maxProgress, NMsg.ofC("Write Data"));
            for (StoreStructDefinition tableMd : tablesMd) {
                this.startSection(NSerializerProtocol.SECTION_DATA);
                if(getMaxRows()>0) {
                    doLog(NMsg.ofC("[%s] start section data (limit %s)", tableMd.toStoreStructId().getFullName(), getMaxRows()).asFine());
                }else{
                    doLog(NMsg.ofC("[%s] start section data", tableMd.toStoreStructId().getFullName()).asFine());
                }
                try (StoreRows rs = db.getRows(tableMd.toStoreStructId())) {
                    dos.writeNonNullableStruct(StoreRows.class, rs.limit(getMaxRows()));
                }
                incProgress(currProgress, maxProgress, NMsg.ofC("Write Data for %s", tableMd.toStoreStructId().getFullName()));
            }
        }
        this.startSection(NSerializerProtocol.STORE_END);
        this.flush();
        incProgress(currProgress, maxProgress, NMsg.ofC("End"));
        return this;
    }


    private void startSection(int sectionId) {
        dos.writeNonNullableInt(sectionId);
    }

    @Override
    public void close() {
        try {
            this.flush();
            this.dos.close();
            if (closeOut) {
                this.out0.close();
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @Override
    public StoreWriter flush() {
        this.dos.flush();
        return this;
    }
}
