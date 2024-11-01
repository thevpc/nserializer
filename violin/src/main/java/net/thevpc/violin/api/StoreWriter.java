package net.thevpc.violin.api;

import net.thevpc.nuts.util.NMsg;
import net.thevpc.violin.model.StoreStructId;

import java.io.Closeable;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
