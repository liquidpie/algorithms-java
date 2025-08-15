package com.vivek.graph.pattern.dfs;

/**
 * 130. Surrounded Regions
 *
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 *
 *     Connect: A cell is connected to adjacent cells horizontally or vertically.
 *     Region: To form a region connect every 'O' cell.
 *     Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells
 *     and none of the region cells are on the edge of the board.
 *
 * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
 *
 * Example 1:
 *
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 *
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *
 * Explanation:
 *
 * In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.
 *
 * Example 2:
 *
 * Input: board = [["X"]]
 *
 * Output: [["X"]]
 *
 * Solution:
 * https://leetcode.com/problems/surrounded-regions/solutions/691675/c-beginner-friendly-boundary-dfs-inplace/?envType=study-plan-v2&envId=top-interview-150
 *
 * We will use boundary DFS to solve this problem
 *
 *       Let's analyze when an 'O' cannot be flipped,
 *       if it has atleast one 'O' in it's adjacent, AND ultimately this chain of adjacent 'O's is connected to some 'O' which lies on boundary of board
 *
 *       consider these two cases for clarity :
 *
 *         O's won't be flipped          O's will be flipped
 *         [X O X X X]                   [X X X X X]
 *         [X O O O X]                   [X O O O X]
 *         [X O X X X]                   [X O X X X]
 *         [X X X X X]                   [X X X X X]
 *
 *       So we can conclude if a chain of adjacent O's is connected some O on boundary then they cannot be flipped
 *
 *  Steps to Solve:
 *  1. Move over the boundary of board, and find O's
 *  2. Every time we find an O, perform DFS from it's position
 *  3. In DFS convert all 'O' to '#'      (why?? so that we can differentiate which 'O' can be flipped and which cannot be)
 *  4. After all DFSs have been performed, board contains three elements,#,O and X
 *  5. 'O' are left over elements which are not connected to any boundary O, so flip them to 'X'
 *  6. '#' are elements which cannot be flipped to 'X', so flip them back to 'O'
 *
 * Reference: https://leetcode.com/problems/surrounded-regions
 */
public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = {
                {'O','X','X','O','X'}, 
                {'X','O','O','X','O'}, 
                {'X','O','X','O','X'}, 
                {'O','X','O','O','O'}, 
                {'X','X','O','X','O'}
        };

        SurroundedRegions helper = new SurroundedRegions();
        helper.solve(board);

        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++)
                System.out.print(chars[j] + " ");
            System.out.println();
        }
    }

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // Moving over first and last column
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                dfs(board, i, 0, m, n);
            if (board[i][n - 1] == 'O')
                dfs(board, i, n - 1, m, n);
        }

        // Moving over first and last row
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                dfs(board, 0, j, m, n);
            if (board[m - 1][j] == 'O')
                dfs(board, m - 1, j, m, n);
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O')
            return;

        board[i][j] = '#';

        dfs(board, i - 1, j, m, n);
        dfs(board, i + 1, j, m, n);
        dfs(board, i, j - 1, m, n);
        dfs(board, i, j + 1, m, n);
    }

}
