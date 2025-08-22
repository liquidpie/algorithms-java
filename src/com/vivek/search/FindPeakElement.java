package com.vivek.search;

/**
 * 162. Find Peak Element
 *
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks,
 * return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater
 * than a neighbor that is outside the array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 * Reference:
 * https://leetcode.com/problems/find-peak-element/
 */
public class FindPeakElement {

    public static void main(String[] args) {
        FindPeakElement helper = new FindPeakElement();
        {
            int[] nums = {1, 2, 3, 1};
            System.out.println(helper.findPeakElement(nums)); // 2
        }
        {
            int[] nums = {1,2,1,3,5,6,4};
            System.out.println(helper.findPeakElement(nums)); // 5
        }
        {
            int[] nums = {1,2,3,4};
            System.out.println(helper.findPeakElement(nums)); // 3
        }
    }

    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if ((m == nums.length - 1 || nums[m] > nums[m + 1]) &&
                    (m == 0 || nums[m] > nums[m - 1])) {
                return m;
            } else if (m == 0 || nums[m] > nums[m - 1]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return -1;
    }

}
