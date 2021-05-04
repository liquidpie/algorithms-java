package com.vivek.backtracking;

import java.util.Arrays;

/**
 * Given a N*N board with the Knight placed on the first block of an empty board.
 * Moving according to the rules of chess knight must visit each square exactly once. Print the order of each the cell in which they are visited.
 *
 * Example:
 *
 * Input :
 * N = 8
 * Output:
 * 0  59  38  33  30  17   8  63
 * 37  34  31  60   9  62  29  16
 * 58   1  36  39  32  27  18   7
 * 35  48  41  26  61  10  15  28
 * 42  57   2  49  40  23   6  19
 * 47  50  45  54  25  20  11  14
 * 56  43  52   3  22  13  24   5
 * 51  46  55  44  53   4  21  12
 *
 *
 * Backtracking Algorithm for Knight’s tour
 *
 * Following is the Backtracking algorithm for Knight’s tour problem.
 *
 * If all squares are visited
 *     print the solution
 * Else
 *    a) Add one of the next moves to solution vector and recursively
 *    check if this move leads to a solution. (A Knight can make maximum
 *    eight moves. We choose one of the 8 moves in this step).
 *    b) If the move chosen in the above step doesn't lead to a solution
 *    then remove this move from the solution vector and try other
 *    alternative moves.
 *    c) If none of the alternatives work then return false (Returning false
 *    will remove the previously added item in recursion and if false is
 *    returned by the initial call of recursion then "no solution exists" )
 *
 *
 * Reference: https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
 * https://www.codesdope.com/course/algorithms-knights-tour-problem/
 */
public class KnightsTour {

    private static final int N = 8;

    static void solveKnightsTour() {
        int[][] sol = new int[N][N];

        for (int[] ints : sol)
            Arrays.fill(ints, -1);

        // xMoves[] and yMoves[] define next move of Knight. xMoves[] is for next value of x coordinate and yMoves[] is for next value of y coordinate
        int[] xMoves = {2,  2, 1, -1, -2, -2,  1, -1};
        int[] yMoves = {1, -1, 2,  2,  1, -1, -2, -2};

        // Since the Knight is initially at the first block
        sol[0][0] = 0;

        if (!solveKnightsTourUtil(0, 0, 1, sol, xMoves, yMoves)) {
            System.out.println("Solution doesn't exist!");
        } else {
            print(sol);
        }
    }

    /* A recursive utility function to solve Knight Tour problem */
    private static boolean solveKnightsTourUtil(int x, int y, int move, int[][] sol, int[] xMoves, int[] yMoves) {

        if (move == (N * N))
            return true;

        // Try all next moves from the current coordinate x, y
        for (int k = 0; k < 8; k++) {
            int nextX = x + xMoves[k];
            int nextY = y + yMoves[k];

            if (isSafe(nextX, nextY, sol)) {
                sol[nextX][nextY] = move;

                if (solveKnightsTourUtil(nextX, nextY, move + 1, sol, xMoves, yMoves))
                    return true;
                else {
                    // backtrack
                    sol[nextX][nextY] = -1;
                }
            }
        }
        return false;

    }

    private static boolean isSafe(int x, int y, int[][] sol) {
        return x >= 0 && x < N &&
                y >= 0 && y < N &&
                sol[x][y] == -1;
    }

    private static void print(int[][] sol) {
        for (int[] ints: sol) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void main(String[] args) {
        solveKnightsTour();
    }

}
