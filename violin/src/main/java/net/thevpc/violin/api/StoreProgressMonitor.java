package net.thevpc.violin.api;

public interface StoreProgressMonitor {
    void onProgress(double progress, String message);
}
