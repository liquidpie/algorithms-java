package com.vivek.graph.examples;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Shortest Path in a Grid with Obstacles Elimination
 *
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 * Example 2:
 *
 *
 * Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
 * Output: -1
 * Explanation: We need to eliminate at least two obstacles to find such a walk.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 40
 * 1 <= k <= m * n
 * grid[i][j] is either 0 or 1.
 * grid[0][0] == grid[m - 1][n - 1] == 0
 *
 * https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 */
public class ShortestPathInGridWithObstacleElimination {

    private static final int[] ROWS = {-1, 1, 0, 0};
    private static final int[] COLS = {0, 0, -1, 1};

    public static void main(String[] args) {
        int[][] grid = {{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        int k = 1;
        System.out.println(shortestPath(grid, k));
    }

    static int shortestPath(int[][] grid, int k) {
        int rowLength = grid.length;
        int colLength = grid[0].length;

        boolean[][][] visited = new boolean[rowLength][colLength][k + 1];
        visited[0][0][0] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        int minSteps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] elem = queue.poll();
                int row = elem[0];
                int col = elem[1];
                int currObstacle = elem[2];

                if (row == rowLength - 1 && col == colLength - 1)
                    return minSteps;

                for (int j = 0; j < 4; j++) {
                    int newRow = row + ROWS[j];
                    int newCol = col + COLS[j];
                    int nextObstacle = currObstacle;

                    if (newRow >= 0 && newRow < rowLength && newCol >= 0 && newCol < colLength) {
                        if (grid[newRow][newCol] == 1)
                            nextObstacle++;
                        if (nextObstacle <= k && !visited[newRow][newCol][nextObstacle]) {
                            visited[newRow][newCol][nextObstacle] = true;
                            queue.add(new int[] {newRow, newCol, nextObstacle});
                        }
                    }
                }
            }
            minSteps++;
        }

        return -1;

    }

}
