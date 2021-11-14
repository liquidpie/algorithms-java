package com.vivek.graph.examples;

/**
 * A rectangular map consists of N rows and M cols of square areas is given.
 * Each area is painted with some color.
 *
 * Two areas of the map belong to the same country if the following conditions are met:
 *  1. they have the same color;
 *  2. it is possible to travel from one area to the other orthogonally (moving north, south, east, west)
 *     without moving over areas of a different color
 *
 * Return the number of different countries depicted in the map
 *
 * Variation of Island Count problem
 */
public class CountryCount {

    public int solution(int[][] A) {
        // write your code in Java SE 8
        return countCountries(A, A.length, A[0].length);
    }

    private int countCountries(int[][] a, int n, int m) {
        boolean[][] visited = new boolean[n][m];

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j]) {
                    int color = a[i][j];
                    dfs(a, i, j, visited, n, m, color);
                    ++count;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] a, int i, int j, boolean[][] visited, int n, int m, int color) {
        int[] rowNumber = {-1, 1, 0, 0};
        int[] colNumber = {0, 0, -1, 1};

        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            if (isSafe(a, i + rowNumber[k], j + colNumber[k], visited, n, m, color)) {
                dfs(a, i + rowNumber[k], j + colNumber[k], visited, n, m, color);
            }
        }
    }

    private boolean isSafe(int[][] a, int i, int j, boolean[][] visited, int n, int m, int color) {
        return i >= 0 && i < n
                && j >= 0 && j < m
                && a[i][j] == color
                && !visited[i][j];
    }

    public static void main(String[] args) {
        int[][] a = {
                {5, 4, 4},
                {4, 3, 4},
                {3, 2, 4},
                {2, 2, 2},
                {3, 3, 4},
                {1, 4, 4},
                {4, 1, 1},

        };

        CountryCount cc = new CountryCount();
        System.out.println(cc.solution(a));
    }

}
