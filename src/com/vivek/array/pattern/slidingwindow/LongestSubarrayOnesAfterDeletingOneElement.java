package com.vivek.array.pattern.slidingwindow;

/**
 * 1493. Longest Subarray of 1's After Deleting One Element
 *
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 *
 * Example 2:
 *
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 *
 * Example 3:
 *
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 *
 * Reference:
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element
 */
public class LongestSubarrayOnesAfterDeletingOneElement {

    public static void main(String[] args) {
        LongestSubarrayOnesAfterDeletingOneElement helper = new LongestSubarrayOnesAfterDeletingOneElement();
        int[] nums = {0,1,1,1,0,1,1,0,1};
        System.out.println(helper.longestSubarray(nums));
    }

    public int longestSubarray(int[] nums) {
        int zeroIdx = -1;
        int windowStart = 0;
        int max = 0;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            if (nums[windowEnd] == 0 && zeroIdx == -1) {
                zeroIdx = windowEnd;
            } else if (nums[windowEnd] == 0 && zeroIdx != -1) {
                windowStart = zeroIdx + 1;
                zeroIdx = windowEnd;
            }

            max = Math.max(max, windowEnd - windowStart);
        }
        return max;
    }

}
