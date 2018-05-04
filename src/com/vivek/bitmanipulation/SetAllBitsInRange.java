package com.vivek.bitmanipulation;

/**
 * You are given two 32-bit numbers, N and M, and two bit positions, i and j.
 * Set all bits between i and j in N equal to M
 *
 * EXAMPLE:
 * Input: N = 10000000000, M = 10101, i = 2, j = 6
 * Output: N = 10001010100
 */
public class SetAllBitsInRange {

    /**
     * clearing all bits in N between position i and j, and then ORing to put M in there.
     */
    void setBitsInRange(int n, int m, int i, int j) {

        int left = n;
        int right = n;

        left = left >> (j + 1);
        left = left << (j + 1);

        right = right << (31 - i);
        right = right >> (31 - i);

        n = left | right | m << i;

        System.out.println(Integer.toBinaryString(n));
    }

    /**
     * creating a mask to clear the bits between range
     */
    void setBitsInRangeOther(int n, int m, int i, int j) {

        int max = ~0;

        // 1’s through position j, then 0’s
        int left = max - ((1 << j) - 1);
        // 1’s after position i
        int right = (1 << i) - 1;

        // 1’s, with 0s between i and j
        int mask = left | right;

        // Clear i through j, then put m in there
        n = (n & mask) | m << i;

        System.out.println(Integer.toBinaryString(n));
    }

    public static void main(String[] args) {
        SetAllBitsInRange test = new SetAllBitsInRange();
        test.setBitsInRange(0b10000000000, 0b10101, 2, 6);
        test.setBitsInRangeOther(0b10000000000, 0b10101, 2, 6);
    }

}
