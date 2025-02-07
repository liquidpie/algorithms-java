package com.vivek.maths;

/**
 * 69. Sqrt(x)
 *
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
 *
 * You must not use any built-in exponent function or operator.
 *
 *     For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 *
 * Example 1:
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2, so we return 2.
 *
 * Example 2:
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
 *
 * Approach 2: Binary Search
 *
 * Let's go back to the interview context. For x≥2 the square root is always smaller than x/2 and larger than 0 : 0<a<x/2.
 * Since a is an integer, the problem goes down to the iteration over the sorted set of integer numbers. Here the binary search enters the scene.
 *
 * Algorithm
 *
 *  - If x < 2, return x.
 *  - Set the left boundary to left = 2, and the right boundary to right = x / 2.
 *  - While left <= right:
 *      - Take num = (left + right) / 2 as a guess. Compute num * num and compare it with x:
 *          - If num * num > x, move the right boundary ``right = pivot - 1`
 *          - Else, if num * num < x, move the left boundary left = pivot + 1
 *          - Otherwise num * num == x, the integer square root is here, let's return it.
 *  - Return right
 *
 * Reference:
 * https://leetcode.com/problems/sqrtx/description/
 */
public class SquareRoot {

    public static void main(String[] args) {
        System.out.println(sqrt(4));
        System.out.println(sqrt(8));
    }

    static int sqrt(int x) {
        if (x < 2)
            return x;

        int left = 2;
        int right = x / 2;
        while (left <= right) {
            int pivot = (left + right) / 2;
            long sqr = (long) pivot * pivot;
            if (sqr > x) {
                right = pivot - 1;
            } else if (sqr < x) {
                left = pivot + 1;
            } else {
                return pivot;
            }
        }
        return right;
    }

}
