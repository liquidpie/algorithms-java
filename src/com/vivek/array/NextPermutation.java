package com.vivek.array;

import java.util.Arrays;

/**
 * Next Permutation
 *
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order,
 * then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 *
 * The replacement must be in place and use only constant extra memory.

 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 *
 * https://leetcode.com/problems/next-permutation
 */
public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int n = nums.length;
        int i;
        for (i = n - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i])
                break;
        }

        if (i == 0) {
            // Array is already sorted in descending order, sort it in ascending order
            Arrays.sort(nums);
            return;
        }

        // find next greater element in the subarray
        int x = nums[i - 1];
        int smallest = i;
        for (int j = i; j < n; j++) {
            if (nums[j] > x && nums[j] < nums[smallest]) {
                smallest = j;
            }
        }

        // swap the two numbers
        swap(nums, i - 1, smallest);

        // sort the remaining sub-array
        Arrays.sort(nums, i, n);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
//        int[] nums = {3,2,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

}
