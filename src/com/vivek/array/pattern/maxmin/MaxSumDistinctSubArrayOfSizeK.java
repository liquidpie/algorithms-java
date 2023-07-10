package com.vivek.array.pattern.maxmin;

import java.util.HashMap;
import java.util.Map;

/**
 * Maximum Sum of Distinct Subarrays With Length K
 *
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
 *
 * The length of the subarray is k, and
 * All the elements of the subarray are distinct.
 * Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *
 * Input: nums = [1,5,4,2,9,9,9], k = 3
 * Output: 15
 * Explanation: The subarrays of nums with length 3 are:
 * - [1,5,4] which meets the requirements and has a sum of 10.
 * - [5,4,2] which meets the requirements and has a sum of 11.
 * - [4,2,9] which meets the requirements and has a sum of 15.
 * - [2,9,9] which does not meet the requirements because the element 9 is repeated.
 * - [9,9,9] which does not meet the requirements because the element 9 is repeated.
 * We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
 *
 * Example 2:
 *
 * Input: nums = [4,4,4], k = 3
 * Output: 0
 * Explanation: The subarrays of nums with length 3 are:
 * - [4,4,4] which does not meet the requirements because the element 4 is repeated.
 * We return 0 because no subarrays meet the conditions.
 *
 * https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
 */
public class MaxSumDistinctSubArrayOfSizeK {

    public static void main(String[] args) {
        int[] nums = {9,9,9,1,2,3};
        int k = 3;
        System.out.println(maximumSubarraySum(nums, k));
    }

    static long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> set = new HashMap<>();
        long sum = 0;
        long max = sum;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
            set.put(nums[i], set.getOrDefault(nums[i], 0) + 1);
        }

        if (set.size() == k)
            max = sum;

        for (int i = k; i < nums.length; i++) {
            sum = sum + nums[i] - nums[i - k];
            if (set.get(nums[i - k]) == 1)
                set.remove(nums[i - k]);
            else
                set.put(nums[i - k], set.get(nums[i - k]) - 1);
            set.put(nums[i], set.getOrDefault(nums[i], 0) + 1);

            if (set.size() == k)
                max = Math.max(max, sum);
        }

        return max;
    }

}
