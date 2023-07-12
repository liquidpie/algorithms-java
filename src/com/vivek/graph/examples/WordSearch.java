package com.vivek.graph.examples;

/**
 * Word Search
 *
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example 1:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 *
 * Example 2:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 *
 * Example 3:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 * Solution: https://tutorialcup.com/leetcode-solutions/word-search-leetcode-solution.htm
 *
 * Approach ( Backtracking )
 * This is a 2D grid traversal problem, where we have to explore the grid to check if the given word can be formed using adjacent cells of the grid.
 * But instead of performing DFS on whole grid space we would more optimally use backtracking method.
 * In backtracking method we will only go to that path to find the solution which matches our aim.
 * If we know at some point that the current path will not lead to the solution then we will backtrack and go for the next choice.
 *
 * We will go around the grid by marking the current cell in our path as visited at each step.
 * And at end of each step we will also unmark it so that we could have a clean state to try another direction.
 *
 * Algorithm
 * We would make a backtracking function which will start with a particular cell and traverse the adjacent cells of grid in DFS fashion.
 * Because the given word can start from anywhere in the grid,
 * we would loop over all the cells of the grid and for each cell we will call the backtracking function starting from this current cell.
 * As this backtracking function is a recursive function, below are the steps to implement this recursive function:
 *
 * In the beginning we will check if we have reached to the bottom or the base case of the recursion.
 * If the word to be searched is empty or in other words if it’s found, we return true.
 * We check if our path is currently valid or not by checking if we have crossed the boundary of the grid or if the current cell matches the first character of the search word or not.
 * If the current step is valid then mark this cell as visited.
 * And start exploring the four directions by calling the same backtracking function for right, down, left and up cells.
 * At end we un-visit the current cell and return the result of exploration as true or false.
 * If any of the sub exploration results in true then we return true from here otherwise return false.
 *
 * Time Complexity ::
 * O( N*(3^L) ) : where N is the total number of cells in the grid and L is the length of the given word to be searched.
 * For the backtracking function initially we get 4 choices for directions but further it reduced to 3 as we have already visited it in previous step.
 * This depth of this recursive function will be equal to the length of the word (L).
 * Hence in worst case total number of function invocation will be the number of nodes in 3-nary tree, which is about 3^L.
 * In worst case we call this function starting from N number of cells. Hence overall time complexity will be O( N*(3^L) ).
 *
 * Space Complexity ::
 * O(L) : where L is the length of the given word. This space is used for recursion stack.
 *
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {

    private static final int[] ROWS = {-1, 1, 0, 0};
    private static final int[] COLS = {0, 0, 1, -1};

    static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, 0))
                    return true;
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int i, int j, String word, int index) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1)
            return false;

        if (board[i][j] != word.charAt(index))
            return false;

        if (index == word.length() - 1)
            return true;

        char temp = board[i][j];
        board[i][j] = '*';

        for (int k = 0; k < 4; k++) {
            if (dfs(board, i + ROWS[k], j + COLS[k], word, index + 1))
                return true;
        }

        board[i][j] = temp;
        return false;

    }

    public static void main(String[] args) {
        char[][] board= {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCCED";
        System.out.println(exist(board,word));
    }

}
