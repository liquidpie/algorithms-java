package com.vivek.array.pattern.twopointer;

import java.util.Arrays;

/**
 * 167. Two Sum II - Input Array Is Sorted
 *
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Your solution must use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 *
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 *
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 *
 * Reference:
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description
 */
public class TwoSum2SortedArray {

    public static void main(String[] args) {
        int[] numbers1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println(Arrays.toString(twoSum(numbers1, target1))); // [1, 2]

        int[] numbers2 = {2, 3, 4};
        int target2 = 6;
        System.out.println(Arrays.toString(twoSum(numbers2, target2))); // [1, 3]

        int[] numbers3 = {-1, 0};
        int target3 = -1;
        System.out.println(Arrays.toString(twoSum(numbers3, target3))); // [1, 2]
    }

    static int[] twoSum(int[] numbers, int target) {
        int[] indices = {-1, -1};
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                indices[0] = left + 1;
                indices[1] = right + 1;
                return indices;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return indices;
    }

}
