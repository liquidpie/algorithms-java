package com.vivek.matrix;

import java.util.Arrays;

/**
 * Q1. Given a 2d matrix, where each cell contain either 0 or 1
 * find the size of the largest X that can be formed using 1s
 *
 *    0 0 0 1
 *    1 0 1 0
 *    0 1 0 0
 *    1 0 1 0
 *
 *    size = 3
 *
 *    1 1 0 1
 *    1 1 1 0
 *    0 1 0 0
 *    1 0 1 0
 *
 * Approach:
 *
 * If you're OK with O(n^2) extra space, then one option is to build two extra arrays:
 * one to record the length of the longest '\' shape centered at each cell,
 * one to record the length of the longest '/'.
 * (You can build each one in O(n^2) time by using a triply-nested loop -- which might sound like O(n^3) time,
 * but the memoization means that you only need to iterate once over any given \ or /,
 * so the innermost loop can be amortized-constant time.)
 * You then iterate over all positions; for any position, the largest X centered at that position has size
 * equal to the lesser of the two matrices' values at that position.
 *
 * Just track the greatest such lesser value, and you're done.
 * That has worst-case time complexity of O(n^2), which is clearly asymptotically optimal.
 *
 * Actually, one extra array is enough, you can combine the scan of the other direction with the last step.
 *
 * Reference: https://stackoverflow.com/questions/44794063/find-greatest-x-shape-in-a-binary-matrix
 */
public class LargestXBinaryMatrix {

    static int sizeOfLargestX(int[][] matrix) {
        if (matrix == null)
            return 0;

        int row = matrix.length;
        int col = matrix[0].length;

        print(matrix);

        int[][] aux = new int[row][col];

        // Record the length of the longest '\' diagonal centered at each cell
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int dimension = Math.min(i, j);
                int len = 0;
                if (matrix[i][j] == 1) {
                    len += 1;
                    for (int k = 1; k <= dimension && isSafe(i, j, row, col, k); k++) {
                        if (matrix[i - k][j - k] == 1 && matrix[i + k][j + k] == 1)
                            len += 2;
                    }
                }
                aux[i][j] = len == 1 ? 0 : len;
            }
        }

        print(aux);

        // Record the length of the longest '/' diagonal centered at each cell
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int dimension = Math.min(i, j);
                int len = 0;
                if (matrix[i][j] == 1) {
                    len += 1;
                    for (int k = 1; k <= dimension && isSafe(i, j, row, col, k); k++) {
                        if (matrix[i + k][j - k] == 1 && matrix[i - k][j + k] == 1)
                            len += 2;
                    }
                }
                aux[i][j] = Math.min(aux[i][j], len);
            }
        }

        print(aux);

        // Find the cell with greatest value
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result = Math.max(result, aux[i][j]);
            }
        }

        return result;
    }

    private static boolean isSafe(int i, int j, int row, int col, int k) {
        return (i + k) < row && (i - k) >= 0 && (j + k) < col && (j - k) >= 0;
    }

    private static void print(int[][] matrix) {
        for (int[] arr : matrix)
            System.out.println(Arrays.toString(arr));

        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 1},
                {1, 1, 1, 0},
                {0, 1, 1, 0},
                {1, 0, 1, 1}
        };

        System.out.println(sizeOfLargestX(matrix));
    }

}
