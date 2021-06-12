package com.vivek.maths;

/**
 * Legendre’s formula (Given p and n, find the largest x such that p^x divides n!)
 *
 * Given an integer n and a prime number p, find the largest x such that px (p raised to power x) divides n! (factorial)
 *
 * Examples:
 *
 * Input:  n = 7, p = 3
 * Output: x = 2
 * 32 divides 7! and 2 is the largest such power of 3.
 *
 * Input:  n = 10, p = 3
 * Output: x = 4
 * 34 divides 10! and 4 is the largest such power of 3.
 *
 * n! is multiplication of {1, 2, 3, 4, …n}.
 * How many numbers in {1, 2, 3, 4, ….. n} are divisible by p?
 * Every p’th number is divisible by p in {1, 2, 3, 4, ….. n}. Therefore in n!, there are ⌊n/p⌋ numbers divisible by p. So we know that the value of x (largest power of p that divides n!) is at-least ⌊n/p⌋.
 * Can x be larger than ⌊n/p⌋ ?
 * Yes, there may be numbers which are divisible by p2, p3, …
 * How many numbers in {1, 2, 3, 4, ….. n} are divisible by p2, p3, …?
 * There are ⌊n/(p2)⌋ numbers divisible by p2 (Every p2‘th number would be divisible). Similarly, there are ⌊n/(p3)⌋ numbers divisible by p3 and so on.
 * What is the largest possible value of x?
 * So the largest possible power is ⌊n/p⌋ + ⌊n/(p2)⌋ + ⌊n/(p3)⌋ + ……
 * Note that we add only ⌊n/(p2)⌋ only once (not twice) as one p is already considered by expression ⌊n/p⌋. Similarly, we consider ⌊n/(p3)⌋ (not thrice).
 *
 * Reference: https://www.geeksforgeeks.org/legendres-formula-highest-power-of-prime-number-that-divides-n
 */
public class LegendreFormula {

    static int largestPower(int n, int p) {
        int ans = 0;

        while (n > 0) {
            n /= p;
            ans += n;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(largestPower(10, 3));
    }

}
