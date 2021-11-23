package com.vivek.matrix;

/**
 * Given a binary matrix, find out the maximum size square sub-matrix with all 1s.
 *
 * Algorithm:
 * Let the given binary matrix be M[R][C]. The idea of the algorithm is to construct an auxiliary size matrix S[][] in which each entry S[i][j] represents size of the square sub-matrix with all 1s including M[i][j] where M[i][j] is the rightmost and bottom-most entry in sub-matrix.
 *
 * 1) Construct a sum matrix S[R][C] for the given M[R][C].
 *      a)    Copy first row and first columns as it is from M[][] to S[][]
 *      b)    For other entries, use following expressions to construct S[][]
 *          If M[i][j] is 1 then
 *             S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1
 *          Else If M[i][j] is 0
 *             S[i][j]=0
 * 2)Find the maximum entry in S[R][C]
 * 3)Using the value and coordinates of maximum entry in S[i],print
 * sub-matrix of M[][]
 *
 * For the given M[R][C]in above example,constructed S[R][C]would be:
 *
 *    0 1 1 0 1
 *    1 1 0 1 0
 *    0 1 1 1 0
 *    1 1 2 2 0
 *    1 2 2 3 1
 *    0 0 0 0 0
 *
 * The value of maximum entry in above matrix is 3and coordinates of the entry are(4,3).
 * Using the maximum value and its coordinates,we can find out the required sub-matrix.
 */

public class LargestSquareSubmatrixInBinaryMatrix {

    static void largestSquareSubMatrix(int[][] matrix) {
        if (matrix == null)
            return;

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] aux = new int[m][n];

        for (int i = 0; i < m; i++) {
            aux[i][0] = matrix[i][0];
        }

        for (int i = 0; i < n; i++) {
            aux[0][i] = matrix[0][i];
        }

        int max_i = 0;
        int max_j = 0;
        int max = aux[0][0];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    aux[i][j] = 0;
                } else {
                    aux[i][j] = Math.min(aux[i - 1][j], Math.min(aux[i][j - 1], aux[i - 1][j - 1])) + 1;
                }
                if (max < aux[i][j]) {
                    max = aux[i][j];
                    max_i = i;
                    max_j = j;
                }
            }
        }

        String topLeft = (max_i - max + 1) + ", " + (max_j - max + 1);
        String bottomRight = max_i + ", " + max_j;
        System.out.printf("Top-left: (%s), Bottom-right: (%s)%n", topLeft, bottomRight);

    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };
        largestSquareSubMatrix(arr);
    }

}
