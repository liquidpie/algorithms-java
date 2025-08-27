package com.vivek.stack.examples;

import java.util.Stack;

/**
 * 394. Decode String
 *
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and
 * that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 *
 * The test cases are generated so that the length of the output will never exceed 105.
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 *
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 *
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 *
 * Reference: https://leetcode.com/problems/decode-string
 */
public class DecodeString {

    public static void main(String[] args) {
        DecodeString helper = new DecodeString();
        System.out.println(helper.decodeString("3[a]2[bc]"));
        System.out.println(helper.decodeString("3[a2[c]]"));
        System.out.println(helper.decodeString("2[abc]3[cd]ef"));
        System.out.println(helper.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
    }

    public String decodeString(String s) {
        int n = s.length();
        int i = 0;
        int start = i;

        Stack<String> stack = new Stack<>();
        while (i < n) {
            start = i;
            if (isAlpha(s.charAt(i))) {
                while (i < n && isAlpha(s.charAt(i))) {
                    i++;
                }
                stack.push(s.substring(start, i));
            } else if (Character.isDigit(s.charAt(i))) {
                while (i < n && Character.isDigit(s.charAt(i))) {
                    i++;
                }
                stack.push(s.substring(start, i));
            } else if (s.charAt(i) == ']') {
                StringBuilder part = new StringBuilder();
                while (!stack.isEmpty() && !stack.peek().equals("["))
                    part.insert(0, stack.pop());

                stack.pop();
                int cnt = Integer.parseInt(stack.pop());
                stack.push(part.toString().repeat(cnt));
                i++;
            } else {
                stack.push("" + s.charAt(i));
                i++;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }

    private boolean isAlpha(char ch) {
        return ch - 'a' >= 0 && ch - 'a' <= 25;
    }

}
