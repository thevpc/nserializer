package net.thevpc.violin.impl;

import net.thevpc.violin.api.IoRow;
import net.thevpc.violin.model.StoreStructDefinition;

public class DefaultStoreRows extends AbstractStoreRows {
    private StoreStructDefinition def;
    private Object[][] rows;
    private int index;

    public DefaultStoreRows(StoreStructDefinition def, Object[][] rows) {
        this.def = def;
        this.rows = rows;
    }

    @Override
    public StoreStructDefinition getDefinition() {
        return def;
    }

    @Override
    public IoRow nextRow() {
        if (index < rows.length) {
            IOLogger.current().log("Reading row "+(index+1)+" / "+rows.length);
            IoRow c = new DefaultIoRow(def, rows[index]);
            index++;
            return c;
        }
        return null;
    }

}
