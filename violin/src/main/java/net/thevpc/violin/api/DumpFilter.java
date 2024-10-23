package net.thevpc.violin.api;

import net.thevpc.violin.model.StoreStructHeader;

public interface DumpFilter {
    boolean acceptTableHeader(StoreStructHeader h);
}
