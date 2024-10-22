package com.vivek.stack;

import java.util.Stack;

/**
 * Remove Outer parentheses only
 */
public class RemoveParentheses {

    private static final char openingBrace = '(';
    private static final char closingBrace = ')';

    public static void main(String[] args) {
        {
            String input = "((abc)(def))";
            String output = removeOuterParentheses(input);
            System.out.println(output);
        }
        {
            String input = "(abc)(def)";
            String output = removeOuterParentheses(input);
            System.out.println(output);
        }
        {
            String input = "((abc))";
            String output = removeOuterParentheses(input);
            System.out.println(output);
        }
        {
            String input = "(()())()";
            String output = removeOuterParentheses(input);
            System.out.println(output);
        }
    }

    private static String removeOuterParentheses(String input) {
        if (input == null || input.length() == 0)
            return input;

        Stack<Integer> stack = new Stack<>();
        int len = input.length() - 1;

        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == openingBrace) {
                stack.push(i);
            } else if (arr[i] == closingBrace) {
                if (stack.isEmpty()) {
                    break;
                } else if ((len - stack.peek() != i) || (stack.size() != stack.peek() + 1)) {
                    stack.pop();
                } else {
                    return input.substring(stack.peek() + 1, i);
                }
            }
        }

        return input;
    }

}
