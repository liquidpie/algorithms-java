package com.vivek.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Unique Paths
 *
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). T
 * he robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * Example 1:
 *
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * Example 2:
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 * https://stackoverflow.com/questions/64131960/robot-in-a-grid-how-to-get-all-possible-paths
 *
 * https://leetcode.com/problems/unique-paths
 */
public class UniquePathsPrintPath {

    static boolean isPathFound = false;

    public static void main(String[] args) {
        int[][] grid = {
            {1,0},
            {1,1}
        };

        int[][] grid2 = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        System.out.println(getPath(grid2));
    }

    public static List<Point> getPath(int[][] grid) {
        List<Point> path = new ArrayList<>();
        int m = grid.length;
        int n = grid[0].length;
        getPathUtil(grid, m - 1, n - 1, m, n, path);
        return path;
    }

    static void getPathUtil(int[][] grid, int i, int j, int m, int n, List<Point> path) {
        if (isPathFound)
            return;

        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0)
            return;

        if (i == 0 && i == j) {
            path.add(Point.of(i, j));
            isPathFound = true;
            return;
        }

        getPathUtil(grid, i, j - 1, m, n, path);
        getPathUtil(grid, i - 1, j, m, n, path);

        path.add(Point.of(i, j));
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Point of(int x, int y) {
            return new Point(x, y);
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

}
