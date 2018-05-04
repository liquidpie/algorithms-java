package com.vivek.bitmanipulation;

/**
 * Swap odd and even bits in an integer with as few instructions as possible
 * (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, etc).
 */
public class SwapOddEvenBits {

    static int swapBits(int n) {

        // Mask all odd bits with 10101010 in binary (which is 0xAA),
        // then shift them left to put them in the even bits.
        // Then, perform a similar operation for even bits.
        // This takes a total 5 instructions.
        return ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
    }

}
