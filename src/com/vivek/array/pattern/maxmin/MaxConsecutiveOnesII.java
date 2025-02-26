package com.vivek.array.pattern.maxmin;

/**
 * 487. Max Consecutive Ones II
 *
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
 *
 *
 * Example 1:
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation:
 * - If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
 * The max number of consecutive ones is 4.
 *
 * Example 2:
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 * Explanation:
 * - If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
 * The max number of consecutive ones is 4.
 *
 * Reference:
 * https://leetcode.com/problems/max-consecutive-ones-ii/description/
 */
public class MaxConsecutiveOnesII {

    public static void main(String[] args) {
        int[] nums1 = {1, 0, 1, 1, 0};
        int[] nums2 = {1, 0, 1, 1, 0, 1};

        System.out.println(findMaxConsecutiveOnes(nums1)); // 4
        System.out.println(findMaxConsecutiveOnes(nums2)); // 4
    }

    static int findMaxConsecutiveOnes(int[] nums) {
        int zeroIdx = -1;
        int maxCount = Integer.MIN_VALUE;
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                if (zeroIdx != -1)
                    start = zeroIdx + 1;
                zeroIdx = end;
            }
            maxCount = Math.max(maxCount, end - start + 1);
        }
        return maxCount;
    }

}
