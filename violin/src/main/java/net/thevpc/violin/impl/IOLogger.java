package net.thevpc.violin.impl;

import net.thevpc.nuts.util.NMsg;

public interface IOLogger {
    IOLogger NOP = new IOLogger() {
        @Override
        public void log(NMsg msg) {
        }
    };

    static void addLogger(IOLogger lo) {
        IOLoggerHelper.get().add(lo);
    }

    static void removeLogger(IOLogger lo) {
        IOLoggerHelper.get().remove(lo);
    }

    static void runWith(IOLogger lo, Runnable r) {
        IOLoggerHelper o = IOLoggerHelper.get();
        try {
            IOLoggerHelper.put(lo);
            r.run();
        } finally {
            IOLoggerHelper.put(o);
        }
    }

    void log(NMsg msg);

    static IOLogger current() {
        return IOLoggerHelper.get().current();
    }
}
