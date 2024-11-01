package net.thevpc.violin.api;

import net.thevpc.nuts.util.NMsg;

public interface StoreProgressMonitor {
    void onProgress(double progress, NMsg message);
}
