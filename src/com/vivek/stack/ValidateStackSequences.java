package com.vivek.stack;

import java.util.Stack;

/**
 * 946. Validate Stack Sequences
 *
 * Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result
 * of a sequence of push and pop operations on an initially empty stack, or false otherwise.
 *
 * Example 1:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4),
 * pop() -> 4,
 * push(5),
 * pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * Example 2:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * Output: false
 * Explanation: 1 cannot be popped before 2.
 *
 * Solution:
 * https://leetcode.com/problems/validate-stack-sequences/description/comments/1861868
 *
 * Reference:
 * https://leetcode.com/problems/validate-stack-sequences/description/
 */
public class ValidateStackSequences {

    public static void main(String[] args) {
        {
            int[] pushed = {1, 2, 3, 4, 5};
            int[] popped = {4, 5, 3, 2, 1};

            System.out.println(validateStackSequences(pushed, popped)); // true
        }
        {
            int[] pushed = {1, 2, 3, 4, 5};
            int[] popped = {4, 3, 5, 1, 2};

            System.out.println(validateStackSequences(pushed, popped)); // false
        }
    }

    static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();

        int n = pushed.length;
        int pushIdx = 0;
        int popIdx = 0;

        while (pushIdx < n && popIdx < n) {
            if (!stack.isEmpty() && stack.peek() == popped[popIdx]) {
                stack.pop();
                popIdx++;
            } else {
                stack.push(pushed[pushIdx++]);
            }
        }

        while (popIdx < n && !stack.isEmpty() && stack.peek() == popped[popIdx]) {
            stack.pop();
            popIdx++;
        }

        return stack.isEmpty() && popIdx == n;
    }

}
