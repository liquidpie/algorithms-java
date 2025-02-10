package com.vivek.string;

/**
 * 58. Length of Last Word
 *
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 * A word is a maximal
 * substring
 * consisting of non-space characters only.
 *
 * Example 1:
 *
 * Input: s = "Hello World"
 * Output: 5
 * Explanation: The last word is "World" with length 5.
 *
 * Example 2:
 *
 * Input: s = "   fly me   to   the moon  "
 * Output: 4
 * Explanation: The last word is "moon" with length 4.
 *
 * Example 3:
 *
 * Input: s = "luffy is still joyboy"
 * Output: 6
 * Explanation: The last word is "joyboy" with length 6.
 *
 * Reference:
 * https://leetcode.com/problems/length-of-last-word/description
 */
public class LastWordLength {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(lengthOfLastWord("luffy is still joyboy"));
    }

    static int lengthOfLastWord(String s) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                end = i;
                if (i != 0 && s.charAt(i - 1) == ' ') {
                    start = i;
                }
            }
        }

        return end - start + 1;
    }

    static int lengthOfLastWordBuiltIn(String s) {
        s = s.trim();
        return s.length() - s.lastIndexOf(" ") - 1;
    }

}
