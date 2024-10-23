package net.thevpc.violin.api;

public interface ObjectWriter<T> {
    void write(T value, StoreOutputStream dos);
}
