package com.vivek.graph.examples;

import java.util.LinkedList;
import java.util.Queue;

/**
 Given a snake and ladder board, find the minimum number of dice throws required to reach the destination or last cell from source or 1st cell.
 Basically, the player has total control over outcome of dice throw and wants to find out minimum number of throws required to reach last cell.

 If the player reaches a cell which is base of a ladder, the player has to climb up that ladder and if reaches a cell is mouth of the snake,
 has to go down to the tail of snake without a dice throw.

 For example, consider the board shown, the minimum number of dice throws required to reach cell 30 from cell 1 is 3.
 Following are the steps:

 a) First throw two on dice to reach cell number 3 and then ladder to reach 22
 b) Then throw 6 to reach 28.
 c) Finally through 2 to reach 30.

 There can be other solutions as well like (2, 2, 6), (2, 4, 4), (2, 3, 5).. etc.

 Solution:

 The idea is to consider the given snake and ladder board as a directed graph with number of vertices equal to the number of cells in the board.
 The problem reduces to finding the shortest path in a graph.
 Every vertex of the graph has an edge to next six vertices if next 6 vertices do not have a snake or ladder.
 If any of the next six vertices has a snake or ladder, then the edge from current vertex goes to the top of the ladder or tail of the snake.
 Since all edges are of equal weight, we can efficiently find shortest path using Breadth First Search of the graph.

 References:
 1. For the question: https://www.geeksforgeeks.org/snake-ladder-problem-2/
 2. For the solution: http://theoryofprogramming.com/2014/12/25/snakes-and-ladders-game-code/
 3. For the code: https://algorithms.tutorialhorizon.com/snake-and-ladder-problem/

 */
public class SnakeAndLadderMinJumps {

    static int size = 36;

    private static class Vertex {
        int cell;
        int moves;
    }

    static int findMinMoves(int[] board) {
        // visited array for each cell
        boolean[] visited = new boolean[size];

        Queue<Vertex> queue = new LinkedList<>();

        // start from first cell
        Vertex vertex = new Vertex();
        vertex.cell = 0;
        vertex.moves = 0;
        queue.add(vertex);
        visited[0] = true;

        // BFS call
        while (!queue.isEmpty()) {
            vertex = queue.remove();
            int cell = vertex.cell;

            // check if we reached to the end
            if (cell == size - 1)
                break;

            // if not reached to the end then add the reachable adjacent cells from the current cells
            // Since dice can be controlled and max value can be achieved 6
            // so from the current cell, you can reach to next 6 cells so add next 6 cells
            for (int i = cell + 1; i < cell + 6 && i < size; i++) {
                // check if cell is not already visited
                if (!visited[i]) {
                    // update moves
                    Vertex current = new Vertex();
                    current.moves = vertex.moves + 1; // can be reached by throwing dice one more time
                    visited[i] = true;

                    // now fill the cell can be reached (might be snake or ladder)
                    current.cell = board[i] != -1 ? board[i] : i;

                    // add to queue
                    queue.add(current);
                }
            }
        }
        return vertex.moves;
    }

    public static void main(String[] args) {
        int[] board = new int[size];

        for (int i = 0; i < size; i++) {
            board[i] = -1;
        }

        // ladders
        board[2] = 15;
        board[14] = 24;
        board[20] = 31;

        // snakes
        board[11] = 1;
        board[29] = 3;
        board[34] = 21;

        System.out.println("Minimum number of dice throws required: " + findMinMoves(board));
    }

}
