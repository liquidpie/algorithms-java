package com.vivek.graph.examples;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Distance of nearest cell having 1 in a binary matrix
 *
 * Given a binary matrix of N x M, containing at least a value 1. The task is to find the distance of the nearest 1 in the matrix for each cell.
 * The distance is calculated as |i1 – i2| + |j1 – j2|, where i1, j1 are the row number and column number of the current cell,
 * and i2, j2 are the row number and column number of the nearest cell having value 1
 *
 * Examples:
 *
 *     Input: N = 3, M = 4
 *     mat[][] = {
 *                  {0, 0, 0, 1},
 *                  {0, 0, 1, 1},
 *                  {0, 1, 1, 0}
 *              }
 *     Output:
 *     3 2 1 0
 *     2 1 0 0
 *     1 0 0 1
 *     Explanation: For cell at (0, 0), nearest 1 is at (0, 3).
 *     So distance = (0 – 0) + (3 – 0) = 3.
 *     Similarly, all the distance can be calculated
 *
 *     Input: N = 3, M = 3
 *     mat[][] = {
 *                  {1, 0, 0},
 *                  {0, 0, 1},
 *                  {0, 1, 1}
 *               }
 *     Output:
 *     0 1 1
 *     1 1 0
 *     1 0 0
 *     Explanation:  For cell at (0, 1), nearest 1 is at (0, 0).
 *     So distance is 1. Similarly, all the distance can be calculated.
 *
 *
 * Approach ::
 *
 * Distance of nearest cell having 1 in a binary matrix using the BFS method using same grid (given matrix):
 * To solve the problem follow the below idea:
 *
 * The idea is to use multisource Breadth-First Search.First we need to mark all the initial 1s present in given matrix
 * by storing there index(i,j) in queue<pair<int,int>>,we also know that these 1s are the starting point (meaning
 * from their index distance will be calculated.) so we make all these 1s to 0, then we can also say that all the 0s
 * present initially in matrix will soon replaced by distance so we assume the distance as infinite,so we make all 0s
 * present in initial matrix as INT_MAX.
 * eg:
 *     n = 3 ,m=4          (∞ = INT_MAX)                           _____________________________________
 *     0 1 0 0             ∞ 0 ∞ ∞                              |       |      |         |       |
 *     1 1 0 0     ----->  0 0 ∞ ∞                  queue<int,int> | {0,1} |{1,1} | {2,2} | {2,3}    |
 *     0 0 1 1             ∞ ∞ 0 0                                 |_______|______|_______|_______|__________
 *
 * Follow the given steps to solve the problem:
 *
 *  NOTE: grid[ i ][ j ]==0 means this is the starting point and grid[ i ][ j ]==INT_MAX is imaginary distance.
 *  this concept will be used for visited and unvisited purposes. Only grid[ i ][ j ]==INT_MAX will be treated as unvisited,
 *  everything else will be treated as visited.
 *
 *     Create a queue<pair<int,int>> q
 *     Traverse the matrix and do two tasks if (grid[i][j]==1) { q.push( { i , j } ); grid[ i ][ j ] = 0; }    else { grid[ i ][ j ] = INT_MAX; }
 *     Now do a BFS traversal of the graph using the above-created queue.
 *     Run while loop till the size of the queue is greater than 0 then extract the front node of the queue and
 *     remove it and insert all its valid adjacent nodes if (grid[ i ][ j ] = INT_MAX) // it means this node is unvisited till now.
 *
 * Time Complexity: O(N*M).
 * Auxiliary Space: O(M*N)
 *
 * https://www.geeksforgeeks.org/distance-nearest-cell-1-binary-matrix/
 */
public class NearestOneDistanceBinaryMatrix {

    public static void main(String[] args) {
        int[][] mat = {
                 {0, 0, 0, 1},
                 {0, 0, 1, 1},
                 {0, 1, 1, 0}
             };

        nearestHavingOne(mat);

        System.out.println(Arrays.deepToString(mat)
                                .replace("], ", "]\n"));
    }

    static int[][] nearestHavingOne(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<Node> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    queue.add(new Node(i, j));
                } else {
                    grid[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // directions
        int[] rows = {0, 0, -1, 1};
        int[] cols = {-1, 1, 0, 0};

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int base = grid[node.x][node.y];

            for (int i = 0; i < 4; i++) {
                int x1 = node.x + rows[i];
                int y1 = node.y + cols[i];
                if (isSafe(x1, y1, n, m) && grid[x1][y1] == Integer.MAX_VALUE) {
                    grid[x1][y1] = base + 1;
                    queue.add(new Node(x1, y1));
                }
            }
        }
        return grid;
    }

    static boolean isSafe(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
