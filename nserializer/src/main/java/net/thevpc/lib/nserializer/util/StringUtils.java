/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.thevpc.lib.nserializer.util;

import java.io.InputStream;
import java.io.Reader;
import java.util.regex.Pattern;

/**
 * @author vpc
 */
public class StringUtils {
    /**
     * //FROM JDK!!! ObjectOutputStream
     *
     * @param s
     * @return
     */
    public static long getUTFLength(String s) {
        int CHAR_BUF_SIZE = 256;
        char[] cbuf = new char[CHAR_BUF_SIZE];
        int len = s.length();
        long utflen = 0;
        for (int off = 0; off < len; ) {
            int csize = Math.min(len - off, CHAR_BUF_SIZE);
            s.getChars(off, off + csize, cbuf, 0);
            for (int cpos = 0; cpos < csize; cpos++) {
                char c = cbuf[cpos];
                if (c >= 0x0001 && c <= 0x007F) {
                    utflen++;
                } else if (c > 0x07FF) {
                    utflen += 3;
                } else {
                    utflen += 2;
                }
            }
            off += csize;
        }
        return utflen;
    }
    public static String litString(Object s) {
        if (s == null) {
            return "null";
        }
        if (
                s instanceof Number
                        || s instanceof Boolean
        ) {
            return String.valueOf(s);
        }
        if (
                s instanceof Reader
                        || s instanceof char[]
        ) {
            return "char[...]";
        }
        if (
                s instanceof InputStream
                        || s instanceof byte[]
        ) {
            return "byte[...]";
        }
        if (s instanceof String) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toString().toCharArray()) {
                switch (c) {
                    case '\\':
                    case '\"': {
                        sb.append('\\').append(c);
                        break;
                    }
                    case '\n': {
                        sb.append('\\').append('n');
                        break;
                    }
                    case '\r': {
                        sb.append('\\').append('r');
                        break;
                    }
                    default: {
                        sb.append(c);
                    }
                }
            }
            return "\"" + sb.toString() + "\"";
        }
        return "\"" + s + "\"";
    }
}
