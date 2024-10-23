package net.thevpc.violin.api;

import net.thevpc.violin.model.StoreStructDefinition;
import net.thevpc.violin.model.StoreStructId;

public interface StoreWriterModel {
    StoreStructDefinition getDefinition(StoreStructId id);
    StoreRows getRows(StoreStructId id);

}
