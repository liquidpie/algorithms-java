package com.vivek.dp;

/**
 * 221. Maximal Square
 *
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example 1:
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 *
 * Example 2:
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 *
 * Example 3:
 * Input: matrix = [["0"]]
 * Output: 0
 *
 * Reference:
 * https://leetcode.com/problems/maximal-square/description/
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalSquare(matrix)); // Output: 4
    }

    static int maximalSquare(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int ans = 0;

        int[][] dp = new int[n + 1][m + 1];

        // Fill the dp
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // If square cannot be formed
                if (matrix[i][j] == '0') {
                    continue;
                }

                dp[i + 1][j + 1] = 1 + Math.min(dp[i + 1][j],
                        Math.min(dp[i][j + 1], dp[i][j]));

                ans = Math.max(ans, dp[i + 1][j + 1]);
            }
        }

        return ans * ans;
    }

}
