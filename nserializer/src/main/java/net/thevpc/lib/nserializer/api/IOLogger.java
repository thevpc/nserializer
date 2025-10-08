package net.thevpc.lib.nserializer.api;

import net.thevpc.lib.nserializer.impl.IOLoggerNodes;
import net.thevpc.nuts.text.NMsg;

public interface IOLogger {
    IOLogger NOP = new IOLogger() {
        @Override
        public void log(NMsg msg) {
        }
    };

    void log(NMsg msg);

    static IOLoggerNode get(){
        return IOLoggerNodes.get();
    }
}
