package com.vivek.graph.pattern.dfs;

/**
 * 463. Island Perimeter
 *
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1.
 * The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 *
 * Example 2:
 *
 * Input: grid = [[1]]
 * Output: 4
 *
 * Example 3:
 *
 * Input: grid = [[1,0]]
 * Output: 4
 *
 * Reference: https://leetcode.com/problems/island-perimeter
 */
public class IslandPerimeter {

    private int perimeter = 0;

    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    dfs(grid, visited, i, j, m, n);
            }
        }

        return perimeter;
    }

    private void dfs(int[][] grid, boolean[][] visited, int i, int j, int m, int n) {
        if (visited[i][j])
            return;

        visited[i][j] = true;

        int[][] dir = { {0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int startCellPerimeter = 4;
        for (int k = 0; k < 4; k++) {
            int i1 = i + dir[k][0];
            int j1 = j + dir[k][1];

            if (isSafe(i1, j1, m, n) && grid[i1][j1] == 1) {
                startCellPerimeter--;
                dfs(grid, visited, i1, j1, m, n);
            }
        }
        perimeter += startCellPerimeter;
    }

    private boolean isSafe(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    /**
     * Solution:
     * https://leetcode.com/problems/island-perimeter/solutions/5039036/faster-lesser-2-methods-detailed-approach-counting-dfs-python-java-c/?envType=problem-list-v2&envId=xb7kzh6j
     *
     */
    public int islandPerimeter2(int[][] grid) {
        int perimeter = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;
                    if (i > 0 && grid[i - 1][j] == 1)
                        perimeter -= 2;
                    if (j > 0 && grid[i][j - 1] == 1)
                        perimeter -= 2;
                    // why only left and top neighbours are considered?
                    // because right neighbours and down neighbours are not visited yet in iterative approach
                    // so need to check them we will visit them in coming iterations,thats why we are checking
                    // if the previously visited cells have something common with present cell
                }
            }
        }
        return perimeter;
    }

    public static void main(String[] args) {
        IslandPerimeter helper = new IslandPerimeter();
        int[][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };
        System.out.println(helper.islandPerimeter2(grid));
    }

}
