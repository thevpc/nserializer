package net.thevpc.lib.nserializer.model;

import java.util.Optional;

public interface StoreValue {
    StoreDataType getType();

    Object getValue();

    Optional<Integer> getInt();

    Optional<Short> getShort();

    Optional<Long> getLong();

    Optional<Boolean> getBoolean();

    Optional<String> getString();
}
