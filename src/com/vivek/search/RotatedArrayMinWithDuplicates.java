package com.vivek.search;

/**
 * Find Minimum in Rotated Sorted Array II
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 * Example 1:
 *
 * Input: nums = [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 *
 * Time Complexity: O(log n) -> O(n)
 * Space Complexity: O(1)
 *
 * Solution: https://walkccc.me/LeetCode/problems/0154/
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii
 */
public class RotatedArrayMinWithDuplicates {

    public static void main(String[] args) {
        System.out.println(findMin(new int[] {2,2,2,0,1}));
    }

    static int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;

        while (l < h) {
            int mid = (l + h) / 2;
            if (nums[mid] == nums[h])
                --h;
            else if (nums[mid] < nums[h])
                h = mid;
            else
                l = mid + 1;
        }
        return nums[l];
    }

}
