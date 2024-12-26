/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.thevpc.violin.model;

import net.thevpc.nuts.util.NAssert;

import java.util.Optional;

/**
 * @author vpc
 */
public enum StoreDataType {
    NULL(0, StoreDataTypeBase.NULL, true),
    STRING(1, StoreDataTypeBase.STRING, true),
    NSTRING(2, StoreDataTypeBase.STRING, false),
    BYTE(3, StoreDataTypeBase.BYTE, true),
    NBYTE(4, StoreDataTypeBase.BYTE, false),
    BOOLEAN(5, StoreDataTypeBase.BOOLEAN, true),
    NBOOLEAN(6, StoreDataTypeBase.BOOLEAN, false),
    SHORT(7, StoreDataTypeBase.SHORT, true),
    NSHORT(8, StoreDataTypeBase.SHORT, false),
    INT(9, StoreDataTypeBase.INT, true),
    NINT(10, StoreDataTypeBase.INT, false),
    LONG(11, StoreDataTypeBase.LONG, true),
    NLONG(12, StoreDataTypeBase.LONG, false),
    FLOAT(13, StoreDataTypeBase.FLOAT, true),
    NFLOAT(14, StoreDataTypeBase.FLOAT, false),
    DOUBLE(15, StoreDataTypeBase.DOUBLE, true),
    NDOUBLE(16, StoreDataTypeBase.DOUBLE, false),
    BYTES(17, StoreDataTypeBase.BYTES, true),
    NBYTES(18, StoreDataTypeBase.BYTES, false),
    BIG_INT(19, StoreDataTypeBase.BIG_INT, true),
    NBIG_INT(20, StoreDataTypeBase.BIG_INT, false),
    BIG_DECIMAL(21, StoreDataTypeBase.BIG_DECIMAL, true),
    NBIG_DECIMAL(12, StoreDataTypeBase.BIG_DECIMAL, false),
    DATE(23, StoreDataTypeBase.DATE, true),
    NDATE(24, StoreDataTypeBase.DATE, false),
    TIME(25, StoreDataTypeBase.TIME, true),
    NTIME(26, StoreDataTypeBase.TIME, false),
    TIMESTAMP(27, StoreDataTypeBase.TIMESTAMP, true),
    NTIMESTAMP(28, StoreDataTypeBase.TIMESTAMP, false),
    BYTE_STREAM(29, StoreDataTypeBase.BYTE_STREAM, true),
    NBYTE_STREAM(30, StoreDataTypeBase.BYTE_STREAM, false),
    CHAR_STREAM(31, StoreDataTypeBase.CHAR_STREAM, true),
    NCHAR_STREAM(32, StoreDataTypeBase.CHAR_STREAM, false),
    DOCUMENT(33, StoreDataTypeBase.DOCUMENT, true),
    NDOCUMENT(34, StoreDataTypeBase.DOCUMENT, false),
    //    ARRAY(19),
    JAVA_OBJECT(-126, StoreDataTypeBase.JAVA_OBJECT, true),
    NJAVA_OBJECT(-127, StoreDataTypeBase.JAVA_OBJECT, false),
    ;
    private int id;
    private boolean nullable;
    private StoreDataTypeBase base;

    public StoreDataTypeBase base() {
        return base;
    }

    public int id() {
        return id;
    }

    public static StoreDataType of(StoreDataTypeBase base, boolean nullable) {
        NAssert.requireNonNull(base, "base");
        switch (base) {
            case INT:
                return nullable ? INT : NINT;
            case BIG_DECIMAL:
                return nullable ? BIG_DECIMAL : NBIG_DECIMAL;
            case BIG_INT:
                return nullable ? BIG_INT : NBIG_INT;
            case BOOLEAN:
                return nullable ? BOOLEAN : NBOOLEAN;
            case BYTE:
                return nullable ? BYTE : NBYTE;
            case BYTE_STREAM:
                return nullable ? BYTE_STREAM : NBYTE_STREAM;
            case BYTES:
                return nullable ? BYTES : NBYTES;
            case CHAR_STREAM:
                return nullable ? CHAR_STREAM : NCHAR_STREAM;
            case FLOAT:
                return nullable ? FLOAT : NFLOAT;
            case DOCUMENT:
                return nullable ? DOCUMENT : NDOCUMENT;
            case DOUBLE:
                return nullable ? DOUBLE : NDOUBLE;
            case STRING:
                return nullable ? STRING : NSTRING;
            case SHORT:
                return nullable ? SHORT : NSHORT;
            case JAVA_OBJECT:
                return nullable ? JAVA_OBJECT : NJAVA_OBJECT;
            case DATE:
                return nullable ? DATE : NDATE;
            case TIME:
                return nullable ? TIME : NTIME;
            case LONG:
                return nullable ? LONG : NLONG;
            case TIMESTAMP:
                return nullable ? TIMESTAMP : NTIMESTAMP;
            case NULL:
                return NULL;
        }
        throw new IllegalArgumentException("Unsupported base type: " + base);
    }

    private StoreDataType(int id, StoreDataTypeBase base, boolean nullable) {
        this.id = id;
        this.base = base;
        this.nullable = nullable;
    }

    public StoreDataType toNullable() {
        return of(base(), true);
    }

    public StoreDataType toNonNullable() {
        return of(base(), false);
    }

    public boolean isNullable() {
        return nullable;
    }

    public static Optional<StoreDataType> ofId(int id) {
        for (StoreDataType value : StoreDataType.values()) {
            if (value.id() == id) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

}
