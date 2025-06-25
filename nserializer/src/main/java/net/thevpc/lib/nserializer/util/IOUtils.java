package net.thevpc.lib.nserializer.util;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOUtils {
    public static boolean isLobObject(Object o) {
        if (o != null) {
            if (o instanceof InputStream) {
                return true;
            }
            if (o instanceof Reader) {
                return true;
            }
        }
        return false;
    }
}
