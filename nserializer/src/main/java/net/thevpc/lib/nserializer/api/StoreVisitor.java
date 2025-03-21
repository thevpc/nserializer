package net.thevpc.lib.nserializer.api;

import net.thevpc.lib.nserializer.model.StoreStructDefinition;

import java.util.List;

public interface StoreVisitor {
    void visitSchema(List<StoreStructDefinition> md);

    void visitData(StoreRows md);
    void visitEnd();
}
