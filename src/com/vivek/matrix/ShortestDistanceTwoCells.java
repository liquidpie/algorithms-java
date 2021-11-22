package com.vivek.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Shortest distance between two cells in a matrix or grid
 *
 * Given a matrix of N*M order. Find the shortest distance from a source cell to a destination cell,
 * traversing through limited cells only. Also you can move only up, down, left and right. If found output the distance else -1.
 *
 * Input : {'0', '*', '0', 's'},
 *         {'*', '0', '*', '*'},
 *         {'0', '*', '*', '*'},
 *         {'d', '*', '*', '*'}
 * Output : 6
 *
 * Input :  {'0', '*', '0', 's'},
 *          {'*', '0', '*', '*'},
 *          {'0', '*', '*', '*'},
 *          {'d', '0', '0', '0'}
 * Output :  -1
 *
 * Approach:
 * The idea is to BFS (breadth first search) on matrix cells. Note that we can always use BFS to find shortest path if graph is unweighted.
 *
 *   - Store each cell as a node with their row, column values and distance from source cell.
 *   - Start BFS with source cell.
 *   - Make a visited array with all having “false” values except ‘0’cells which are assigned “true” values as they can not be traversed.
 *   - Keep updating distance from source value in each move.
 *   - Return distance when destination is met, else return -1 (no path exists in between source and destination).
 *
 * Reference:
 * https://www.geeksforgeeks.org/shortest-distance-two-cells-matrix-grid/
 * https://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
 */
public class ShortestDistanceTwoCells {

    static int minDistance(char[][] grid) {
        Cell source = new Cell(0, 0, 0);

        // Finding source
        firstLoop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 's') {
                    source.row = i;
                    source.col = j;
                    break firstLoop;
                }
            }
        }

        int[] movesX = {-1, 1, 0, 0};
        int[] movesY = {0, 0, -1, 1};

        // apply BFS
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[source.row][source.col] = true;

        Queue<Cell> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            // destination found
            if (grid[cell.row][cell.col] == 'd') {
                return cell.dist;
            }

            for (int k = 0; k < 4; k++) {
                int x = cell.row + movesX[k];
                int y = cell.col + movesY[k];
                if (isSafe(x, y, grid, visited)) {
                    queue.add(new Cell(x, y, cell.dist + 1));
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }

    private static boolean isSafe(int x, int y, char[][] grid, boolean[][] visited) {
        return x >= 0 && x < grid.length &&
                y >= 0 && y < grid[0].length &&
                grid[x][y] != '0' &&
                !visited[x][y];
    }

    public static void main(String[] args) {
        char[][] grid = {
                { '0', '*', '0', 's' },
                { '*', '0', '*', '*' },
                { '0', '*', '*', '*' },
                { 'd', '*', '*', '*' }
        };
        System.out.println(minDistance(grid));
    }

    static class Cell {
        int row;
        int col;
        int dist;

        Cell(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}
