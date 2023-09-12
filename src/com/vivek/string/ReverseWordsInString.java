package com.vivek.string;

import java.util.Stack;
import java.util.StringJoiner;

/**
 * Reverse Words in a String
 *
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 * Example 1:
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 *
 * https://leetcode.com/problems/reverse-words-in-a-string
 */
public class ReverseWordsInString {

    public static void main(String[] args) {
        String s = "  hello world  ";
        System.out.println(reverseWords(s));
    }

    static String reverseWords(String s) {
        String[] arr = s.split("\\s+");
        StringJoiner sj = new StringJoiner(" ");
        for (int i = arr.length - 1; i >= 0; i--) {
            if (!arr[i].trim().isEmpty()) {
                sj.add(arr[i]);
            }
        }
        return sj.toString();
    }

    static String reverseWords2(String s) {
        String[] arr = s.split("\\s+");
        StringBuilder sj = new StringBuilder();
        String delimeter = " ";
        boolean first = true;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (!arr[i].trim().isEmpty()) {
                sj.append(!first ? delimeter : "").append(arr[i]);
                first = false;
            }
        }
        return sj.toString();
    }

}
