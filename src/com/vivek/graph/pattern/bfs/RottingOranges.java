package com.vivek.graph.pattern.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 *
 * You are given an m x n grid where each cell can have one of three values:
 *
 *     0 representing an empty cell,
 *     1 representing a fresh orange, or
 *     2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 *
 * Example 1:
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 *
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 *
 * Example 3:
 *
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 * Reference: https://leetcode.com/problems/rotting-oranges
 */
public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int mins = 0;

        int[][] adj = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<Cell> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2)
                    queue.add(new Cell(i, j));
            }
        }

        while (!queue.isEmpty()) {
            int qSize = queue.size();

            for (int i = 0; i < qSize; i++) {
                Cell curr = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int x1 = curr.x + adj[j][0];
                    int y1 = curr.y + adj[j][1];
                    if (x1 < m && x1 >= 0 && y1 < n && y1 >= 0 && grid[x1][y1] == 1) {
                        grid[x1][y1] = 2;
                        queue.add(new Cell(x1, y1));
                    }
                }
            }

            mins++;
        }


        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 1)
                    return -1;
            }
        }

        return mins == 0 ? 0 : mins - 1;

    }

    static class Cell {
        int x;
        int y;

        Cell(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

}
