/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.thevpc.violin.model;

import java.util.Optional;

/**
 *
 * @author vpc
 */
public enum StoreDataTypeBase {
    NULL(0),
    STRING(1),
    BYTE(3),
    BOOLEAN(5),
    SHORT(7),
    INT(9),
    LONG(11),
    FLOAT(13),
    DOUBLE(15),
    BYTES(17),
    BIG_INT(19),
    BIG_DECIMAL(21),
    DATE(23),
    TIME(25),
    TIMESTAMP(27),
    BYTE_STREAM(29),
    CHAR_STREAM(31),
    DOCUMENT(33),
    JAVA_OBJECT(-126),
    ;
    private int id;

    public int id() {
        return id;
    }

    private StoreDataTypeBase(int id) {
        this.id = id;
    }

    public static Optional<StoreDataTypeBase> ofId(int id) {
        for (StoreDataTypeBase value : StoreDataTypeBase.values()) {
            if (value.id() == id) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

}
