package com.vivek.matrix;

/**
 * Find the row with maximum number of 1s
 *
 * Given a boolean 2D array, where each row is sorted. Find the row with the maximum number of 1s.
 *
 * Example:
 *
 * Input matrix
 * 0 1 1 1
 * 0 0 1 1
 * 1 1 1 1  // this row has maximum 1s
 * 0 0 0 0
 *
 * Output: 2
 */
public class RowWithMax1s {

    static int rowWithMax1s(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int leftmostOneIdx = n - 1;
        int rowWithMax1s = 0;

        // Find the left most position of 1 in row 0
        for (int col = 0; col < n; col++) {
            if (mat[0][col] == 1)
                leftmostOneIdx = col;
        }

        if (leftmostOneIdx == 0)
            return rowWithMax1s;

        // Check for below rows if it has left most position of 1 as former index than previous row
        for (int row = 1; row < m; row++) {
            while (leftmostOneIdx > 0 && mat[row][leftmostOneIdx - 1] == 1) {
                leftmostOneIdx--;
                rowWithMax1s = row;
            }
        }

        return rowWithMax1s;

    }

    public static void main(String[] args) {
        int[][] mat = {
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };
        System.out.println("Index of row with maximum 1s is " + rowWithMax1s(mat));
    }

}
