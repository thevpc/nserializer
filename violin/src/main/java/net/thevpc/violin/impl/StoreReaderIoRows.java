package net.thevpc.violin.impl;

import net.thevpc.violin.api.IoRow;
import net.thevpc.violin.api.StoreInputStream;
import net.thevpc.violin.model.StoreStructDefinition;

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
        IOLogger.current().log("Reading row " + rowIndex);
        return new StoreReaderIoRow(md, dis);
    }

}
