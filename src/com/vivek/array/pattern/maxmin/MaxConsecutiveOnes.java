package com.vivek.array.pattern.maxmin;

/**
 * 485. Max Consecutive Ones
 *
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 * Example 1:
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 *
 * Example 2:
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 *
 * Reference:
 * https://leetcode.com/problems/max-consecutive-ones/description/
 */
public class MaxConsecutiveOnes {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 0, 1, 1, 1};
        int[] nums2 = {1, 0, 1, 1, 0, 1};

        System.out.println(findMaxConsecutiveOnes(nums1)); // 3
        System.out.println(findMaxConsecutiveOnes(nums2)); // 2
    }

    static int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = Integer.MIN_VALUE;
        int currentCount = 0;

        for (int num : nums) {
            if (num == 1) {
                currentCount++;
            } else {
                maxCount = Math.max(maxCount, currentCount);
                currentCount = 0;
            }
        }
        return Math.max(maxCount, currentCount);
    }

}
