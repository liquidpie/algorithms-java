package com.vivek.bitmanipulation;

/**
 * Created by VJaiswal on 26/09/17.
 */
public class PowerOfTwo {

    public static void main(String[] args) {
        // Determine if an integer is a power of two
        int x = 4;
        boolean isPowerOfTwo = (x & (x - 1)) == 0;

        System.out.println(isPowerOfTwo);
    }

}
