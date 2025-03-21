package net.thevpc.lib.nserializer.api;

public interface StoreRowFilter {
    StoreRowAction accept(IoRow row, long index);
}
