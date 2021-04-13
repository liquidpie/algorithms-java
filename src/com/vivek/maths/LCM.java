package com.vivek.maths;

/**
 * LCM (Least Common Multiple) of two numbers is the smallest number which can be divided by both numbers.
 *
 * For example, LCM of 15 and 20 is 60, and LCM of 5 and 7 is 35.
 *
 * Example:
 * 15 = 5 * 3
 * 25 = 5 * 5
 * LCM = 5 * 5 * 3
 *     = 75
 *
 * A simple solution is to find all prime factors of both numbers, then find union of all factors present in both numbers.
 * Finally, return the product of elements in union.
 *
 * An efficient solution is based on the below formula for LCM of two numbers ‘a’ and ‘b’.
 *
 *    a x b = LCM(a, b) * GCD (a, b)
 *    LCM(a, b) = (a x b) / GCD(a, b)
 *
 * Reference:
 * https://www.geeksforgeeks.org/program-to-find-lcm-of-two-numbers/
 */
public class LCM {

    static int lcm(int a, int b) {

        return (a * b) / gcd(a, b);

    }

    private static int gcd(int a, int b) {
        if (a == 0)
            return b;

        return gcd(b % a, a);
    }

    public static void main(String[] args) {
        int a = 15, b = 20;
        System.out.println("LCM of " + a + " and " + b + " is " + lcm(a, b));
    }

}
