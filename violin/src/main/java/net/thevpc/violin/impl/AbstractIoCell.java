package net.thevpc.violin.impl;

import net.thevpc.violin.api.IoCell;

import java.io.File;
import java.nio.file.Path;

public abstract class AbstractIoCell implements IoCell {
    @Override
    public void close() {

    }

    @Override
    public IoCell repeatable() {
        return new RepeatableReadIoCell(this);
    }

    public void writeLob(File file) {
        throw new IllegalArgumentException("not supported yet");
    }

    @Override
    public void writeLob(Path file) {
        throw new IllegalArgumentException("not supported yet");
    }


}
