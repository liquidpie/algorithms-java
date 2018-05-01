package com.vivek.bitmanipulation;

/**
 * Flip the bits of a number excluding the sign bit
 */
public class ModulusDivisionByPowerOfTwo {

    public static void main(String[] args) {
        // Compute modulus division by 1 << s without a division operator
        int n = 34; // numerator
        int s = 3; // shift constant
        // denominator
        int d = 1 << s; // this will be 1, 2, 4, 8, 16, 32 ...

        int result = n & (d - 1);

        System.out.println("Input: " + n);
        System.out.println("Output: " + result);


    }

}
