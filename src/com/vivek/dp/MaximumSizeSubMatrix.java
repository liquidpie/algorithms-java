package com.vivek.dp;

/**
 * Maximum size square sub-matrix with all 1s
 *
 * Given a binary matrix mat of size n * m, the task is to find out the maximum length of a side of a square sub-matrix with all 1s.
 *
 * Example:
 *
 * Input:
 * mat = [
 *    [0, 1, 1, 0, 1],
 *    [1, 1, 0, 1, 0],
 *    [0, 1, 1, 1, 0],
 *    [1, 1, 1, 1, 0],
 *    [1, 1, 1, 1, 1],
 *    [0, 0, 0, 0, 0] ]
 *
 * Output: 3
 * Explanation: The maximum length of a side of the square sub-matrix is 3 where every element is 1.
 *
 * Input:
 * mat = [[1, 1],
 *       [1, 1]]
 *
 * Output: 2
 * Explanation: The maximum length of a side of the square sub-matrix is 2. The matrix itself is the maximum sized sub-matrix in this case.
 *
 * Reference:
 * https://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
 */
public class MaximumSizeSubMatrix {

    public static void main(String[] args) {
        int[][] mat = {
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };
        System.out.println(maxSquare(mat));
    }

    static int maxSquare(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int ans = 0;

        // Create 2d array for tabulation
        int[][] dp = new int[n + 1][m + 1];

        // Fill the dp
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // If square cannot be formed
                if (mat[i][j] == 0) {
                    continue;
                }

                dp[i + 1][j + 1] = 1 + Math.min(dp[i + 1][j],
                        Math.min(dp[i][j + 1], dp[i][j]));

                ans = Math.max(ans, dp[i + 1][j + 1]);
            }
        }

        return ans;
    }

}
