package com.vivek.matrix;

import java.util.Arrays;

/**
 * Rotate a matrix by 90 degree without using any extra space
 *
 * Given a square matrix, turn it by 90 degrees in anti-clockwise direction without using any extra space.
 *
 * Approach: The idea is to find the transpose of the matrix and then reverse the columns of the transposed matrix.
 *
 * Reference: https://www.geeksforgeeks.org/rotate-matrix-90-degree-without-using-extra-space-set-2/
 */
public class RotateMatrix90Degrees {

    static void rotate(int[][] matrix) {
        transpose(matrix);
        reverseColumns(matrix);
    }

    private static void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /*
        Outer loop from 0 to column count and inner loop from 0 to row count/2,
        interchange elements at (i, j) with (i, row[count-1-j]),
        where i and j are indices of inner and outer loop respectively.
     */
    private static void reverseColumns(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0, k = n - 1; j < k; j++, k--) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[k][i];
                matrix[k][i] = temp;
            }
        }
    }

    private static void print(int[][] matrix) {
        for (int[] arr : matrix)
            System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        };

        rotate(matrix);

        print(matrix);
    }

}
