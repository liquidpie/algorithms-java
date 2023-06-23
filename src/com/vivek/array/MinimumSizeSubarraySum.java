package com.vivek.array;

/**
 * Minimum Size Subarray Sum
 *
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray
 *  whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 *
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 * https://leetcode.com/problems/minimum-size-subarray-sum
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int target = 7 ;
        int[] nums = {2, 3, 1, 2, 4, 3};
//        int[] nums = {1,1,1,1,1,1,1,1};
        System.out.println(minSubArrayLen(target, nums));
    }

    static int minSubArrayLen(int target, int[] nums) {
        int subsum = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        for (int end = 0; end < nums.length; end++) {
            subsum += nums[end];
            while (subsum >= target) {
                minLen = Math.min(minLen, end - start + 1);
                subsum -= nums[start++];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

}
