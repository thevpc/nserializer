package net.thevpc.violin.impl;

import net.thevpc.violin.api.StoreInputStream;
import net.thevpc.violin.model.StoreDataType;
import net.thevpc.violin.model.StoreFieldDefinition;
import net.thevpc.violin.util.IOUtils;

import java.io.IOException;
import java.io.UncheckedIOException;

public class StoreReaderIoCell extends AbstractIoCell {

    private final StoreFieldDefinition column;
    private final StoreInputStream dis;
    private Object obj;
    private boolean objRead;

    public StoreReaderIoCell(StoreFieldDefinition column, StoreInputStream dis) {
        this.column = column;
        this.dis = dis;
    }

    @Override
    public StoreFieldDefinition getDefinition() {
        return column;
    }

    @Override
    public boolean isLob() {
        return IOUtils.isLobObject(getObject());
    }

    @Override
    public Object getObject() {
        if (!objRead) {
            try {
                objRead = true;
                StoreDataType fType = column.getStoreType();
                return obj = dis.readAnyExpected(fType).getValue();
            } catch (UncheckedIOException e) {
                throw new UncheckedIOException(new IOException(
                        "unable to read column " + column.getFullName()
                        , e.getCause()
                ));
            } catch (Exception e) {
                throw new UncheckedIOException(new IOException(
                        "unable to read column " + column.getFullName(), e
                ));
            }
        }
        return obj;
    }


}
