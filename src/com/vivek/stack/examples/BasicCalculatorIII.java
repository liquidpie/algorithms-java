package com.vivek.stack.examples;

import java.util.Stack;

/**
 * Basic Calculator III
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Constraints:
 *     s consists of digits, '+', '-', '*', '/', '(', and ')'.
 *     s is a valid expression.
 *
 * Example 1:
 *
 * Input: s = "1+1"
 * Output: 2
 *
 * Example 2:
 *
 * Input: s = "6-4/2"
 * Output: 4
 *
 * Example 3:
 *
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 *
 * Solution:
 *
 * This is an extension problem to Basic Calculator and Basic Calculator II.
 * The differences are this time it really looks like a real calculator where it can do "+-/*" and with brackets.
 * It's still the same thought process with exactly the same idea before: having two stacks,
 * one stack for the operator and the other for the operands,
 * and with exactly the same calculating preferences before: calculate * and / before + and -.
 * When seeing right bracket, continue popping till you see the left bracket
 *
 * Time Complexity: O(N), N is the length of the string
 * Space Complexity: O(N), extra stack is needed
 *
 * https://blog.baozitraining.org/2019/08/leetcode-solution-772-basic-calculator.html
 *
 * https://leetcode.com/problems/basic-calculator-iii/
 */
public class BasicCalculatorIII {

    static int calculate(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("invalid input");
        }

        s = s.trim();
        int i = 0;

        Stack<Long> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder number = new StringBuilder(); // deal with non single digit numbers

        while (i < s.length()) {
            char c = s.charAt(i);

            if (c == ' ') {
                i++;
                continue;
            }

            if (Character.isDigit(c)) {
                number.append(c);
            } else if (isValidOperator(c)) {
                if (number.length() != 0) {
                    operands.push(Long.parseLong(number.toString()));
                    number = new StringBuilder();
                }

                // Basically based on different priority of operators
                if (operators.isEmpty()) {
                    // it says it only contains non-negative integer, but now we have "-1 + 2", "-(2+3)*4"
                    // this is to deal with the starting negative case
                    if (c == '-' && i == 0) {
                        operands.push(-1L);
                        operators.push('*');
                    } else {
                        operators.push(c);
                    }
                } else if (!operators.isEmpty() && (c == '*' || c == '/') && (operators.peek() == '+' || operators.peek() == '-')) {
                    // do nothing, keep pushing because */ has higher priority than +-
                    operators.push(c);
                } else if (!operators.isEmpty() && (c == '+' || c == '-') && (operators.peek() == '*' || operators.peek() == '/')) {
                    // calculate all previous expressions till hit the left bracket
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        operands.push(calculateValue(operands, operators.pop()));
                    }

                    operators.push(c);
                } else if (c == '(') {
                    operators.push(c);
                    // this is to deal with 1 * (-7+2) case
                    if (isNegativeNumberFollowingLeftBracket(s, i + 1)) {
                        operands.push(-1L);
                        operators.push('*');
                        while (i < s.length()) {
                            if (s.charAt(i) == '-') {
                                // i++;  // skip this '-', later we i++ on line 129
                                break;
                            }
                            i++;
                        }
                    }
                } else if (c == ')') {
                    if (number.length() != 0) {
                        operands.push(Long.parseLong(number.toString()));
                        number = new StringBuilder();
                    }

                    while (!operators.isEmpty() && operators.peek() != '(') {
                        char op = operators.pop();
                        operands.push(calculateValue(operands, op));

                    }
                    operators.pop(); // pop out the left (

                } else {
                    // for normal +- with previous +- || */ with previous */ case just calculate 1 step ahead
                    // but because 15 / 3 / 5 should be 1, but it won't work 3 / 5 = 0, so we have to calculate
                    // every time we can calculate and get result
                    if (!operators.isEmpty() && operators.peek() != '(') {
                        operands.push(calculateValue(operands, operators.pop()));
                    }
                    operators.push(c);
                }
            }
            i++;
        }

        if (number.length() != 0) {
            operands.push(Long.parseLong(number.toString()));
        }
        // for "3+2*2" case that's why we need a while loop
        while (!operators.isEmpty()) {
            operands.push(calculateValue(operands, operators.pop()));
        }

        return (int)operands.pop().longValue();
    }

    // given the current index(this would normally be the index after the '(', find out if there is
    // a negative integer following the '('
    private static boolean isNegativeNumberFollowingLeftBracket(String s, int index) {
        while (index < s.length()) {
            char c = s.charAt(index);
            if (c == ' ') {
                index++;
                continue;
            }
            if (c == '-') {
                return isDigitFollowing(s, index + 1);
            } else {
                return false;
            }
        }
        return false;
    }

    // given the current index, find out if it's a digit following it.
    private static boolean isDigitFollowing(String s, int index) {
        while (index < s.length()) {
            char c = s.charAt(index);
            if (c == ' ') {
                index++;
                continue;
            }
            if (Character.isDigit(c)) {
                return true;
            }
            return false;
        }
        return false;
    }


    private static boolean isValidOperator(char op) {
        if (op == '+' || op == '-' || op == '*' || op == '/' || op == '(' || op == ')') {
            return true;
        }
        return false;
    }

    private static long calculateValue(Stack<Long> operands, char op) {
        long o2 = operands.pop();
        long o1 = operands.pop();

        if (op == '+') {
            return o1 + o2;
        } else if (op == '-') {
            return o1 - o2;
        } else if (op == '*') {
            return o1 * o2;
        } else if (op == '/') {
            return o1 / o2;
        } else {
            throw new IllegalArgumentException("invalid op! " + op);
        }
    }

    public static void main(String[] args) {
        String s = "2*(5+5*2)/3+(6/2+8)";
        System.out.println(calculate(s));
    }

}
