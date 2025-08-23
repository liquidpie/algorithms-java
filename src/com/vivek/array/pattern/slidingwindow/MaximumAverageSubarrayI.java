package com.vivek.array.pattern.slidingwindow;

/**
 * 643. Maximum Average Subarray I
 *
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
 * Any answer with a calculation error less than 10-5 will be accepted.
 *
 * Example 1:
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Example 2:
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 * Reference: https://leetcode.com/problems/maximum-average-subarray-i
 *
 */
public class MaximumAverageSubarrayI {

    public static void main(String[] args) {
        MaximumAverageSubarrayI helper = new MaximumAverageSubarrayI();
        int[] nums = {1,12,-5,-6,50,3};
        System.out.println(helper.findMaxAverage(nums, 4));
    }

    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double maxSum = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum / k;
    }

}
