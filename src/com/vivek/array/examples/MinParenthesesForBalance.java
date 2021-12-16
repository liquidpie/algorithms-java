package com.vivek.array.examples;

/**
 * Minimum number of Parentheses to be added to make it valid
 *
 * Given a string S of parentheses ‘(‘ or ‘)’. The task is to find a minimum number of parentheses ‘(‘ or ‘)’ (at any positions)
 * we must add to make the resulting parentheses string is valid.
 * Examples:
 *
 * Input: str = "())"
 * Output: 1
 * One '(' is required at beginning.
 *
 * Input: str = "((("
 * Output: 3
 * Three ')' is required at end.
 *
 * Approach: We keep the track of balance of the string i:e the number of ‘(‘ minus the number of ‘)’. A string is valid if its balance is 0, and also every prefix has non-negative balance.
 * Now, consider the balance of every prefix of S. If it is ever negative (say, -1), we must add a ‘(‘ bracket at the beginning. Also, if the balance of S is positive (say, +P), we must add P times ‘)’ brackets at the end.
 *
 * Reference: https://www.geeksforgeeks.org/minimum-number-of-parentheses-to-be-added-to-make-it-valid/
 */
public class MinParenthesesForBalance {

    static int minParentheses(String expr) {
        int bal = 0;
        for (int i = 0; i < expr.length(); i++) {
            bal += expr.charAt(i) == '(' ? 1 : -1;
        }

        return Math.abs(bal);
    }

    public static void main(String[] args) {
        System.out.println(minParentheses("))"));
    }

}
