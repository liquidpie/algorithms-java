package com.vivek.maths;

/**
 * GCD (Greatest Common Divisor) or HCF (Highest Common Factor) of two numbers is the largest number that divides both of them.
 *
 * Example:
 * 36 = 2 * 2 * 3 * 3
 * 60 = 2 * 2 * 3 * 5
 * GCD = Multiplication of common factors
 * = 2 * 2 * 3
 * = 12
 *
 * A simple solution is to find all prime factors of both numbers, then find intersection of all factors present in both numbers.
 * Finally return product of elements in the intersection.
 * An efficient solution is to use Euclidean algorithm which is the main algorithm used for this purpose.
 * The idea is, GCD of two numbers doesn’t change if smaller number is subtracted from a bigger number.
 *
 * Basic Euclidean Algorithm for GCD
 * The algorithm is based on the below facts:
 *  - If we subtract a smaller number from a larger (we reduce a larger number), GCD doesn’t change.
 *    So if we keep subtracting repeatedly the larger of two, we end up with GCD.
 *  - Now instead of subtraction, if we divide the smaller number, the algorithm stops when we find remainder 0.
 */
public class GCD {

    /**
     * Euclidean algorithm
     */
    static int gcd(int a, int b) {
        // Everything divides 0
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        // Base case
        if (a == b)
            return a;

        return a > b ? gcd(a - b, b) : gcd(a, b - a);
    }

    static int gcdExtended(int a, int b) {
        if (a == 0)
            return b;

        return gcdExtended(b % a, a);
    }

    /**
     * Time Complexity: O(N * log(M)), where M is the smallest element of the array
     * Auxiliary Space: O(1)
     *
     * https://www.geeksforgeeks.org/gcd-two-array-numbers/
     */
    static int gcdArray(int[] arr) {
        int gcd = arr[0];
        for (int i = 1; i < arr.length; i++) {
            gcd = gcd(gcd, arr[i]);

            if (gcd == 1)
                return 1;
        }
        return gcd;
    }

    public static void main(String[] args) {
        int a = 98, b = 56;
        System.out.println("GCD of " + a +" and " + b + " is " + gcd(a, b));
        System.out.println("GCD of " + a +" and " + b + " is " + gcdExtended(a, b));

        int[] arr = { 2, 4, 6, 8, 16 };
        System.out.println(gcdArray(arr));
    }

}
