package net.thevpc.violin.api;

import net.thevpc.violin.model.StoreStructDefinition;

import java.util.List;

public interface StoreVisitor {
    void visitSchema(List<StoreStructDefinition> md);

    void visitData(StoreRows md);
    void visitEnd();
}
