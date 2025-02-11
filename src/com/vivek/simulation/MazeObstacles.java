package com.vivek.simulation;

import java.util.*;

/**
 * You are given a binary matrix (m * n) and an integer K.
 * 0 in the binary matrix represents that you can go to that cell and 1 represents that you can't.
 * You have to go from (0, 0) to (m-1, n-1).
 *
 * You can only go to the cells which has 0 in it.
 * You can move in 4 directions and in one move you can go upto K steps in that direction if there is no obstacle(1) on that path.
 *
 * Find the minimum number of moves to reach the end of the matrix.
 *
 * Time Complexity: O(m × n × K) in the worst case.
 * Space Complexity: O(m × n) due to the visited set and queue.
 */
public class MazeObstacles {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {1, 0, 0, 0}
        };
        int k = 1;
        System.out.println(minMoves(grid, k)); // Output: 6
    }

    static int minMoves(int[][] grid, int k) {
        int[][] dirs = { {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int m = grid.length;
        int n = grid[0].length;

        Set<Point> visited = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 0, 0));
        visited.add(new Point(0, 0));

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.row == m - 1 && current.col == n - 1)
                return current.moves;

            for (int[] dir : dirs) {
                int steps = 0;
                int nextRow = current.row;
                int nextCol = current.col;
                while (steps < k && isSafe(grid, m, n, dir, current)) {
                    nextRow += dir[0];
                    nextCol += dir[1];
                    if (!visited.contains(new Point(nextRow, nextCol))) {
                        queue.add(new Position(nextRow, nextCol, current.moves + 1));
                    }
                    steps++;
                }
            }
        }

        return -1;
    }

    private static boolean isSafe(int[][] grid, int m, int n, int[] dir, Position current) {
        return (current.row + dir[0]) >= 0 && (current.row + dir[0]) < m
                && (current.col + dir[1]) >= 0 && (current.col + dir[1]) < n
                && grid[current.row + dir[0]][current.col + dir[1]] == 0;
    }

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point point)) return false;
            return row == point.row && col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    static class Position {
        int row;
        int col;
        int moves;

        public Position(int row, int col, int moves) {
            this.row = row;
            this.col = col;
            this.moves = moves;
        }
    }

}
