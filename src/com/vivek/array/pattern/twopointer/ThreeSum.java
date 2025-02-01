package com.vivek.array.pattern.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3Sum
 *
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
 * i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 *
 * https://leetcode.com/problems/3sum
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 5, -2, 3};
        int sumValue = 2;
        System.out.println(sumTriplet(nums, sumValue));
    }

    public static List<List<Integer>> sumTriplet(int[] nums, int sumValue) {
        List<List<Integer>> triplets = new ArrayList<>();

        if (nums == null || nums.length == 0)
            return triplets;

        Arrays.sort(nums);

        for (int index = 0; index < nums.length; index++) {
            // avoid duplicates at first element
            if (index > 0 && nums[index] == nums[index - 1])
                continue;

            int left = index + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[index] + nums[left] + nums[right] < sumValue) {
                    left++;
                } else if (nums[index] + nums[left] + nums[right] > sumValue) {
                    right--;
                } else {
                    triplets.add(List.of(nums[index], nums[left], nums[right]));

                    left++;
                    // avoid duplicate at second element
                    while (nums[left] == nums[left - 1] && left < right)
                        left++;
                }
            }
        }
        return triplets;
    }

}
