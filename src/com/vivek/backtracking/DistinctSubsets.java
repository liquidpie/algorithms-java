package com.vivek.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Subsets
 *
 * Given an integer array nums of unique elements, return all possible
 * subsets
 *  (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * Solution: https://walkccc.me/LeetCode/problems/0078/
 * https://tutorialcup.com/leetcode-solutions/subset-leetcode.htm
 *
 * https://leetcode.com/problems/subsets
 */
public class DistinctSubsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsetsUsingBitManipulation(nums));

        System.out.println(subsetsUsingBacktracking(nums));
    }

    /**
     * Approach:
     * Each subset of a set of n elements can be represented as a sequence of n bits, which corresponds to an integer between 0…2n-1.
     * The ones in the bit sequence indicate which elements are included in the subset.
     *
     * Algorithm
     *  1. Declare an array of vectors “ans” in which we will store our all subsets.
     *  2. Initialize a variable n which represents the size of the nums_array.
     *  3. Run a loop for I in range 0 to 2n-1
     *      3.1. Initialize an array “temp” in which we will store our current subset.
     *      3.2. Run a loop for j in range 0 to n-1
     *          3.2.1. If the jth bit of I is set, then add the nums[i] to the temp array.
     *      3.3. Add the “temp” array to “ans”
     *  4. Print the final ans array.
     *
     * Complexity Analysis:
     *
     * Time Complexity:- O(N x 2^N) to generate all subsets and then copy them into the output list.
     * Space Complexity:- O(N x 2^N)  to keep all the subsets of length NN since each of the NN elements could be present or absent.
     */
    static List<List<Integer>> subsetsUsingBitManipulation(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        // Increase the size by using left shift (1 * 2^n)
        int limit = 1 << n;

        for (int i = 0; i < limit; i++) {
            List<Integer> current = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // right shift i and j i.e. i / 2^j
                if (((i >> j) & 1) == 1) {
                    current.add(nums[j]);
                }
            }
            result.add(current);
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/subsets/solutions/27281/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning/
     *
     * https://www.youtube.com/watch?v=REOH22Xwdkk
     *
     * Complexity Analysis:
     *
     * Time Complexity:- O(N x 2^N) to generate all subsets and then copy them into the output list.
     * Space Complexity:- O(N x 2^N)  to keep all the subsets of length NN since each of the NN elements could be present or absent.
     */
    static List<List<Integer>> subsetsUsingBacktracking(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> subset, int[] nums, int start) {
        result.add(new ArrayList<>(subset));
        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            backtrack(result, subset, nums, i + 1);
            subset.remove(subset.size() - 1);
        }
    }

}
