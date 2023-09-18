package com.vivek.numbers;

/**
 * Power of Three
 *
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 *
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 *
 * Example 1:
 *
 * Input: n = 27
 * Output: true
 * Explanation: 27 = 33
 *
 * Example 2:
 *
 * Input: n = 0
 * Output: false
 * Explanation: There is no x where 3x = 0.
 *
 * Example 3:
 *
 * Input: n = -1
 * Output: false
 * Explanation: There is no x where 3x = (-1).
 *
 * https://leetcode.com/problems/power-of-three
 *
 * Solution:
 * https://leetcode.com/problems/power-of-three/solutions/77856/1-line-java-solution-without-loop-recursion/?envType=list&envId=xb7kzh6j
 */
public class IsPowerOfThree {

    public static void main(String[] args) {
        {
            int n = 27;
            System.out.println(isPowerOfThree(n));
        }
        {
            int n = 64;
            System.out.println(isPowerOfThree(n));
        }
    }

    static boolean isPowerOfThree(int n) {
        int maxPow = (int) (Math.pow(3, (int) (Math.log(Integer.MAX_VALUE) / Math.log(3))));
        return n > 0 && maxPow % n == 0;
    }

}
