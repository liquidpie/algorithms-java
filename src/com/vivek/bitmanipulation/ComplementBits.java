package com.vivek.bitmanipulation;

/**
 * Complement all the bits of given number including sign bit
 */
public class ComplementBits {

    public static void main(String[] args) {
        int n = 2;

        System.out.println("Input:  " + Integer.toBinaryString(n));

        System.out.println("Output: " + Integer.toBinaryString(~n));

    }

}
