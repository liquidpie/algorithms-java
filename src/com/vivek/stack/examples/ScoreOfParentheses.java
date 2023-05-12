package com.vivek.stack.examples;

import java.util.Stack;

/**
 * Score of Parentheses
 *
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 *     "()" has score 1.
 *     AB has score A + B, where A and B are balanced parentheses strings.
 *     (A) has score 2 * A, where A is a balanced parentheses string.
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: 1
 *
 * Example 2:
 *
 * Input: s = "(())"
 * Output: 2
 *
 * Example 3:
 *
 * Input: s = "()()"
 * Output: 2
 *
 * https://leetcode.com/problems/score-of-parentheses/
 */
public class ScoreOfParentheses {

    public static void main(String[] args) {
        System.out.println(scoreOfParentheses("(())"));
    }

    public static int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int score = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
                score++;
                i++;
            } else if (s.charAt(i) == '(') {
                stack.push(score);
                score = 0;
            } else {
                score = 2 * score + stack.pop();
            }
        }
        return score;
    }

}
