package com.vivek.stack.examples;

import java.util.Stack;

/**
 * Check If Word Is Valid After Substitutions
 *
 * Given a string s, determine if it is valid.
 *
 * A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the following operation any number of times:
 *
 *     Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft + tright. Note that tleft and tright may be empty.
 *
 * Return true if s is a valid string, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabcbc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "aabcbc"
 * Thus, "aabcbc" is valid.
 *
 * Example 2:
 *
 * Input: s = "abcabcababcc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * Thus, "abcabcababcc" is valid.
 *
 * Example 3:
 *
 * Input: s = "abccba"
 * Output: false
 * Explanation: It is impossible to get "abccba" using the operation.
 *
 *
 * * Approach: Everytime we encounter the character 'c' we will
 *          * check if the last two elements were 'a' and 'b'.
 *          * The stack should be empty in case of valid strings.
 *          *
 *          * Time Complexity: O(n)
 *          * Space Complexity: O(n)
 *
 * https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/
 */
public class ValidSubstitutions {

    public static void main(String[] args) {
        String s = "abcabcababcc";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'c') {
                if (stack.size() < 2) {
                    return false;
                }
                if (stack.pop() != 'b' || stack.pop() != 'a') {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

}
