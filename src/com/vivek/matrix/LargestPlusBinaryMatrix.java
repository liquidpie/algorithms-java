package com.vivek.matrix;

/**
 * Find size of the largest ‘+’ formed by all ones in a binary matrix
 *
 * Given a N X N binary matrix, find the size of the largest ‘+’ formed by all 1s.
 * Example:
 *
 *             1, 0, 1, 1, 1, 1, 0, 1, 1, 1
 *             1, 0, 1, 0, 1, 1, 1, 0, 1, 1
 *             1, 1, 1, 0, 1, 1, 0, 1, 0, 1
 *             0, 0, 0, 0, 1, 0, 0, 1, 0, 0
 *             1, 1, 1, 0, 1, 1, 1, 1, 1, 1
 *             1, 1, 1, 1, 1, 1, 1, 1, 1, 0
 *             1, 0, 0, 0, 1, 0, 0, 1, 0, 1
 *             1, 0, 1, 1, 1, 1, 0, 0, 1, 1
 *             1, 1, 0, 0, 1, 0, 1, 0, 0, 1
 *             1, 0, 1, 1, 1, 1, 0, 1, 0, 0
 *
 * Approach:
 *
 * The idea is to maintain four auxiliary matrices left[][], right[][], top[][], bottom[][] to store consecutive 1’s in every direction. For each cell (i, j) in the input matrix, we store below information in these four matrices –
 *
 *   left(i, j) stores maximum number of
 *   consecutive 1's to the left of cell (i, j)
 *   including cell (i, j).
 *
 *   right(i, j) stores maximum number of
 *   consecutive 1's to the right of cell (i, j)
 *   including cell (i, j).
 *
 *   top(i, j) stores maximum number of
 *   consecutive 1's at top of cell (i, j)
 *   including cell (i, j).
 *
 *   bottom(i, j) stores maximum number of
 *   consecutive 1's at bottom of cell (i, j)
 *   including cell (i, j).
 *
 * After computing value for each cell of above matrices,
 * the largest + would be formed by a cell of input matrix that has maximum value by considering minimum of (left(i, j), right(i, j), top(i, j), bottom(i, j) )
 * We can use Dynamic Programming to compute the total amount of consecutive 1’s in every direction.
 *
 *
 *   if mat(i, j) == 1
 *       left(i, j) = left(i, j - 1) + 1
 *   else left(i, j) = 0
 *
 *   if mat(i, j) == 1
 *       top(i, j) = top(i - 1, j) + 1;
 *   else
 *       top(i, j) = 0;
 *
 *   if mat(i, j) == 1
 *       bottom(i, j) = bottom(i + 1, j) + 1;
 *   else
 *       bottom(i, j) = 0;
 *
 *   if mat(i, j) == 1
 *       right(i, j) = right(i, j + 1) + 1;
 *   else
 *       right(i, j) = 0;
 *
 * Reference: https://www.geeksforgeeks.org/find-size-of-the-largest-formed-by-all-ones-in-a-binary-matrix
 */
public class LargestPlusBinaryMatrix {

    static int sizeOfLargestPlus(int[][] matrix) {
        if (matrix == null)
            return 0;

        int n = matrix.length;

        int[][] left = new int[n][n];
        int[][] right = new int[n][n];
        int[][] top = new int[n][n];
        int[][] bottom = new int[n][n];

        // initialize above four matrices
        for (int i = 0; i < n; i++) {
            left[i][0] = matrix[i][0];
            right[i][n - 1] = matrix[i][n - 1];
            top[0][i] = matrix[0][i];
            bottom[n - 1][i] = matrix[n - 1][i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                left[i][j] = (matrix[i][j] == 1) ? (left[i][j - 1] + 1) : 0;
                top[j][i] = (matrix[j][i] == 1) ? (top[j - 1][i] + 1) : 0;

                // calculate new value of j
                j = n - 1 - j;

                right[i][j] = (matrix[i][j] == 1) ? (right[i][j + 1] + 1) : 0;
                bottom[j][i] = (matrix[j][i] == 1) ? (bottom[j + 1][i] + 1) : 0;

                // revert back value of j
                j = n - 1 - j;
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int total = Math.min(Math.min(Math.min(left[i][j], right[i][j]), top[i][j]), bottom[i][j]);
                total = ((total - 1) * 4) + 1;
                result = Math.max(result, total);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0 },
                { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                { 1, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
                { 1, 1, 0, 0, 1, 0, 1, 0, 0, 1 },
                { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }
        };

        System.out.println(sizeOfLargestPlus(matrix));
    }

}
