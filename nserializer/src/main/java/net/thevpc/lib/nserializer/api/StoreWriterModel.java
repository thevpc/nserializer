package net.thevpc.lib.nserializer.api;

import net.thevpc.lib.nserializer.model.StoreStructDefinition;
import net.thevpc.lib.nserializer.model.StoreStructId;

public interface StoreWriterModel {
    StoreStructDefinition getDefinition(StoreStructId id);
    StoreRows getRows(StoreStructId id);

}
