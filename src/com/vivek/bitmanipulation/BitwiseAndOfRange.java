package com.vivek.bitmanipulation;

/**
 *
 * Bitwise AND of Numbers Range
 *
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
 *
 * Example 1:
 *
 * Input: left = 5, right = 7
 * Output: 4
 *
 * Example 2:
 *
 * Input: left = 0, right = 0
 * Output: 0
 *
 * Example 3:
 *
 * Input: left = 1, right = 2147483647
 * Output: 0
 *
 * An efficient solution is to follow following steps.
 * 1) Find position of Most Significant Bit (MSB) in both numbers.
 * 2) If positions of MSB are different, then result is 0.
 * 3) If positions are same. Let positions be msb_p.
 * ……a) We add 2^msb_p to result.
 * ……b) We subtract 2^msb_p from x and y,
 * ……c) Repeat steps 1, 2 and 3 for new values of x and y.
 */
public class BitwiseAndOfRange {

    static int bitwiseAnd(int left, int right) {
        int result = 0;
        while (left > 0 && right > 0) {
            int msbL = msbPosition(left);
            int msbR = msbPosition(right);

            if (msbL != msbR)
                break;

            int val = 1 << msbL;
            result += val;

            left = left - val;
            right = right - val;

        }
        return result;
    }

    private static int msbPosition(int n) {
        int msb = -1;
        while (n > 0) {
            msb++;
            n = n >> 1;
        }
        return msb;
    }

    public static void main(String[] args) {
        System.out.println(bitwiseAnd(5, 7));
    }
}
