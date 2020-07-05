package com.vivek.bitmanipulation;

/**
 * Reverse all the bits
 * 1100101 -> 1010011
 */
public class ReverseBits {

    public static int reverse(int n) {
        int copy = n;
        int i = 0;
        int result = 0;

        boolean isSigned = ((copy >> 31) & 1) == 1;

        while (i < 31) {
            result |= (n & 1);
            n >>= 1;
            result <<= 1;
            i++;
        }
        if (isSigned) {
            result |= 1;
        }

        return result;
    }

    public static void main(String[] args) {
        int input = 232324343;

        System.out.println("Input:  " + Integer.toBinaryString(input));

        int reversed = reverse(input);

        System.out.println("Output: " + Integer.toBinaryString(reversed));

    }

}
