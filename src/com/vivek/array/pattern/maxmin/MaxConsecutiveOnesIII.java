package com.vivek.array.pattern.maxmin;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1004. Max Consecutive Ones III
 *
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 * Example 1:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Example 2:
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Solution:
 *
 * - Initialize two pointers. The two pointers help us to mark the left and right end of the window/subarray with contiguous 1's.
 * - left = 0, right = 0, window_size = 0
 * - We use the right pointer to expand the window until the window/subarray is desirable. i.e.
 *   number of 0's in the window are in the allowed range of [0, k].
 * - Once we have a window which has more than the allowed number of 0's, we can move the left pointer ahead
 *   one by one until we encounter 0 on the left too. This step ensures we are throwing out the extra zero.
 *
 * Reference:
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int[] nums2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};

        System.out.println(findMaxConsecutiveOnes(nums1, 2)); // 6
        System.out.println(findMaxConsecutiveOnes(nums2, 3)); // 10
    }

    static int findMaxConsecutiveOnes(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end;
        for (end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                k--;
            }
            if (k < 0) {
                k += 1 - nums[start];
                start++;
            }
            max = Math.max(max, end - start);
        }
        // The reason why this works is that the window size only increases or stays the same as the loop progresses.
        // it never shrinks. When a zero is encountered and k is reduced, the left pointer may move right but the right pointer
        // also moves right on the next iteration maintaining or increasing the size of the window
        // return end - start;
        return Math.max(max, end - start);
    }

}
