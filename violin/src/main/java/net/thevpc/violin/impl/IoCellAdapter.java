package net.thevpc.violin.impl;

import net.thevpc.violin.api.IoCell;
import net.thevpc.violin.model.StoreFieldDefinition;

public abstract class IoCellAdapter extends AbstractIoCell {
    protected StoreFieldDefinition d;
    protected IoCell other;

    public IoCellAdapter(StoreFieldDefinition d, IoCell other) {
        this.d = d;
        this.other = other;
    }

    @Override
    public StoreFieldDefinition getDefinition() {
        return d;
    }

    @Override
    public Object getObject() {
        return other.getObject();
    }

    @Override
    public void close() {
        other.close();
    }

    @Override
    public boolean isLob() {
        return other.isLob();
    }
}
