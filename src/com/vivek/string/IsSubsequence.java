package com.vivek.string;

/**
 * Is Subsequence
 *
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Example 1:
 *
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 *
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 * https://leetcode.com/problems/is-subsequence
 */
public class IsSubsequence {

    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }

    static boolean isSubsequence(String s, String t) {
        if (s.length() == 0)
            return true;
        if (t.length() == 0)
            return false;

        int k = 0;
        for (int i = 0; i < t.length(); i++) {
            if (k == s.length())
                return true;
            if (s.charAt(k) == t.charAt(i)) {
                k++;
            }
        }

        return k == s.length();
    }

}
