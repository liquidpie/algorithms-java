package com.vivek.dp;

/**
 * Minimum Path Sum From First Column to Last Column (todo)
 *
 * Given a m x n grid filled with non-negative numbers, find a path from left column to right column, which minimizes the sum of all numbers along its path.
 *
 * Directions:
 *      * left -> right
 *      * top -> bottom
 *      * bottom -> top
 *
 * Start Point: cell(i, 0)
 * End Point: cell(i, n - 1)
 *
 *
 * Idea:
 *
 *
 * Time Complexity
 *
 *
 * Space Complexity
 *
 *
 */
public class MinimumPathSumThreeDirection {

    public static void main(String[] args) {
        int[][] grid = {
                {10,3,2},
                {1,5,100},
                {4,200,1000}
        };

        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        int n = grid.length; // Number of rows
        int m = grid[0].length; // Number of columns

        // Create a DP array to store the minimum path sum
        int[][] dp = new int[n][m];

        // Initialize the first column with the corresponding grid values
        for (int i = 0; i < n; i++) {
            dp[i][0] = grid[i][0];
        }

        // Fill the DP table column by column
        for (int j = 1; j < m; j++) { // Iterate over columns (left-to-right)
            for (int i = n - 1; i >= 0; i--) {
                // Initialize the current cell with a large value
                dp[i][j] = Integer.MAX_VALUE;

                // Check bottom-to-top movement
                if (i < n - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + grid[i][j]);
                }
            }

            for (int i = 0; i < n; i++) { // Iterate over rows in the current column
                // Initialize the current cell with a large value
                dp[i][j] = Integer.MAX_VALUE;

                // Check top-to-bottom movement
                if (i > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + grid[i][j]);
                }

                // Check left-to-right movement
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }

        // Find the minimum value in the last column
        int minPathSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minPathSum = Math.min(minPathSum, dp[i][m - 1]);
        }

        return minPathSum;
    }

}
