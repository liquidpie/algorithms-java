package com.vivek.matrix;

/**
 * 289. Game of Life
 *
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by
 * the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or
 * dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the
 * following four rules (taken from the above Wikipedia article):
 *
 *     Any live cell with fewer than two live neighbors dies as if caused by under-population.
 *     Any live cell with two or three live neighbors lives on to the next generation.
 *     Any live cell with more than three live neighbors dies, as if by over-population.
 *     Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * The next state of the board is determined by applying the above rules simultaneously to every cell in the current
 * state of the m x n grid board. In this process, births and deaths occur simultaneously.
 *
 * Given the current state of the board, update the board to reflect its next state.
 *
 * Note that you do not need to return anything.
 *
 * Example 1:
 *   Input         Output
 *  0, 1, 0        0, 0, 0
 *  0, 0, 1   -->  1, 0, 1
 *  1, 1, 1        0, 1, 1
 *  0, 0, 0        0, 1, 0
 *
 *  Example 2:
 *  Input     Output
 *  1, 1       1, 1
 *  1, 0  -->  1, 1
 *
 * Approach 1: Using temporary matrix (not space optimized)
 * - Create a temporary matrix initialized with the board state
 * - Calculate live neighbors for each cell using temporary matrix
 * - Apply rules to each cell and update the original board
 *
 * Time Complexity: O(m x n)
 * Space Complexity: O(m x n)
 *
 * -----------------------------------------
 * Approach 2: In-place O(1) Space Solution
 * -----------------------------------------
 * The problem could also be solved in-place. O(M×N) space complexity could be too expensive when the board is very large.
 * We only have two states live(1) or dead(0) for a cell. We can use some dummy cell value to signify previous state of
 * the cell along with the new changed value.
 *
 * For e.g. If the value of the cell was 1 originally but it has now become 0 after applying the rule,
 * then we can change the value to -1. The negative sign signifies the cell is now dead(0)
 * but the magnitude signifies the cell was a live(1) cell originally.
 *
 * Also, if the value of the cell was 0 originally but it has now become 1 after applying the rule,
 * then we can change the value to 2.
 * The positive sign signifies the cell is now live(1) but the magnitude of 2 signifies the cell was a dead(0) cell originally.
 *
 * Algorithm
 *
 * 1. Iterate the cells of the Board one by one.
 * 2. The rules are computed and applied on the original board. The updated values signify both previous and updated value.
 * 3. The updated rules can be seen as this:
 *      a. Rule 1: Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 *      Hence, change the value of cell to -1. This means the cell was live before but now dead.
 *      b. Rule 2: Any live cell with two or three live neighbors lives on to the next generation. Hence, no change in the value.
 *      c. Rule 3: Any live cell with more than three live neighbors dies, as if by over-population.
 *      Hence, change the value of cell to -1. This means the cell was live before but now dead.
 *      Note that we don't need to differentiate between the rule 1 and 3. The start and end values are the same.
 *      Hence, we use the same dummy value.
 *      d. Rule 4: Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *      Hence, change the value of cell to 2. This means the cell was dead before but now live.
 * 4. Apply the new rules to the board.
 * 5. Since the new values give an indication of the old values of the cell, we accomplish the same results as approach 1 but without saving a copy.
 * 6. To get the Board in terms of binary values i.e. live(1) and dead(0), we iterate the board again and
 * change the value of a cell to a 1 if its value currently is greater than 0 and change the value to a 0
 * if its current value is lesser than or equal to 0.
 *
 * -------------
 * Follow up 2:
 * -------------
 * What would happen if the board is infinitely large? Can we still use the same solution that we saw earlier or
 * is there something else we will have to do different? If the board becomes infinitely large, there are multiple problems our current solution would run into:
 *
 * It would be computationally impossible to iterate a matrix that large.
 * It would not be possible to store that big a matrix entirely in memory. We have huge memory capacities these days i.e.
 * of the order of hundreds of GBs. However, it still wouldn't be enough to store such a large matrix in memory.
 * We would be wasting a lot of space if such a huge board only has a few live cells and the rest of them are all dead.
 * In such a case, we have an extremely sparse matrix and it wouldn't make sense to save the board as a "matrix".
 *
 * One aspect of the problem is addressed by a great solution provided by Stefan Pochmann. So as mentioned before,
 * it's quite possible that we have a gigantic matrix with a very few live cells. In that case it would be stupidity to save the entire board as is.
 *
 * If we have an extremely sparse matrix, it would make much more sense to actually save the location of only the live cells
 * and then apply the 4 rules accordingly using only these live cells.
 *
 * Essentially, we obtain only the live cells from the entire board and then apply the different rules using only the live cells
 * and finally we update the board in-place. The only problem with this solution would be when the entire board cannot fit into memory.
 * If that is indeed the case, then we would have to approach this problem in a different way.
 * For that scenario, we assume that the contents of the matrix are stored in a file, one row at a time.
 *
 * In order for us to update a particular cell, we only have to look at its 8 neighbors which essentially lie in the row above and below it.
 * So, for updating the cells of a row, we just need the row above and the row below.
 * Thus, we read one row at a time from the file and at max we will have 3 rows in memory.
 * We will keep discarding rows that are processed and then we will keep reading new rows from the file, one at a time.
 *
 * Reference:
 * https://leetcode.com/problems/game-of-life/description/
 */
