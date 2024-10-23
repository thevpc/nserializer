package net.thevpc.violin.api;

public interface ObjectReader<T> {
    T read(StoreInputStream dos);
}
