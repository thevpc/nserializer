package net.thevpc.lib.nserializer.api;

public interface ObjectWriter<T> {
    void write(T value, StoreOutputStream dos);
}
