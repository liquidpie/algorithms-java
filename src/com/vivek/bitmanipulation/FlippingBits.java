package com.vivek.bitmanipulation;

import java.util.Scanner;

/**
 * Input is 32-bit unsigned integer
 */
public class FlippingBits {

    public static void main(String... args) {
        try (Scanner in = new Scanner(System.in)) {
            long n = in.nextLong();
            System.out.println(n ^ (long)(Math.pow(2, 32) - 1));
        }
    }

}
