package com.vivek.bitmanipulation;

import java.nio.ByteOrder;

/**
 * Find whether a machine is big endian or little endian.
 */
public class Endianness {

    public static void main(String[] args) {
        if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            System.out.println("BIG_ENDIAN");
        } else {
            System.out.println("LITTLE_ENDIAN");
        }
    }

    /**
     * below code is in C
     */
//    #define BIG_ENDIAN 0
//    #define LITTLE_ENDIAN 1
//
//    int testEndian() {
//        short int value = 0x0001;
//        char *byte = (char *) &value;
//        return (byte[0] ? LITTLE_ENDIAN : BIG_ENDIAN);
//    }

}
