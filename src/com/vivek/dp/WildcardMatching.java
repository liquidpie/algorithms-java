package com.vivek.dp;

/**
 * Wildcard Matching
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *
 *     '?' Matches any single character.
 *     '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 *
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Example 3:
 *
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 *          * Approach: We will use a dynamic-programming based approach
 *          * to keep track of the pattern matching algorithm. We use a 2-D matrix
 *          * to persist the matches between the pattern and the string. If the element
 *          * in the pattern matches the one in the string then we rely on the
 *          * match result obtained so far without those specific elements(so dp[i-1][j-1]).
 *          * Otherwise, we check if the current element in the pattern is "*" in which
 *          * case we either check for the match results by either removing a character
 *          * from the string or the pattern.
 *          *
 *          * Time Complexity: O(mn)
 *          * Space Complexity: O(mn)
 *
 * https://leetcode.com/problems/wildcard-matching/description/
 */
public class WildcardMatching {

    public static void main(String[] args) {
        String s = "abcde";
        String p = "a?c*";

        System.out.println(isMatch(s, p));
    }

    static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;    //s and p are empty

        for (int j = 0; j < m + 1; j++) {
            if (j != 0 && p.charAt(j - 1) == '*')   // s is empty and p is '*'
                dp[0][j] = dp[0][j - 1];            // imp step
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++){
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }
        return dp[n][m];
    }

}
