package net.thevpc.lib.nserializer.impl;

import net.thevpc.nuts.util.NMsg;
import net.thevpc.lib.nserializer.api.IoRow;
import net.thevpc.lib.nserializer.model.StoreStructDefinition;

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
            IOLogger.current().log(NMsg.ofC("Reading row %s / %s",(index+1),rows.length));
            IoRow c = new DefaultIoRow(def, rows[index]);
            index++;
            return c;
        }
        return null;
    }

}
