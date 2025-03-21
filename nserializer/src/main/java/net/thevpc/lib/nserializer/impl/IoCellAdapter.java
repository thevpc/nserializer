package net.thevpc.lib.nserializer.impl;

import net.thevpc.lib.nserializer.api.IoCell;
import net.thevpc.lib.nserializer.model.StoreFieldDefinition;

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
