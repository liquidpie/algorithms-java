package com.vivek.recursion;

/**
 * Pow(x, n)
 *
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * Example 1:
 *
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 * Approach:
 *
 * There are different ways to solve this problem, but one of the most efficient ones is to use binary exponentiation.
 * This is a technique that reduces the number of multiplications by using the fact that x^n = (x(n/2))2 if n is even,
 * and x^n = x * (x^(n-1)) if n is odd. This way, you can recursively compute the power in O(log n) time instead of O(n) time.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 *
 * Solution: https://leetcode.com/problems/powx-n/solutions/1337794/java-c-simple-o-log-n-easy-faster-than-100-explained
 *
 * https://leetcode.com/problems/powx-n
 */
public class Power {

    public static void main(String[] args) {
        System.out.println(myPow(2.0, 10));
    }

    public static double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 1 / myPow(x, -n);
        if (n % 2 == 1)
            return x * myPow(x, n - 1);
        else
            return myPow(x * x, n / 2);
    }

    public double myPowIterative(double x, int n) {
        if (n < 0){
            n = -n;
            x = 1 / x;
        }

        double pow = 1;

        while (n != 0){
            if ((n & 1) != 0){
                pow *= x;
            }
            x *= x;
            n >>>= 1;
        }
        return pow;
    }

}
