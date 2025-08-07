package com.vivek.matrix;

/**
 * 48. Rotate Image
 *
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * Example 2:
 *
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 *
 * Solution:
 * To achieve a 90-degree rotation, two key transformations are needed:
 *
 *     Transpose (Swap rows and columns)
 *     Vertical Reversal (Flip along the horizontal axis)
 *
 *
 * Combining these steps effectively rotates the matrix. Let’s break it down:
 *
 * 1. Transpose
 *
 * Transposing a matrix swaps the rows and columns. In simpler terms, we swap matrix[i][j] with matrix[j][i] for every valid pair of indices above the diagonal.
 *
 * 2. Vertical Reversal
 *
 * In this step, we flip the matrix upside down. For every column, the topmost element swaps with the bottommost element,
 * the second topmost swaps with the second bottommost, and so on.
 *
 *
 * After transpose:
 * Example 1:
 *         1 4 7
 *         2 5 8
 *         3 6 9
 *
 * Example 2:
 *         5  2  13 15
 *         1  4  3  14
 *         9  8  6  12
 *         11 10 7  16
 *
 * Reference:
 * https://leetcode.com/problems/rotate-image/description
 */
public class RotateImage {

    public static void main(String[] args) {
        RotateImage helper = new RotateImage();

        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        helper.rotate(matrix);
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        int m = n / 2;
        // vertical reverse
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

}
