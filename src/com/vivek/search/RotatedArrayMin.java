package com.vivek.search;

/**
 * Find Minimum in Rotated Sorted Array
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 *
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 *
 * Approach:
 *
 * Another way to phrase this problem is “find the smallest value in the array”. This is because the smallest value in the array will always be the point at which the rotation starts, and thus the answer.
 * Finding “minimum”, “maximum”, “target” can be done in O(n).
 * If the array is rotated the left side will exclusively be larger than the right side.
 * The problem ask for a solution in O(log n) which means we’ll need to use an algorithm like binary search.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 *
 * Solution: https://walkccc.me/LeetCode/problems/0153/
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
 */
public class RotatedArrayMin {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(findMin(new int[]{11,13,15,17}));
        System.out.println(findMin(new int[]{1}));
        System.out.println(findMin(new int[]{2,3,4,5,1}));
    }

    public static int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;

        while (l < h) {
            int mid = (l + h) / 2;
            if (nums[mid] < nums[h])
                h = mid;
            else
                l = mid + 1;
        }

        return nums[l];
    }

}
