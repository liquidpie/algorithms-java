package com.vivek.array;

/**
 * Find the Duplicate Number
 *
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Follow up:
 *
 * - How can we prove that at least one duplicate number must exist in nums?
 * - Can you solve the problem in linear runtime complexity?
 *
 * Solution:
 *
 * Marking visited value within the array
 * Since all values of the array are between [1…n] and the array size is n+1, while scanning the array from left to right, we set the nums[n] to its negative value.
 *
 * With extra O(1) space, with modifying the input.
 *
 *     // Visited
 *     public static int findDuplicate_mark(int[] nums) {
 *         int len = nums.length;
 *         for (int num : nums) {
 *             int idx = Math.abs(num);
 *             if (nums[idx] < 0) {
 *                 return idx;
 *             }
 *             nums[idx] = -nums[idx];
 *         }
 *
 *         return len;
 *     }
 * Analysis
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * https://leetcode.com/problems/find-the-duplicate-number
 */
public class DuplicateNumber {

    public static void main(String[] args) {
        {
            int[] nums = {3,1,3,4,2};
            System.out.println(findDuplicate(nums));
        }
        {
            int[] nums = {1,1,2};
            System.out.println(findDuplicate(nums));
        }
    }

    static int findDuplicate(int[] nums) {
        for (int num : nums) {
            int idx = Math.abs(num);
            if (nums[idx] < 0) {
                return idx;
            }
            nums[idx] = -nums[idx];
        }
        return nums.length;
    }

}
