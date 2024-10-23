package net.thevpc.violin.impl;

import net.thevpc.violin.api.IoCell;
import net.thevpc.violin.api.IoRow;
import net.thevpc.violin.model.StoreFieldDefinition;
import net.thevpc.violin.model.StoreStructDefinition;
import net.thevpc.violin.model.StoreValue;

import java.util.List;

public class IoRowAdapter implements IoRow {
    private StoreStructDefinition def;
    private IoRow other;
    private IoCell[] cells;

    public IoRowAdapter(StoreStructDefinition def, IoRow other) {
        this.def = def;
        this.other = other;
    }
    @Override
    public IoRow repeatable() {
        return new RepeatableReadIoCellArr(this);
    }

    @Override
    public IoCell[] getColumns() {
        if (cells == null) {
            List<? extends StoreFieldDefinition> columns = def.getColumns();
            cells = new IoCell[columns.size()];
            IoCell[] bb = other.getColumns();
            for (int i = 0; i < cells.length; i++) {
                IoCell b = bb[i];
                cells[i]=new IoCellAdapter(columns.get(i), b) {
                    @Override
                    public StoreValue getValue() {
                        return b.getValue();
                    }
                }.repeatable();
            }
        }
        return cells;
    }

//    private int index;

//    @Override
//    public IoCell nextColumn() {
//        List<? extends StoreFieldDefinition> columns = def.getColumns();
//        if (index < columns.size()) {
//            IoCell b = other.nextColumn();
//            IoCell n = new IoCellAdapter(columns.get(index), b) {
//                @Override
//                public StoreValue getValue() {
//                    return b.getValue();
//                }
//
//            };
//            index++;
//            return n;
//        }
//        return null;
//    }

    @Override
    public StoreStructDefinition getDefinition() {
        return def;
    }


}
