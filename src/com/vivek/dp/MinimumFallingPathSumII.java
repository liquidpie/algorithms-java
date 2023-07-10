package com.vivek.dp;

/**
 * Minimum Falling Path Sum II
 *
 * Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
 *
 * A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.
 *
 * Example 1:
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 13
 * Explanation:
 * The possible falling paths are:
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * The falling path with the smallest sum is [1,5,7], so the answer is 13.
 *
 * Example 2:
 *
 * Input: grid = [[7]]
 * Output: 7
 *
 * Solution:
 * Works on similar approach as MaximumFallingPathSum
 *
 * https://leetcode.com/problems/minimum-falling-path-sum-ii/
 */
public class MinimumFallingPathSumII {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(minFallingPathSum(grid));
    }

    static int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] minValues = new int[n];
        for (int j = 0; j < n; j++) {
            minValues[j] = grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            int prevRowMin = Integer.MAX_VALUE;
            int prevRowSecondMin = Integer.MAX_VALUE;;

            for (int j = 0; j < n; j++) {
                if (minValues[j] < prevRowMin) {
                    prevRowSecondMin = prevRowMin;
                    prevRowMin = minValues[j];
                } else if (minValues[j] < prevRowSecondMin) {
                    prevRowSecondMin = minValues[j];
                }
            }

            for (int j = 0; j < n; j++) {
                if (minValues[j] == prevRowMin) {
                    minValues[j] = grid[i][j] + prevRowSecondMin;
                } else {
                    minValues[j] = grid[i][j] + prevRowMin;
                }
            }
        }

        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minSum = Math.min(minSum, minValues[i]);
        }

        return minSum;
    }

}
