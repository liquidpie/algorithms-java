package com.vivek.dp;

/**
 * Maximum Falling Path Sum
 *
 * Given a 2D array of integers, pick one element from each row, with a restriction.
 * Restriction: Element in same column can not be picked for adjacent rows.
 * Maximize the sum of integers picked.
 * Example:
 * Input
 * 1 3 2
 * 4 5 2
 * 6 10 7
 *
 * Output: 17
 *
 * Solution:
 *
 * Approach 1:
 * Solve the problem with Dynamic Programming.
 * - We set the dp table base case for the first row as we can pick elements from any column
 * - We can traverse on the matrix from second row and compare it against the previous row max
 * - Set current dp element as
 *              dp[i][j] = matrix[i][j] + maxPrevRow
 * - return the maximum sum which will be in the last row
 *
 * This approach suffers from O(m * n^2) Time complexity
 *
 * Approach 2:
 * We can improve on the Time Complexity by by eliminating the innermost loop and
 * pre-computing `max from prev row` and `second max from prev row`
 * Time Complexity: O(m * n)
 *
 * Approach 3:
 * We can reduce the space complexity of the algorithm by using a rolling array approach.
 * Instead of storing the entire dp table, we can maintain an array to store the maximum sum till the ith row and roll it over for the next row.
 *
 */
public class MaximumFallingPathSum {

    /**
     * In this code, the maxSum method takes a 2D array matrix as input. It initializes a dynamic programming table dp of the same size as the matrix.
     * The dp[i][j] represents the maximum sum achievable by picking an element from row i and column j while satisfying the restriction.
     *
     * The code iterates row by row, filling in the dp table. For each element, it finds the maximum value from the previous row's elements,
     * excluding the element in the same column, and adds it to the current element. This ensures that the restriction is satisfied.
     *
     * Finally, the code finds the maximum sum in the last row of the dp table, which represents the maximum possible sum of integers picked.
     */
    static int maxSumApproach1(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        for (int j = 0; j < cols; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int maxPrevRow = 0;
                for (int k = 0; k < cols; k++) {
                    if (j != k) {
                        maxPrevRow = Math.max(maxPrevRow, dp[i - 1][k]);
                    }
                }
                dp[i][j] = matrix[i][j] + maxPrevRow;
            }
        }

        int maxSum = 0;
        for (int j = 0; j < cols; j++) {
            maxSum = Math.max(maxSum, dp[rows - 1][j]);
        }

        return maxSum;
    }

    /**
     * In this optimized solution, we maintain two variables, maxPrevRow and secondMaxPrevRow,
     * to keep track of the maximum and second maximum values from the previous row.
     * This eliminates the need for the innermost loop in the previous solution.
     *
     * The code iterates through each row, updating the maxPrevRow and secondMaxPrevRow variables.
     * Then, it iterates through each column, considering the appropriate maximum value from the previous row to
     * calculate the maximum sum for the current element.
     *
     * The remaining parts of the code remain the same, where the maximum sum is found in the last row of the dp table.
     *
     * The optimized solution reduces the time complexity from O(n^3) to O(n^2), resulting in better performance for larger matrices.
     */
    static int maxSumApproach2(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        for (int j = 0; j < cols; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < rows; i++) {
            int maxPrevRow = Integer.MIN_VALUE;
            int secondMaxPrevRow = Integer.MIN_VALUE;

            for (int j = 0; j < cols; j++) {
                if (dp[i - 1][j] > maxPrevRow) {
                    secondMaxPrevRow = maxPrevRow;
                    maxPrevRow = dp[i - 1][j];
                } else if (dp[i - 1][j] > secondMaxPrevRow) {
                    secondMaxPrevRow = dp[i - 1][j];
                }
            }

            for (int j = 0; j < cols; j++) {
                if (dp[i - 1][j] == maxPrevRow) {
                    dp[i][j] = matrix[i][j] + secondMaxPrevRow;
                } else {
                    dp[i][j] = matrix[i][j] + maxPrevRow;
                }
            }
        }

        int maxSum = 0;
        for (int j = 0; j < cols; j++) {
            maxSum = Math.max(maxSum, dp[rows - 1][j]);
        }

        return maxSum;
    }

    /**
     *
     */
    static int maxSumApproach3(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] maxValues = new int[cols];

        for (int j = 0; j < cols; j++) {
            maxValues[j] = matrix[0][j];
        }

        for (int i = 1; i < rows; i++) {
            int prevMax = maxValues[0];
            int prevSecondMax = 0;

            for (int j = 0; j < cols; j++) {
                if (maxValues[j] > prevMax) {
                    prevSecondMax = prevMax;
                    prevMax = maxValues[j];
                } else if (maxValues[j] > prevSecondMax) {
                    prevSecondMax = maxValues[j];
                }
            }

            for (int j = 0; j < cols; j++) {
                if (maxValues[j] == prevMax) {
                    maxValues[j] = matrix[i][j] + prevSecondMax;
                } else {
                    maxValues[j] = matrix[i][j] + prevMax;
                }
            }
        }

        int maxSum = 0;
        for (int j = 0; j < cols; j++) {
            maxSum = Math.max(maxSum, maxValues[j]);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 2},
                {4, 5, 2},
                {6, 10, 7}
        };

        int result = maxSumApproach3(matrix);
        System.out.println("Maximized sum: " + result);  // Output: Maximized sum: 17
    }

}
