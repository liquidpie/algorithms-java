package com.vivek.stack.examples;

import java.util.Stack;

/**
 * Basic Calculator
 *
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 *
 * Solution: https://blog.baozitraining.org/2019/08/leetcode-solution-224-basic-calculator.html
 *
 * Approach ::
 * A generic way for solving those calculating numbers problems is to use stack.
 * More specifically, use two stacks: one stack for the operator and the other for the operands.
 *
 * A few caveats :
 * - Pay attention to the order when popping out operands and calculate, the order matters.
 * - The parenthesis matters, 2 - 1 + 2 = 3, while 2 - (1+2) = -1 = 2 - 1 - 2 if you want to remove the bracket you need to change the sign
 * - "1000" is a valid expression without any operators
 * - The number might not be just single digit, so need to read the char and convert the numbers
 *
 * Solutions :->
 *
 * 1. Standard generic way
 * Keep two stacks, operator and operands. When we see left bracket, keep pushing to the stack.
 * We calculate the values as normal within the inner most bracket. When we see right bracket, calculate and pop out the left bracket
 *
 * Time Complexity: O(N), N is the length of the string
 * Space Complexity: O(N), extra stack is needed
 *
 * 2. Use the sign method with one stack
 * The thought process is very similar to use the stacks, in this method, the clever part is it uses only one stack and also pushed a sign.
 * +1 for "+" and -1 for "-1". Whenever there is a left bracket, you push the existing result and the sign into the stack.
 * Whenever there is a right bracket, you pop up the sign and the value. Pretty neat!
 *
 * Time Complexity: O(N), N is the length of the string
 * Space Complexity: O(N), extra stack is needed
 *
 *
 * https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculator {

    public static void main(String[] args) {
//        String s = "(1+(4+5+2)-3)+(6+8)";
//        String s = "1 + 1";
        String s = "2147483647";
        System.out.println("Input: " + s);
        System.out.println(calculateUsingStackAndSign(s));
    }

    static int calculateUsingTwoStacks(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int i = 0;
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder number = new StringBuilder();

        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            if (Character.isDigit(c)) {
                number.append(c);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                // push the current number to the operands stack
                if (number.length() != 0) {
                    operands.push(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                }
                if (!operators.isEmpty() && operators.peek() != '(') {
                    char op = operators.pop();
                    operands.push(calculateValue(operands, op));
                }
                if (!operators.isEmpty()) { // pop the left bracket
                    operators.pop();
                }
            } else if (c == '+' || c == '-') {
                if (number.length() != 0) {
                    operands.push(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                }
                if (!operators.isEmpty() && operators.peek() != '(') {
                    operands.push(calculateValue(operands, operators.pop()));
                }
                operators.push(c);
            }
            i++;
        }

        if (number.length() != 0) {
            operands.push(Integer.parseInt(number.toString()));
        }

        // need this for the 1+1 case, don't need a while since we are only one step away
        if (!operators.isEmpty()) {
            operands.push(calculateValue(operands, operators.pop()));
        }

        return operands.pop();
    }

    private static int calculateValue(Stack<Integer> operands, char operator) {
        // Pay attention to the stack order
        int o2 = operands.pop();
        int o1 = operands.pop();

        if (operator == '+') {
            return o1 + o2;
        } else if (operator == '-') {
            return o1 - o2;
        } else {
            throw new IllegalArgumentException("invalid operator!");
        }
    }

    /**
     * The main idea of this is the left bracket might change the sign of a number, however, this does not seem to be very generalized
     * https://leetcode.com/problems/basic-calculator/discuss/62362/JAVA-Easy-Version-To-Understand!!!!!
     */
    static int calculateUsingStackAndSign(String s) {
        int n = s.length();
        int sign = 1;
        int result = 0;

        // Use one stack for numbers, for operators used the sign, + -> 1, - -> -1
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sum * sign;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                // first pop is the sign, +1 or -1, second is the value
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }

}
