package com.vivek.bitmanipulation;

/**
 * 342. Power of Four
 *
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 *
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 *
 * Example 1:
 *
 * Input: n = 16
 * Output: true
 *
 * Example 2:
 *
 * Input: n = 5
 * Output: false
 *
 * Example 3:
 *
 * Input: n = 1
 * Output: true
 *
 * Solution:
 * To determine if n is a Power of 4, simply check if:
 *
 *     n is Positive integer, and is a Power of Two:
 *
 * n & (n−1)=0​(1)
 *
 * This formula is already well explained in POWER OF TWO solution.
 *
 *     the only difference here, is we need to check if
 *
 * n % 3=1​(2)
 *
 * Notice, we did not expilicitly check for n≤0, that is because,
 *
 *     eq. (1) will ensure negative numbers will be false, and
 *     eq. (2) will ensure n=0 will be false.
 *
 * https://leetcode.com/problems/power-of-four/solutions/7081924/one-simple-trick-power-of-4-with-detaile-nw8u/
 *
 * Reference: https://leetcode.com/problems/power-of-four/
 */
public class PowerOfFour {

    public boolean isPowerOfFour(int n) {
        return (n & (n - 1)) == 0 && n % 3 == 1;
    }

}
