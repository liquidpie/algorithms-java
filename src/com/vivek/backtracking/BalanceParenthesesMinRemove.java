package com.vivek.backtracking;

import java.util.*;

/**
 *  Balance opening and closing parentheses by removing
 *  the minimum number of invalid ones.
 *  Show the total number of combinations of indices of
 *  such characters you need to remove.
 *
 *  Note:
 *  The opening parenthesis is uniquely "(".
 *  The closing parenthesis is uniquely ")".
 *
 *  Examples:
 *  #, Input, Output:
 *  1, "()",     1
 *  2, "((",     1
 *  3, ")(",     1
 *  4, "(()()",  3
 *
 *  Detailed explanations:
 *  In the following explanation, [a, b, ...] means an array of indexes.
 *
 *  Case 1, 2, 3: Each case has only ONE combination of indices to make it valid.
 *  For the case 1, remove characters at [] from "()" to get "()".
 *  For the case 2, remove characters at [0, 1] from "((" to get "".
 *  For the case 3, remove characters at [0, 1] from ")(" to get "".
 *
 *  Case 4: There are THREE combinations of indices to make it valid.
 *  Remove [0] from "(()()" to get "()()", or
 *  Remove [1] from "(()()" to get "()()", or
 *  Remove [3] from "(()()" to get "(())".
 *
 *  Approach:
 * As we need to generate all possible output we will backtrack among all states by removing one opening or closing bracket and
 * check if they are valid if invalid then add the removed bracket back and go for next state.
 * We will use BFS for moving through states, use of BFS will assure removal of minimal number of brackets
 * because we traverse into states level by level and each level corresponds to one extra bracket removal.
 * Other than this BFS involve no recursion so overhead of passing parameters is also saved.
 *
 * Below code has a method isValidString to check validity of string, it counts open and closed parenthesis at each index ignoring non-parenthesis character.
 * If at any instant count of close parenthesis becomes more than open then we return false else we keep update the count variable.
 *
 *  Reference: https://www.geeksforgeeks.org/remove-invalid-parentheses
 */
public class BalanceParenthesesMinRemove {

    static void balanceParentheses(String expr) {
        if (expr == null || expr.length() == 0)
            return;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(expr);
        visited.add(expr);

        boolean level = false;
        List<String> output = new ArrayList<>();

        while (!queue.isEmpty()) {
            String str = queue.poll();

            if (isBalanced(str)) {
                output.add(str);
                // If answer is found, make level true
                // so that valid string of only that level
                // are processed.
                level = true;
            }

            if (level)
                continue;

            for (int i = 0; i < str.length(); i++) {
                // Removing parenthesis from str and
                // pushing into queue,if not visited already
                String temp = str.substring(0, i) + str.substring(i + 1);
                if (!visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }
        }

        System.out.println(output.size());
        System.out.println(output);

    }

    private static boolean isBalanced(String expr) {
        int balance = 0;
        for (char ch : expr.toCharArray()) {
            balance += ch == '(' ? 1 : -1;
            if (balance < 0)
                return false;
        }
        return balance == 0;
    }

    public static void main(String[] args) {
        String expr = "(()()";
        balanceParentheses(expr);
    }

}
