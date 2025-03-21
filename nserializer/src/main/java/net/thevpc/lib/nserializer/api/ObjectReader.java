package net.thevpc.lib.nserializer.api;

public interface ObjectReader<T> {
    T read(StoreInputStream dos);
}
