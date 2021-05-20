package com.vivek.graph.examples;

import java.util.HashSet;
import java.util.Set;

/**
 * Number of Distinct Islands
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Solution:
 * At the beginning, we need to find every island, which we can do using a straightforward depth-first search. The hard part is deciding whether two islands are the same.
 *
 * Since two islands are the same if one can be translated to match another, let's translate every island so the top-left corner is (0, 0) For example, if an island is made from squares [(2, 3), (2, 4), (3, 4)], we can think of this shape as [(0, 0), (0, 1), (1, 1)] when anchored at the top-left corner.
 *
 * From there, we only need to check how many unique shapes there ignoring permutations (so [(0, 0), (0, 1)] is the same as [(0, 1), (1, 0)]). We use sets directly as we have showcased below, but we could have also sorted each list and put those sorted lists in our set structure shapes.
 */
public class DistinctIslandCount {

    private static final int ROW = 5;
    private static final int COL = 5;

    int countIslands(int[][] grid) {

        Set<String> result = new HashSet<>();
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (grid[row][col] == 1) {
                    Set<String> islandStr = new HashSet<>();
                    dfs(grid, row, col, row, col, islandStr);
                    result.add(islandStr.toString());
                }
            }
        }

        return result.size();
    }

    private void dfs(int[][] grid, int row, int col, int baseRow, int baseCol, Set<String> islandStr) {
        if (isSafe(grid, row, col)) {
            islandStr.add((row - baseRow) + "_" + (col - baseCol));
            grid[row][col] = -1;

            // recur for top, bottom, left and right cells
            dfs(grid, row - 1, col, baseRow, baseCol, islandStr);
            dfs(grid, row + 1, col, baseRow, baseCol, islandStr);
            dfs(grid, row, col - 1, baseRow, baseCol, islandStr);
            dfs(grid, row, col + 1, baseRow, baseCol, islandStr);
        }
    }

    private boolean isSafe(int[][] grid, int row, int col) {
        return row >= 0 && row < ROW &&
                col >= 0 && col < COL &&
                grid[row][col] == 1;
    }

    public static void main(String[] args) {
        int[][] m = new int[][] {
                { 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 }
        };

        DistinctIslandCount islands = new DistinctIslandCount();
        System.out.println(islands.countIslands(m));
    }

}
