package com.vivek.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Subsets II
 *
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets
 *  (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * Solution: https://leetcode.com/problems/subsets/solutions/27281/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning/
 *
 * https://leetcode.com/problems/subsets-ii/
 */
public class DistinctSubsetsWithDuplicates {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(distinctSubsets(nums));
    }

    public static List<List<Integer>> distinctSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> subset, int[] nums, int start) {
        result.add(new ArrayList<>(subset));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue;
            subset.add(nums[i]);
            backtrack(result, subset, nums, i + 1);
            subset.remove(subset.size() - 1);
        }
    }

}
