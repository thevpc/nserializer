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

//    public static void copy(InputStream in, Path file) {
//        Path p = file.getParent();
//        if (p != null) {
//            p.toFile().mkdirs();
//        }
//        try (OutputStream out = Files.newOutputStream(file)) {
//            copy(in, out);
//        } catch (IOException ex) {
//            throw new UncheckedIOException(ex);
//        }
//    }
//
//    public static void copy(Reader in, Path file) {
//        Path p = file.getParent();
//        if (p != null) {
//            p.toFile().mkdirs();
//        }
//        try (Writer out = Files.newBufferedWriter(file)) {
//            copy(in, out);
//        } catch (IOException ex) {
//            throw new UncheckedIOException(ex);
//        }
//    }

//    public static void copy(InputStream in, OutputStream out) {
//        byte[] buffer = new byte[4096 * 2];
//        int c = 0;
//        try {
//            while ((c = in.read(buffer)) > 0) {
//                out.write(buffer, 0, c);
//            }
//        } catch (IOException ex) {
//            throw new UncheckedIOException(ex);
//        }
//    }

//    public static void copy(Reader in, Writer out) {
//        char[] buffer = new char[4096 * 2];
//        int c = 0;
//        try {
//            while ((c = in.read(buffer)) > 0) {
//                out.write(buffer, 0, c);
//            }
//        } catch (IOException ex) {
//            throw new UncheckedIOException(ex);
//        }
//    }
//
//    public static byte[] readyFully(URL url) {
//        try(InputStream in=url.openStream()){
//            return readyFully(in);
//        }catch (IOException ex){
//            throw new UncheckedIOException(ex);
//        }
//    }
//
//    public static byte[] readyFully(InputStream in) {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        copy(in, bos);
//        return bos.toByteArray();
//    }

}
