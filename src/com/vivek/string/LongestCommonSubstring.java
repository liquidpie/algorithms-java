package com.vivek.string;

/**
 * Longest Common Substring
 *
 * Given two strings X and Y, find the length of the longest common substring.
 *
 * Input : X = “GeeksforGeeks”, y = “GeeksQuiz”
 * Output : 5
 * Explanation:
 * The longest common substring is “Geeks” and is of length 5.
 *
 * Input : X = “abcdxyz”, y = “xyzabcd”
 * Output : 4
 * Explanation:
 * The longest common substring is “abcd” and is of length 4.
 *
 * Input : X = “zxabcdezy”, y = “yzabcdezx”
 * Output : 6
 * Explanation:
 * The longest common substring is “abcdez” and is of length 6.
 *
 *
 * Dynamic Programming can be used to find the longest common substring in O(m*n) time.
 * The idea is to find the length of the longest common suffix for all substrings of both strings and store these lengths in a table.
 *
 *    The longest common suffix has following optimal substructure property.
 *    If last characters match, then we reduce both lengths by 1
 *    LCSuff(X, Y, m, n) = LCSuff(X, Y, m-1, n-1) + 1 if X[m-1] = Y[n-1]
 *    If last characters do not match, then result is 0, i.e.,
 *    LCSuff(X, Y, m, n) = 0 if (X[m-1] != Y[n-1])
 *    Now we consider suffixes of different substrings ending at different indexes.
 *    The maximum length Longest Common Suffix is the longest common substring.
 *    LCSubStr(X, Y, m, n) = Max(LCSuff(X, Y, i, j)) where 1 <= i <= m and 1 <= j <= n
 *
 * Time Complexity: O(m*n)
 * Auxiliary Space: O(m*n)
 *
 * Reference: https://www.geeksforgeeks.org/longest-common-substring-dp-29/
 */
public class LongestCommonSubstring {

    static int longestCommonSubstring(char[] x, char[] y, int m, int n) {
        // Create a table to store lengths of longest common suffixes of substrings.
        // Note that LCSuff[i][j] contains length of longest common suffix of X[0..i-1] and Y[0..j-1].
        // The first row and first column entries have no logical meaning, they are used only for simplicity of program
        int[][] lcSuff = new int[m + 1][n + 1];

        int result  = 0;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    lcSuff[i][j] = 0;
                }
                else if (x[i - 1] == y[j - 1]) {
                    lcSuff[i][j] = lcSuff[i - 1][j - 1] + 1;
                    result = Math.max(result, lcSuff[i][j]);
                } else {
                    lcSuff[i][j] = 0;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str1 = "GeeksforGeeks";
        String str2 = "GeeksQuiz";
        System.out.println(longestCommonSubstring(str1.toCharArray(), str2.toCharArray(), str1.length(), str2.length()));
    }

}
