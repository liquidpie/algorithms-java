package com.vivek.graph.examples;

/**
 * Max Area of Island
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally
 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 * https://leetcode.com/problems/max-area-of-island
 */
public class IslandMaxArea {

    static int[] rowNbr = {-1, 1, 0, 0};
    static int[] colNbr = {0, 0, -1, 1};
    static int maxAreaOfIsland(int[][] grid) {
        int mRow = grid.length;
        int mCol = grid[0].length;

        boolean[][] visited = new boolean[mRow][mCol];
        int result = 0;
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mCol; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(grid, i, j, mRow, mCol, visited, 1);
                    result = Math.max(result, area);
                }
            }
        }

        return result;
    }

    private static int dfs(int[][] grid, int row, int col, int mRow, int mCol, boolean[][] visited, int currArea) {
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            if (isSafe(grid, row + rowNbr[i], col + colNbr[i], mRow, mCol, visited)) {
                currArea = dfs(grid, row + rowNbr[i], col + colNbr[i], mRow, mCol, visited, currArea + 1);
            }
        }
        return currArea;
    }

    private static boolean isSafe(int[][] grid, int row, int col, int mRow, int mCol, boolean[][] visited) {
        return row >= 0 && row < mRow &&
                col >= 0 && col < mCol &&
                grid[row][col] == 1 &&
                !visited[row][col];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };

//        int[][] grid = new int[][] {
//                {0,0,1,0,0,0,0,1,1,0,0,0,0}
//        };

        System.out.println(maxAreaOfIsland(grid));
    }

}
