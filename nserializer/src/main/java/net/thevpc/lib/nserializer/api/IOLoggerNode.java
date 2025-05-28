package net.thevpc.lib.nserializer.api;

import java.util.concurrent.Callable;

public interface IOLoggerNode extends IOLogger {
    IOLoggerNode add(IOLogger other);

    void runWith(Runnable run);

    <T> T callWith(Callable<T> run) throws Exception ;
}
