package com.vivek.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 698. Partition to K Equal Sum Subsets
 *
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty
 * subsets whose sums are all equal.
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 *
 *
 * Approach:
 * https://chatgpt.com/c/687571c6-d5fc-8010-9e67-973cafc42d9d
 *
 * Reference:
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
 */
public class PartitionToKEqualSubsetsSum {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = Arrays.stream(nums).sum();
        if (total % k != 0) return false;

        int target = total / k;
        Arrays.sort(nums);
        int n = nums.length;

        if (nums[n - 1] > target) return false;

        boolean[] visited = new boolean[n];
        Map<String, Boolean> memo = new HashMap<>();

        return backtrack(nums, k, 0, 0, visited, target, memo);
    }

    private boolean backtrack(int[] nums, int k, int currSum, int start, boolean[] visited,
                              int target, Map<String, Boolean> memo) {
        if (k == 0) return true;
        if (currSum == target)
            return backtrack(nums, k - 1, 0, 0, visited, target, memo);

        String key = Arrays.toString(visited) + "_" + currSum + "_" + k;
        if (memo.containsKey(key)) return memo.get(key);

        for (int i = start; i < nums.length; i++) {
            if (!visited[i] && currSum + nums[i] <= target) {
                visited[i] = true;
                if (backtrack(nums, k, currSum + nums[i], i + 1, visited, target, memo)) {
                    memo.put(key, true);
                    return true;
                }
                visited[i] = false; // backtrack
            }
        }

        memo.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        PartitionToKEqualSubsetsSum solver = new PartitionToKEqualSubsetsSum();
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(solver.canPartitionKSubsets(nums, k)); // Output: true
    }

}
