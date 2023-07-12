package com.vivek.stack.examples;

import java.util.Stack;

/**
 * Basic Calculator II
 *
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Constraints:
 *
 * - s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * - s represents a valid expression.
 * - The answer is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 * Solution: https://blog.baozitraining.org/2019/08/leetcode-solution-227-basic-calculator.html
 *
 * This problem is very similar to Basic Calculator. The difference is there is no parenthesis in this one, but there is * and / .
 * Similar to the "Use the sign method with one stack" in Basic Calculator.
 * The idea is in the first pass, only calculate "*" and "/" and push the values into the stack,
 * then have a 2nd pass to do the "+" and "-" calculations.
 *
 * Time Complexity: O(N), N is the length of the string
 * Space Complexity: O(N), extra stack is needed
 *
 * https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {

    static int calculate(String s) {
        int n = s.length();
        char expr = '+';
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(i) && s.charAt(i) != ' ') || i == n - 1) {
                if (expr == '+') {
                    stack.push(num);
                } else if (expr == '-') {
                    stack.push(num);
                } else if (expr == '*') {
                    stack.push(stack.pop() * num);
                } else if (expr == '/') {
                    stack.push(stack.pop() / num);
                }
                expr = s.charAt(i);
                num = 0;
            }
        }

        int result = 0;
        for (int i : stack) {
            result += i;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = " 3+5 / 2 ";
        System.out.println(calculate(s));
    }

}
