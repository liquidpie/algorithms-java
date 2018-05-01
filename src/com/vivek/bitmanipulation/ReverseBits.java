package com.vivek.bitmanipulation;

/**
 * Reverse all the bits of given number including sign bit
 */
public class ReverseBits {

    public static void main(String[] args) {
        int n = 2;

        System.out.println("Input:  " + Integer.toBinaryString(n));

        System.out.println("Output: " + Integer.toBinaryString(~n));

    }

}
