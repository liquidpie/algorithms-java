package com.vivek.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Combination Sum
 *
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * frequency
 *  of at least one of the chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 *
 * Input: candidates = [2], target = 1
 * Output: []
 *
 * Approach ::
 * The approach for the problem is simple to find. Generally, in problems such as this, we are required to use backtracking.
 * Because in such questions we are required to do a complete search and find the answer. So first, we sort the list of
 * integers that is given to us. Afterward, we create a recursive function that keeps on adding an integer to the current
 * temporary list of integers and keeping track of the remaining sum required to add up to the given target.
 * So inside the recursive function, we keep two base cases. The first base case is to check if the remaining sum
 * required goes negative. In that case, we will return from the recursive function. The second base case is our
 * required condition if the remaining sum equals zero. If the remaining sum is zero this means that we have reached
 * our required target. At that point, we push the current temporary list of integers into the output array of arrays.
 *
 * Complexity Analysis
 * Time Complexity
 * O(N^(M/T + 1)) where N is the number of candidates, M is the smallest candidate among all the given integers,
 * and T is the target value. Thus the time complexity is exponential and this is expected because the algorithm is recursive backtracking.
 *
 * Space Complexity
 * O(T/M), where T is the target value and M is the minimal element among all other candidates.
 *
 * Solution: https://tutorialcup.com/leetcode-solutions/combination-sum-leetcode-solution.htm
 *
 *
 * https://leetcode.com/problems/combination-sum
 */
public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int remain, int start, List<Integer> tempList, List<List<Integer>> result) {
        if (remain < 0)
            return;
        else if (remain == 0)
            result.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                backtrack(candidates, remain - candidates[i], i, tempList, result); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }

}
