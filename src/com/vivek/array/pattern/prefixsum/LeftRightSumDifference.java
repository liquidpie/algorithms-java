package com.vivek.array.pattern.prefixsum;

/**
 * 2574. Left and Right Sum Differences
 *
 * You are given a 0-indexed integer array nums of size n.
 *
 * Define two arrays leftSum and rightSum where:
 *
 *     leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element, leftSum[i] = 0.
 *     rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element, rightSum[i] = 0.
 *
 * Return an integer array answer of size n where answer[i] = |leftSum[i] - rightSum[i]|.
 *
 * Example 1:
 *
 * Input: nums = [10,4,8,3]
 * Output: [15,1,11,22]
 * Explanation: The array leftSum is [0,10,14,22] and the array rightSum is [15,11,3,0].
 * The array answer is [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].
 *
 * Example 2:
 *
 * Input: nums = [1]
 * Output: [0]
 * Explanation: The array leftSum is [0] and the array rightSum is [0].
 * The array answer is [|0 - 0|] = [0].
 *
 * Solution:
 * First evaluate rsum by taking sum of all elements
 * then while iterating, keeep adding new element to lsum and subtracting it from the rsum.
 *
 * Reference: https://leetcode.com/problems/left-and-right-sum-differences/description/
 */
public class LeftRightSumDifference {

    public int[] leftRightDifference(int[] nums) {
        int rightSum = 0;
        int leftSum = 0;
        for (int num : nums)
            rightSum += num;

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rightSum -= nums[i];
            result[i] = Math.abs(rightSum - leftSum);
            leftSum += nums[i];
        }

        return result;
    }

}
