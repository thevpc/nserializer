package net.thevpc.lib.nserializer.api;

import net.thevpc.nuts.text.NMsg;

public interface StoreProgressMonitor {
    void onProgress(double progress, NMsg message);
}
