package net.thevpc.lib.nserializer.impl;

import net.thevpc.lib.nserializer.api.IoRow;
import net.thevpc.lib.nserializer.api.StoreRowAction;
import net.thevpc.lib.nserializer.api.StoreRowFilter;
import net.thevpc.lib.nserializer.api.StoreRows;
import net.thevpc.lib.nserializer.model.StoreStructDefinition;

public class StoreRowsFilter extends AbstractStoreRows {
    private StoreRows item;
    private StoreRowFilter filter;
    private long currIndex;
    private boolean stopped;

    public StoreRowsFilter(StoreRows item, StoreRowFilter filter) {
        this.item = item;
        this.filter = filter;
    }

    @Override
    public StoreStructDefinition getDefinition() {
        return item.getDefinition();
    }

    @Override
    public IoRow nextRow() {
        if (stopped) {
            return null;
        }
        while (true) {
            IoRow u = item.nextRow();
            if (u == null) {
                return null;
            }
            currIndex++;
            if (filter == null) {
                return u;
            }
            StoreRowAction z = filter.accept(u, currIndex - 1);
            switch (z) {
                case ACCEPT: {
                    return u;
                }
                case SKIP: {
                    break;
                }
                case STOP: {
                    return null;
                }
                case STOP_AFTER: {
                    stopped = true;
                    return u;
                }
                default: {
                    throw new IllegalArgumentException("unsupported");
                }
            }
        }
    }

    @Override
    public void close() {
        item.close();
    }
}
