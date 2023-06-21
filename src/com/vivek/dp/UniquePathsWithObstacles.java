package com.vivek.dp;

import java.util.Arrays;

/**
 * Unique Paths II
 *
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). T
 * he robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * Example 1:
 *
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * Example 2:
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 * Idea:
 * The main idea to solve this problem is to use dynamic programming.
 * Let dp[i][j] = number of unique paths ending at this cell.
 * If the current cell contains an obstacle, dp[i][j] = 0.
 * If the current cell is a free cell:
 * We can reach the current cell from the top cell, hence dp[i][j] += dp[i-1][j], provided the top cell exists.
 * We can reach the current cell from the left cell, hence dp[i][j] += dp[i][j-1], provided the left cell exists.
 * Finally, our answer is dp[n-1][m-1].
 *
 * Time Complexity
 * The time complexity of the above code is O(N*M) where N = number of rows and M = number of columns.
 * Since we traversed the entire input matrix at least once, Time Complexity is O(N*M).
 *
 * Space Complexity
 * The space complexity of the above code is O(N*M). We need a dynamic programming matrix of size N*M to store all intermediate values.
 *
 * Solution: https://tutorialcup.com/leetcode-solutions/unique-paths-ii-leetcode-solution.htm
 *
 * https://leetcode.com/problems/unique-paths-ii
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
//        int[][] grid = {
//                {0,1},
//                {0,0}
//        };
        System.out.println(uniquePathsWithObstacles(grid));
    }

    public static int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    if (grid[i][j] == 0)
                        dp[i][j] = 1;
                }
                else if (i == 0) {
                    if (grid[i][j] == 0) {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
                else if (j == 0) {
                    if (grid[i][j] == 0) {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
                else {
                    if (grid[i][j] == 0) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }

}
