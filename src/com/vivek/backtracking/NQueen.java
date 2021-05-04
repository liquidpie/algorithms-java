package com.vivek.backtracking;

import java.util.Arrays;

/**
 * The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other.
 *
 * The expected output is a binary matrix which has 1s for the blocks where queens are placed.
 * For example, following is the output matrix for 4 queen solution.
 *
 *               { 0,  1,  0,  0}
 *               { 0,  0,  0,  1}
 *               { 1,  0,  0,  0}
 *               { 0,  0,  1,  0}
 *
 * Backtracking Algorithm:
 * The idea is to place queens one by one in different columns, starting from the leftmost column.
 * When we place a queen in a column, we check for clashes with already placed queens.
 * In the current column, if we find a row for which there is no clash, we mark this row and column as part of the solution.
 * If we do not find such a row due to clashes then we backtrack and return false.
 *
 * 1) Start in the leftmost column
 * 2) If all queens are placed
 *     return true
 * 3) Try all rows in the current column.
 *    Do following for every tried row.
 *     a) If the queen can be placed safely in this row
 *        then mark this [row, column] as part of the
 *        solution and recursively check if placing
 *        queen here leads to a solution.
 *     b) If placing the queen in [row, column] leads to
 *        a solution then return true.
 *     c) If placing queen doesn't lead to a solution then
 *        unmark this [row, column] (Backtrack) and go to
 *        step (a) to try other rows.
 * 3) If all rows have been tried and nothing worked,
 *    return false to trigger backtracking.
 *
 * Reference: https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
 */
public class NQueen {

    private final int n;

    public NQueen(int n) {
        this.n = n;
    }

    void solveNQ() {
        int[][] board = new int[n][n];

        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution doesn't exist!");
        }
        else {
            print(board);
        }
    }

    private boolean solveNQUtil(int[][] board, int col) {
        if (col >= n)
            return true;

        for (int row = 0; row < n; row++) {
            if (isSafe(row, col, board)) {
                board[row][col] = 1;

                if (solveNQUtil(board, col + 1))
                    return true;

                board[row][col] = 0;
            }
        }

        return false;
    }

    private boolean isSafe(int row, int col, int[][] board) {

        // check this row on left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        // check the upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // check the lower diagonal on left side
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    private void print(int[][] board) {
        for (int[] ints : board)
            System.out.println(Arrays.toString(ints));
    }

    public static void main(String[] args) {
        NQueen nQueen = new NQueen(4);

        nQueen.solveNQ();
    }

}
