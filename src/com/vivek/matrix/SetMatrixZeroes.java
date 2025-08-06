package com.vivek.matrix;

/**
 * 73. Set Matrix Zeroes
 *
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 *
 * You must do it in place.
 *
 * Example 1:
 *
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * Example 2:
 *
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * Solution:
 * - Find if any cell in first row or first column is set as 0.
 * - Traverse through rest of matrix and if any cell=0 is observed, set the corresponding cell in first row and first column as 0.
 * - Set the rows and columns to 0 for which cell with value 0 is observed in first row and first column.
 * - If there were cells with 0 value observed intially in first row or first column, set first row and column to 0 respectively.
 *
 * Reference:
 * https://leetcode.com/problems/set-matrix-zeroes/
 */
public class SetMatrixZeroes {

    public static void main(String[] args) {
        SetMatrixZeroes helper = new SetMatrixZeroes();

        int[][] matrix = {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}

        };
        helper.setZeroes(matrix);
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean zeroInFirstRow = false;
        boolean zeroInFirstCol = false;

        // find 0 in first row and flag it
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                zeroInFirstRow = true;
                break;
            }
        }

        // find 0 in first col and flag it
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                zeroInFirstCol = true;
                break;
            }
        }

        // mark corresponding cells in first row and col as 0 for each cell which is 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // set zeroes as per the 0 in first row and col
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        // mark the complete row as zero
        if (zeroInFirstRow) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // mark the complete col as zero
        if (zeroInFirstCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}
