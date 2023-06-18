package com.vivek.search;

/**
 * Search Insert Position
 *
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 * Example 3:
 *
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 * Approach: Return l when key is not found in ordinary binary search
 *
 * https://leetcode.com/problems/search-insert-position
 */
public class SearchInsertPosition {

    static int searchInsert(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        int mid = 0;
        while (l <= h) {
            mid = (l + h) / 2;
            if (nums[mid] == target)
                return mid;
            else if (target < nums[mid]) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 7;

        System.out.println(searchInsert(nums, target));
    }

}
