package com.vivek.dp;

/**
 * 1567. Maximum Length of Subarray With Positive Product
 *
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 *
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 *
 * Return the maximum length of a subarray with positive product.
 *
 * Example 1:
 *
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 *
 * Example 2:
 *
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 *
 * Example 3:
 *
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 *
 * Approach::
 *
 * Intuition ->
 * At every point, we need to keep a track of both positive and negative product lengths.
 *
 *     - As long as you are seeing just positive numbers, the length represents the max length of the positive product.
 *     - The moment you see a negative number (first or odd numbers of negative), the length from point 1 starts to
 *       represent the max length of the -ve product.
 *     - So basically we are switching the lengths depending on how many negative numbers we have seen so far.
 *       If the number of negatives so far is an odd amount, then the length represents a negative product length.
 *       Otherwise, it represents the length of the positive product.
 *
 * Algorithm ->
 * 1. If we see a 0, we gotta start off things again
 * 2. If we see a positive number :
 *      2.1. Increase length of positive mutilpicative result till now.
 *      2.2. Increase length of negative mutilpicative result till now, unless we had not encountered any negative before.
 * 3. If we see a negative number:
 *      3.1. It's time to swap positive and negative multiplicative results' lengths and do similar task as we did in above case.
 * 4. In each iteration, use the length of positive mutilpicative result to compute answer.
 *
 * elements      :   9    5    8    2    -6    4    -3    0    2    -5    15    10    -7    9    -2
 * positive_len  :   1    2    3    4     0    1     7    0    1     0     1     2     5    6     5
 * negative_len  :   0    0    0    0     5    6     2    0    0     2     3     4     3    4     7
 *
 * https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/solutions/820072/easy-soultion-with-dry-run-java/
 * https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/description/
 */
public class LongestSubarrayWithPositiveProduct {

    public static void main(String[] args) {
        int[] nums = {9, 5, 8, 2, -6, 4, -3, 0, 2, -5, 15, 10, -7, 9, -2};
        System.out.println(getMaxLen(nums));
    }

    static int getMaxLen(int[] nums) {
        int positive = 0;
        int negative = 0;
        int ans = 0;

        for (int num : nums) {
            if (num == 0) {
                positive = 0;
                negative = 0;
            } else if (num > 0) {
                positive++;
                negative = negative == 0 ? 0 : negative + 1;
            } else {
                int val = negative;
                negative = positive + 1;
                positive = val == 0 ? 0 : val + 1;
            }

            ans = Math.max(ans, positive);
        }

        return ans;
    }

}
