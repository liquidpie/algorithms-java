package com.vivek.search;

import java.util.Arrays;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 * Solution:
 * You don't just use one binary search; you utilize two of them. 🪄 Yes, you heard that right!
 *
 * One binary search is dedicated to finding the lower bound, and the other to discover the upper bound.
 *
 * Reference: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
 */
public class FirstLastPositionInSortedArray {

    public static void main(String[] args) {
        FirstLastPositionInSortedArray helper = new FirstLastPositionInSortedArray();
        int[] arr = {1, 3, 3, 3, 3, 5};
        System.out.println(Arrays.toString(helper.searchRange(arr, 3)));
    }

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int[] pos = {-1, -1};
        if (n == 0)
            return pos;

        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                if (m == 0 || (nums[m - 1] < nums[m])) {
                    pos[0] = m;
                    break;
                } else {
                    l--;
                }
            } else if (nums[m] < target) {
                l++;
            } else {
                r--;
            }
        }

        l = 0;
        r = n - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                if (m == n - 1 || (nums[m + 1] > nums[m])) {
                    pos[1] = m;
                   break;
                } else {
                    r++;
                }
            } else if (nums[m] < target) {
                l++;
            } else {
                r--;
            }
        }

        return pos;
    }

}
