package com.vivek.dp;

/**
 * Minimum Path Sum
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example 1:
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 *
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *
 * Example 2:
 *
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 *
 * Idea:
 * The main idea to solve this problem is to use dynamic programming.
 * To reach every cell (i, j), we can either come from the cell lying upwards (i – 1, j) or from the cell lying leftwards (i, j – 1).
 * Iterate for each row and each column, and for every cell find the minimum of these two values:
 * grid[i – 1][j] if (i – 1, j) exists otherwise INT_MAX.
 * grid[i][j – 1] if (i, j – 1) exists otherwise INT_MAX.
 * Hence, the minimum cost to reach the current cell will be grid[i][j] + min_value where min_value is the value obtained in the above step.
 * Our answer will be grid[n – 1][m – 1].
 *
 * https://tutorialcup.com/leetcode-solutions/minimum-path-sum-leetcode-solution.htm
 *
 * Time Complexity
 * The time complexity of the above code is O(N*M) since we’re iterating for each cell, where N = number of rows and M = number of columns.
 *
 * Space Complexity
 * The space complexity of the above code is O(1) since we are using constant extra space.
 *
 * https://leetcode.com/problems/minimum-path-sum
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = { {1,3,1}, {1,5,1}, {4,2,1} };
        int[][] grid2 = { {1,2,3}, {4,5,6 } };

        System.out.println(minPathSum(grid2));
    }

    public static int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int current = Integer.MAX_VALUE;
                if (i - 1 >= 0)
                    current = Math.min(current, grid[i - 1][j]);
                if (j - 1 >= 0)
                    current = Math.min(current, grid[i][j - 1]);
                if (current == Integer.MAX_VALUE)
                    current = 0;

                grid[i][j] += current;
            }
        }

        return grid[n - 1][m - 1];
    }

}
