package net.thevpc.lib.nserializer.impl;

import net.thevpc.lib.nserializer.model.StoreFieldDefinition;

public class ConstantIoCell extends AbstractIoCell {
    int i;
    StoreFieldDefinition d;
    Object val;
    boolean lob;

    public ConstantIoCell(StoreFieldDefinition d, Object val,boolean lob) {
        this.d = d;
        this.val = val;
        this.lob = lob;
    }

    @Override
    public boolean isLob() {
        return lob;
    }

    @Override
    public StoreFieldDefinition getDefinition() {
        return d;
    }

    @Override
    public Object getObject() {
        return val;
    }

}
