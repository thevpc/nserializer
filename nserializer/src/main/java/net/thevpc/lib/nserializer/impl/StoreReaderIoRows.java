package net.thevpc.lib.nserializer.impl;

import net.thevpc.lib.nserializer.api.IOLogger;
import net.thevpc.nuts.text.NMsg;
import net.thevpc.lib.nserializer.api.IoRow;
import net.thevpc.lib.nserializer.api.StoreInputStream;
import net.thevpc.lib.nserializer.model.StoreStructDefinition;

public class StoreReaderIoRows extends AbstractStoreRows {

    private final StoreStructDefinition md;
    private final StoreInputStream dis;
    private long rowIndex;
    private boolean stopped = false;

    public StoreReaderIoRows(StoreInputStream dis) {
        this.dis = dis;
        this.md = dis.readNonNullableStruct(StoreStructDefinition.class);
    }

    @Override
    public StoreStructDefinition getDefinition() {
        return md;
    }

    @Override
    public IoRow nextRow() {
        if (stopped) {
            return null;
        }
        int b = dis.readNonNullableByte();
        if (b == 0) {
            stopped = true;
            return null;
        }
        rowIndex++;
        IOLogger.get().log(NMsg.ofC("Reading row %s",rowIndex));
        return new StoreReaderIoRow(md, dis);
    }

}
