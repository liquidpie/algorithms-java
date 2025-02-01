package com.vivek.array.pattern.twopointer;

import java.util.Arrays;

/**
 * 189. Rotate Array
 *
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 * Solution:
 * Algorithm
 *
 * This approach is based on the fact that when we rotate the array k times, k elements from the back end of the array come to the front and the rest of the elements from the front shift backwards.
 *
 * In this approach, we firstly reverse all the elements of the array. Then, reversing the first k elements followed by reversing the rest n−k elements gives us the required result.
 *
 * Let n=7 and k=3.
 *
 * Original List                   : 1 2 3 4 5 6 7
 * After reversing all numbers     : 7 6 5 4 3 2 1
 * After reversing first k numbers : 5 6 7 4 3 2 1
 * After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
 *
 * Complexity Analysis
 *
 * Time complexity: O(n). n elements are reversed a total of three times.
 *
 * Space complexity: O(1). No extra space is used.
 *
 * Reference:
 * https://leetcode.com/problems/rotate-array/description
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotate(nums, k);
        System.out.println("nums = " + Arrays.toString(nums));

        nums = new int[]{-1, -100, 3, 99};
        k = 2;
        rotate(nums, k);
        System.out.println("nums = " + Arrays.toString(nums));

        {
            nums = new int[]{-1, -100, 3, 99};
            k = 2;
            rotateUsingExtraSpace(nums, k);
            System.out.println("nums = " + Arrays.toString(nums));
        }
    }

    static void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Algorithm
     *
     * We use an extra array in which we place every element of the array at its correct
     * position i.e. the number at index i in the original array is placed at the
     * index (i+k)% length of array.
     * Then, we copy the new array to the original one.
     *
     * Complexity Analysis
     *
     * Time complexity: O(n).
     * One pass is used to put the numbers in the new array.
     * And another pass to copy the new array to the original one.
     *
     * Space complexity: O(n). Another array of the same size is used.
     */
    static void rotateUsingExtraSpace(int[] nums, int k) {
        int[] temp = new int[nums.length];
        k %= nums.length;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }

        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }
    }

}