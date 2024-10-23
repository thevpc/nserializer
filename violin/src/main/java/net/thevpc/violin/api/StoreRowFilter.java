package net.thevpc.violin.api;

public interface StoreRowFilter {
    StoreRowAction accept(IoRow row, long index);
}
