package com.vivek.graph.pattern.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * Images are stored in the form of a grid. Image recognition is possible by comparing grids of two images and checking if they have any matching regions.
 *
 * You are given two grids where each cell of a grid contains is either 0 or 1. If two cells share a side then they are adjacent.
 * Cells that contain 1s form a connected region if any cell of that region can be reached by moving through the adjacent cells that contain 1.
 * Overlay the first grid onto the second and if a region in the first grid completely matches a region in the second grid, the regions are matched.
 *
 * Count total number of such matched regions in the second grid.
 *
 * For example given two 3x3 grids:
 *
 * G1:	111		G2: 111
 * 	    100			100
 * 	    100			101
 *
 * There are two regions in G2: {(0,0), (0,1), (0,2), (1,0), (2,0)} and {(2,2)}. Regions in G1 cover the first region in G2 but not the second region. Thus, there is only one matching region.
 *
 * Making a slight alteration to the above example:
 *
 * G1:	111		G2: 111
 * 	    101			100
 * 	    100			101
 *
 * Now there are no matching regions. From G1, "1" at position (1,2) is not matched in G2. G2[2][2] == "1" is not matched in G1.
 */
public class IslandMatching {

    static final int ROW = 3;
    static final int COL = 3;

    int countMatches(int[][] grid1, int[][] grid2) {
        Set<String> islands1 = islands(grid1);
        Set<String> islands2 = islands(grid2);

        islands1.retainAll(islands2);
        return islands1.size();
    }

    Set<String> islands(int[][] grid) {
        boolean[][] visited = new boolean[ROW][COL];
        Set<String> result = new HashSet<>();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    Set<String> islandStr = new HashSet<>();
                    dfs(grid, i, j, visited, islandStr);
                    result.add(islandStr.toString());
                }
            }
        }
        return result;
    }

    private void dfs(int[][] grid, int row, int col, boolean[][] visited, Set<String> islandStr) {
        int[] rowNbr = {-1, 1,  0, 0};
        int[] colNbr = { 0, 0, -1, 1};

        visited[row][col] = true;
        islandStr.add(row + "-" + col);

        for (int k = 0; k < 4; k++) {
            if (isSafe(grid, row + rowNbr[k], col + colNbr[k], visited)) {
                dfs(grid, row + rowNbr[k], col + colNbr[k], visited, islandStr);
            }
        }
    }

    private boolean isSafe(int[][] matrix, int row, int col, boolean[][] visited) {
        return row >= 0 && row < ROW
                && col >= 0 && col < COL
                && matrix[row][col] == 1
                && !visited[row][col];
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 1, 0},
                {1, 0, 1},
                {1, 0, 1}
        };
        int[][] grid2 = {
                {1, 1, 0},
                {1, 0, 1},
                {1, 0, 1}
        };

        IslandMatching islandMatching = new IslandMatching();
        System.out.println(islandMatching.countMatches(grid1, grid2));
    }

}
