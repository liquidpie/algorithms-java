package com.vivek.matrix;

import java.util.ArrayList;
import java.util.List;

public class DiagonalTraversal {

    private static final boolean zigZag = true; // parameter to print in zigZag order

    static void byAuxiliarySpace(int[][] mat, int m, int n) {
        List<List<Integer>> diagonals = new ArrayList<>(m + n - 1);

        // we will use a 2D vector to store the diagonals of our array the 2D vector will have (n+m-1)
        // rows that is equal to the number of diagonals
        for (int i = 0; i < n + m - 1; i++) {
            diagonals.add(new ArrayList<>());
        }

        // fill diagonal lists
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diagonals.get(i + j).add(mat[i][j]);
            }
        }

        if (zigZag) {
            boolean upwards = false;
            for (int i = 0; i < diagonals.size(); i++) {
                if (upwards) {
                    for (int j = diagonals.get(i).size() - 1; j >= 0; j--) {
                        System.out.print(diagonals.get(i).get(j) + " ");
                    }
                } else {
                    for (int j = 0; j < diagonals.get(i).size(); j++) {
                        System.out.print(diagonals.get(i).get(j) + " ");
                    }
                }
                upwards = !upwards;
                System.out.println();
            }
        } else {
            for (int i = 0; i < diagonals.size(); i++) {
                for (int j = 0; j < diagonals.get(i).size(); j++) {
                    System.out.print(diagonals.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }
    }


    static void withoutExtraSpace(int[][] mat, int m , int n) {
        int i = 0, j = 0;

        boolean isUp = true;

        // Traverse the matrix till all elements get traversed
        for (int k = 0; k < m * n;) {
            if (isUp) {
                for (; i >= 0 && j < n; j++, i--) {
                    System.out.print(mat[i][j] + " ");
                    k++;
                }

                // Set i and j according to direction
                if (i < 0 && j <= n - 1)
                    i = 0;
                if (j == n) {
                    i = i + 2;
                    j--;
                }
            }

            else {
                for (; j >= 0 && i < m; i++, j--) {
                    System.out.print(mat[i][j] + " ");
                    k++;
                }

                // Set i and j according to direction
                if (j < 0 && i <= m - 1)
                    j = 0;
                if (i == m) {
                    j = j + 2;
                    i--;
                }
            }

            isUp = !isUp;

            System.out.println();
        }
    }


    public static void main(String[] args) {
        int m = 5;
        int n = 4;
        int[][] matrix = {
                {  1,  2,  3,  4 },
                {  5,  6,  7,  8 },
                {  9, 10, 11, 12 },
                { 13, 14, 15, 16 },
                { 17, 18, 19, 20 }
        };

        byAuxiliarySpace(matrix, m, n);

        withoutExtraSpace(matrix, m, n);
    }

}
