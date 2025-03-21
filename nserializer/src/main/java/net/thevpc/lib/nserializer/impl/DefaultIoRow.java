package net.thevpc.lib.nserializer.impl;

import net.thevpc.lib.nserializer.api.IoCell;
import net.thevpc.lib.nserializer.api.IoRow;
import net.thevpc.lib.nserializer.model.StoreFieldDefinition;
import net.thevpc.lib.nserializer.model.StoreStructDefinition;
import net.thevpc.lib.nserializer.util.IOUtils;

import java.util.List;

public class DefaultIoRow implements IoRow {
    private StoreStructDefinition def;
    private Object[] values;
    private IoCell[] cells;

    public DefaultIoRow(StoreStructDefinition def, Object[] values) {
        this.def = def;
        this.values = values;
    }
    @Override
    public IoRow repeatable() {
        return new RepeatableReadIoCellArr(this);
    }

    public IoCell[] getColumns(){
        if(cells==null){
            List<? extends StoreFieldDefinition> columns = def.getColumns();
            cells=new IoCell[columns.size()];
            for (int i = 0; i <cells.length; i++) {
                cells[i]=new ConstantIoCell(
                        columns.get(i),
                        values[i],
                        IOUtils.isLobObject(values[i])
                ).repeatable();
            }
        }
        return cells;
    }


//    private int index;
//    @Override
//    public IoCell nextColumn() {
//        List<? extends StoreFieldDefinition> columns = def.getColumns();
//        if (index < columns.size()) {
//            ConstantIoCell y = new ConstantIoCell(
//                    columns.get(index),
//                    values[index]
//            );
//            index++;
//            return y;
//        }
//        return null;
//    }

    @Override
    public StoreStructDefinition getDefinition() {
        return def;
    }

}
