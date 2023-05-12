package com.vivek.stack.examples;

import java.util.Stack;

/**
 * Minimum Remove to Make Valid Parentheses
 *
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 *     It is the empty string, contains only lowercase characters, or
 *     It can be written as AB (A concatenated with B), where A and B are valid strings, or
 *     It can be written as (A), where A is a valid string.
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 *
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class MinRemoveValidParentheses {

    public static void main(String[] args) {
        String s = "lee(t(c)od(e";
        System.out.println(minRemoveParenthesesToMakeValid(s));
    }

    public static String minRemoveParenthesesToMakeValid(String s) {
        char[] sArray = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < sArray.length; i++) {
            if (sArray[i] == '(') {
                stack.push(i);
            } else if (sArray[i] == ')') {
                if (!stack.empty()) {
                    stack.pop();
                } else {
                    sArray[i] = '_';
                }
            }
        }

        while (!stack.isEmpty()) {
            sArray[stack.pop()] = '_';
        }

        StringBuilder builder = new StringBuilder();

        for (char c : sArray) {
            if (c != '_') {
                builder.append(c);
            }
        }

        return builder.toString();
    }

}
