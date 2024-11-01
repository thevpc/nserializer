package net.thevpc.violin.impl;

import net.thevpc.nuts.util.NMsg;
import net.thevpc.violin.api.StoreProgressMonitor;
import net.thevpc.violin.api.StoreWriter;
import net.thevpc.violin.model.StoreStructId;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class AbstractStoreWriter implements StoreWriter {
    private boolean compress = false;
    private boolean data = true;
    private long maxRows = -1;
    private LinkedHashSet<StoreStructId> structs = new LinkedHashSet<>();
    private StoreProgressMonitorHelper mon = new StoreProgressMonitorHelper();
    private Logger LOG;

    public boolean isCompress() {
        return compress;
    }
    public void addProgressMonitor(StoreProgressMonitor m) {
        mon.addProgressMonitor(m);
    }

    public StoreWriter setCompress(boolean compress) {
        this.compress = compress;
        return this;
    }

    public boolean isData() {
        return data;
    }

    public StoreWriter setData(boolean data) {
        this.data = data;
        return this;
    }

    public long getMaxRows() {
        return maxRows;
    }

    public StoreWriter setMaxRows(long maxRows) {
        this.maxRows = maxRows;
        return this;
    }

    public StoreWriter addStructs(StoreStructId... pred) {
        structs.addAll(pred == null ? new ArrayList<>() :
                Arrays.stream(pred).filter(x -> x != null).collect(Collectors.toList())
        );
        return this;
    }

    @Override
    public StoreWriter addStructs(Collection<StoreStructId> pred) {
        structs.addAll(pred == null ? new ArrayList<>() :
                pred.stream().filter(x -> x != null).collect(Collectors.toList())
        );
        return this;
    }

    protected void incProgress(long[] indexHolder, long max, NMsg message) {
        indexHolder[0]++;
        double progress = indexHolder[0] * 100.0 / max;
        mon.onProgress(progress, message);
        doLog(NMsg.ofC("[%s%s] %s",new DecimalFormat("00.0").format(progress),"%",message));
    }

    protected LinkedHashSet<StoreStructId> getStructs() {
        return structs;
    }

    protected void doLog(NMsg msg){
        if(LOG==null){
            LOG=Logger.getLogger(getClass().getName());
        }
        Level level = msg.getLevel();
        LOG.log(level==null?Level.FINE : level, msg::toString);
        IOLogger.current().log(msg);
    }
}
