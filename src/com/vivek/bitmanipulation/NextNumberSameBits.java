package com.vivek.bitmanipulation;

/**
 * print the next smallest and next largest number that have the same number of
 * 1 bits in their binary representation
 */
public class NextNumberSameBits {

    private int setBit(int n, int index) {
        return n | 1 << index;
    }

    private int clearBit(int n, int index) {
        int mask = ~(1 << index);
        return n & mask;
    }

    private int getBit(int n, int index) {
        return (n & (1 << index)) >> index;
    }

    /**
     1. Traverse from right to left. Once we’ve passed a 1, turn on the next 0.
        We’ve now increased the number by 2^i. Yikes! Example: xxxxx011100 becomes xxxxx111100
     2. Turn off the one that’s just to the right side of that.
        We’re now bigger by 2^i - 2^(i-1) Example: xxxxx111100 becomes xxxxx101100
     3. Make the number as small as possible by rearranging all the 1s to be as far right as possible:
        Example: xxxxx101100 becomes xxxxx100011
     */
    int getNextNumber(int n) {
        int countOnes = 0;
        int i;
        for (i = 0; i < 31; i++) {
            int bit = getBit(n, i);
            int nextBit = getBit(n, i + 1);
            if (bit == 1)
                countOnes++;
            if (bit == 1 && nextBit == 0)
                break;
        }

        // turn on next 0
        n = setBit(n, i + 1);
        // turn off previous 1
        n = clearBit(n, i);
        countOnes--;

        // set zeros
        for (int j = i - 1; j >= countOnes; j--) {
            n = clearBit(n, j);
        }

        // set ones
        for (int j = countOnes - 1; j >= 0; j--) {
            n = setBit(n, j);
        }

        return n;
    }

    /**
     1. Traverse from right to left. Once we’ve passed a zero, turn off the next 1.
        Example: xxxxx100011 becomes xxxxx000011.
     2. Turn on the 0 that is directly to the right.
        Example: xxxxx000011 becomes xxxxx010011.
     3. Make the number as big as possible by shifting all the ones as far to the left as possible.
        Example: xxxxx010011 becomes xxxxx011100.
     */
    int getPreviousNumber(int n) {
        int countOnes = 0;
        int i;
        for (i = 0; i < 31; i++) {
            int bit = getBit(n, i);
            int nextBit = getBit(n, i + 1);
            if (bit == 1)
                countOnes++;
            if (bit == 0 && nextBit == 1)
                break;
        }

        // turn off next 1
        n = clearBit(n, i + 1);
        // turn on current 0
        n = setBit(n, i);

        // set ones
        for (int j = i - 1; j >= countOnes; j--) {
            n = setBit(n, j);
        }

        // set zeroes
        for (int j = countOnes - 1; j >= 0; j--) {
            n = clearBit(n, j);
        }

        return n;
    }

    public static void main(String[] args) {
        NextNumberSameBits test = new NextNumberSameBits();
        System.out.println(test.getNextNumber(12));
    }
}
