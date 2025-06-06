package com.vivek.array.pattern.twopointer;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * Examples:
 *
 * Input: arr[]   = {2, 0, 2}
 * Output: 2
 *
 * Input: arr[]   = {3, 0, 2, 0, 4}
 * Output: 7
 *
 * Solution:
 * The basic intuition of the problem is as follows:
 *
 * An element of the array can store water if there are higher bars on the left and the right.
 * The amount of water to be stored in every position can be found by finding the heights of bars on the left and right sides.
 * The total amount of water stored is the summation of the water stored in each index.
 *
 *
 *
 * Reference: https://www.geeksforgeeks.org/trapping-rain-water/
 *
 */
public class TrappingRainWater {

    static int maxWater(int[] arr) {
        int n = arr.length;
        int[] lMax = new int[n];
        int[] rMax = new int[n];

        int leftMax = 0;
        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, arr[i]);
            lMax[i] = leftMax;
        }

        int rightMax = 0;
        for (int i = n - 1; i >= 0; i--) {
            rightMax = Math.max(rightMax, arr[i]);
            rMax[i] = rightMax;
        }

        int result = 0;
        for (int i = 0; i <n; i++) {
            int min = Math.min(lMax[i], rMax[i]);
            result += min - arr[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3, 0, 2, 0, 4};

        System.out.println(maxWater(arr));

    }

}