public class GameOfLife {

    public static void main(String[] args) {
        {
            int[][] board = {
                    {0, 1, 0},
                    {0, 0, 1},
                    {1, 1, 1},
                    {0, 0, 0}
            };
            gameOfLifeInPlace(board);

            for (int[] row : board) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        }
        {
            int[][] board = {
                    {1, 1},
                    {1, 0}
            };
            gameOfLifeInPlace(board);

            for (int[] row : board) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Time Complexity: O(m x n)
     * Space Complexity: O(1)
     */
    static void gameOfLifeInPlace(int[][] board) {
        int m  = board.length;
        int n = board[0].length;

        // update the current state based on the rules
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = countLiveNeighbors2(board, i, j, m, n);
                if (board[i][j] == 1 && (count < 2 || count > 3)) { // Rule 1 or Rule 3
                    board[i][j] = -1;
                } else if (board[i][j] == 0 && count == 3) { // Rule 4
                    board[i][j] = 2;
                }
            }
        }

        // Get the final representation
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    /**
     * Uses a temporary matrix to store the state updates
     * Time Complexity: O(m x n)
     * Space Complexity: O(m x n)
     */
    static void gameOfLifeUsingExtraSpace(int[][] board) {
        int m  = board.length;
        int n = board[0].length;

        // use an auxiliary space
        int[][] temp = new int[m][n];

        // copy the current state to the auxiliary space
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = board[i][j];
            }
        }

        // update the current state based on the rules
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = countLiveNeighbors(temp, i, j, m, n);
                if (temp[i][j] == 1 && (count < 2 || count > 3)) { // Rule 1 or Rule 3
                    board[i][j] = 0;
                } else if (temp[i][j] == 0 && count == 3) { // Rule 4
                    board[i][j] = 1;
                }
            }
        }
    }

    private static int countLiveNeighbors(int[][] board, int i, int j, int m, int n) {
        int count = 0;
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int k = 0; k < dirs.length; k++) {
            if (i + dirs[k][0] >= 0 && j + dirs[k][1] >= 0 && i + dirs[k][0] < m && j + dirs[k][1] < n) {
                if (board[i + dirs[k][0]][j + dirs[k][1]] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countLiveNeighbors2(int[][] board, int i, int j, int m, int n) {
        int count = 0;
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int k = 0; k < dirs.length; k++) {
            if (i + dirs[k][0] >= 0 && j + dirs[k][1] >= 0 && i + dirs[k][0] < m && j + dirs[k][1] < n) {
                if (board[i + dirs[k][0]][j + dirs[k][1]] == 1 || board[i + dirs[k][0]][j + dirs[k][1]] == -1) {
                    count++;
                }
            }
        }
        return count;
    }

}
