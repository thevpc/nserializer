package net.thevpc.violin.api;

import net.thevpc.violin.model.StoreStructId;

import java.io.Closeable;
import java.util.Collection;

public interface StoreWriter extends Closeable {


    void addProgressMonitor(StoreProgressMonitor m);

    StoreWriter write();

    StoreWriter flush();

    StoreWriter setData(boolean data);

    StoreWriter setMaxRows(long maxRows);

    StoreWriter setCompress(boolean compress);

    StoreWriter addStructs(StoreStructId... pred);
    StoreWriter addStructs(Collection<StoreStructId> pred);

    void close() ;
}
