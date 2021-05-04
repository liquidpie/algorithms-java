package com.vivek.backtracking;

import java.util.Arrays;

/**
 * A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e., maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1]. A rat starts from source and has to reach the destination. The rat can move only in two directions: forward and down.
 *
 * In the maze matrix, 0 means the block is a dead end and 1 means the block can be used in the path from source to destination. Note that this is a simple version of the typical Maze problem. For example, a more complex version can be that the rat can move in 4 directions and a more complex version can be with a limited number of moves.
 *
 * Following is a binary matrix representation of the maze.
 *
 * {1, 0, 0, 0}
 * {1, 1, 0, 1}
 * {0, 1, 0, 0}
 * {1, 1, 1, 1}
 *
 * Following is the solution matrix (output of program) for the above input matrix.
 *
 * {1, 0, 0, 0}
 * {1, 1, 0, 0}
 * {0, 1, 0, 0}
 * {0, 1, 1, 1}
 * All entries in solution path are marked as 1.
 *
 * Approach: Form a recursive function, which will follow a path and check if the path reaches the destination or not. If the path does not reach the destination then backtrack and try other paths.
 *
 * Algorithm:
 *
 *     1. Create a solution matrix, initially filled with 0’s.
 *     2. Create a recursive function, which takes initial matrix, output matrix and position of rat (i, j).
 *     3. if the position is out of the matrix or the position is not valid then return.
 *     4. Mark the position output[i][j] as 1 and check if the current position is destination or not. If destination is reached print the output matrix and return.
 *     5. Recursively call for position (i+1, j) and (i, j+1).
 *     6. Unmark position (i, j), i.e output[i][j] = 0.
 *
 * Reference: https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
 */
public class RatMaze {

    static void solveMaze(int[][] maze, int n) {
        int[][] sol = new int[n][n];

        if (!solveMazeUtil(maze, 0, 0, sol, n)) {
            System.out.println("Solution doesn't exist");
        }

        print(sol);
    }

    private static boolean solveMazeUtil(int[][] maze, int x, int y, int[][] sol, int n) {
        // if (x, y is goal) return true
        if (x == n - 1 && y == n - 1 && maze[x][y] == 1) {
            sol[x][y] = 1;
            return true;
        }

        // Check if maze[x][y] is valid
        if (isSafe(maze, x, y, n)) {
            // Check if the current block is already part of solution path
            if (sol[x][y] == 1)
                return false;

            // mark x, y as part of solution path
            sol[x][y] = 1;

            // Move forward in x direction
            if (solveMazeUtil(maze, x + 1, y, sol, n))
                return true;

            // If moving in x direction doesn't give solution then Move down in y direction
            if (solveMazeUtil(maze, x, y + 1, sol, n))
                return true;

            // If moving in y direction doesn't give solution then Move backwards in x direction
            if (solveMazeUtil(maze, x - 1, y, sol, n))
                return true;

            // If moving backwards in x direction doesn't give solution then Move upwards in y direction
            if (solveMazeUtil(maze, x, y - 1, sol, n))
                return true;

            // If none of the above movements works then BACKTRACK: unmark x, y as part of solution path
            sol[x][y] = 0;
            return false;
        }

        return false;
    }

    private static boolean isSafe(int[][] maze, int x, int y, int n) {
        return (x >= 0 && x < n &&
                y >= 0 && y < n &&
                maze[x][y] == 1);
    }

    private static void print(int[][] sol) {
        for (int[] ints : sol) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

        solveMaze(maze, maze.length);

    }

}
