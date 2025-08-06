package com.vivek.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * 36. Valid Sudoku
 *
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 *     Each row must contain the digits 1-9 without repetition.
 *     Each column must contain the digits 1-9 without repetition.
 *     Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 *
 *     A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 *     Only the filled cells need to be validated according to the mentioned rules.
 *
 * Example 1:
 *
 * Input: board =
 * [['5','3','.','.','7','.','.','.','.']
 * ,['6','.','.','1','9','5','.','.','.']
 * ,['.','9','8','.','.','.','.','6','.']
 * ,['8','.','.','.','6','.','.','.','3']
 * ,['4','.','.','8','.','3','.','.','1']
 * ,['7','.','.','.','2','.','.','.','6']
 * ,['.','6','.','.','.','.','2','8','.']
 * ,['.','.','.','4','1','9','.','.','5']
 * ,['.','.','.','.','8','.','.','7','9']]
 * Output: true
 *
 * Example 2:
 *
 * Input: board =
 * [['8','3','.','.','7','.','.','.','.']
 * ,['6','.','.','1','9','5','.','.','.']
 * ,['.','9','8','.','.','.','.','6','.']
 * ,['8','.','.','.','6','.','.','.','3']
 * ,['4','.','.','8','.','3','.','.','1']
 * ,['7','.','.','.','2','.','.','.','6']
 * ,['.','6','.','.','.','.','2','8','.']
 * ,['.','.','.','4','1','9','.','.','5']
 * ,['.','.','.','.','8','.','.','7','9']]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8.
 * Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 * Solution:
 * https://leetcode.com/problems/valid-sudoku/solutions/15472/short-simple-java-using-strings/?envType=study-plan-v2&envId=top-interview-150
 *
 * Collect the set of things we see, encoded as strings. For example:
 *
 *     '4' in row 7 is encoded as "(4)7".
 *     '4' in column 7 is encoded as "7(4)".
 *     '4' in the top-right block is encoded as "0(4)2".
 *
 * Scream false if we ever fail to add something because it was already added (i.e., seen before).
 *
 * Reference:
 * https://leetcode.com/problems/valid-sudoku/
 */
public class ValidSudoku {

    public static void main(String[] args) {
        ValidSudoku helper = new ValidSudoku();

        {
            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };
            System.out.println(helper.isValidSudoku(board));
        }

        {
            char[][] board = {
                    {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };
            System.out.println(helper.isValidSudoku(board));
        }
    }

    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    String s = "(" + num + ")";
                    if (!set.add(i + s) ||
                            !set.add(s + j) ||
                            !set.add(i/3 + s + j/3))
                        return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSudokuV1(char[][] board) {

        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                Set<Character> set = new HashSet<>();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        char num = board[i + k][j + l];
                        if (set.contains(num))
                            return false;
                        if (num != '.')
                            set.add(num);
                    }
                }
            }
        }


        for (int i = 0; i < 9; i++) {
            Set<Character> rowSet = new HashSet<>();
            Set<Character> colSet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char rowwise = board[i][j];
                char colwise = board[j][i];
                if (rowSet.contains(rowwise))
                    return false;
                if (colSet.contains(colwise))
                    return false;
                if (rowwise != '.')
                    rowSet.add(rowwise);
                if (colwise != '.')
                    colSet.add(colwise);
            }
        }

        return true;
    }

}
