package com.vivek.string;


/**
 * Longest repeating and non-overlapping substring
 *
 * Given a string str, find the longest repeating non-overlapping substring in it.
 * In other words find 2 identical substrings of maximum length which do not overlap.
 * If there exists more than one such substring return any of them.
 *
 * Examples:
 *
 * Input : str = "geeksforgeeks"
 * Output : geeks
 *
 * Input : str = "aab"
 * Output : a
 *
 * Input : str = "aabaabaaba"
 * Output : aaba
 *
 * Input : str = "aaaaaaaaaaa"
 * Output : aaaaa
 *
 * Input : str = "banana"
 * Output : an
 *          or na
 *
 * Naive Solution : The problem can be solved easily by taking all the possible substrings and
 * for all the substrings check it for the remaining(non-overlapping) string if there exists an identical substring.
 * There are O(n2) total substrings and checking them against the remaining string will take O(n) time.
 * So overall time complexity of above solution is O(n3).
 *
 * Dynamic Programming : This problem can be solved in O(n2) time using Dynamic Programming.
 * The basic idea is to find the longest repeating suffix for all prefixes in the string str.
 *
 * Length of longest non-repeating substring can be recursively
 * defined as below.
 *
 * LCSRe(i, j) stores length of the matching and
 *             non-overlapping substrings ending
 *             with i'th and j'th characters.
 *
 * If str[i-1] == str[j-1] && (j-i) > LCSRe(i-1, j-1)
 *      LCSRe(i, j) = LCSRe(i-1, j-1) + 1,
 * Else
 *      LCSRe(i, j) = 0
 *
 * Where i varies from 1 to n and
 *       j varies from i+1 to n
 *
 * To avoid overlapping we have to ensure that the length of suffix is less than (j-i) at any instant.
 * The maximum value of LCSRe(i, j) provides the length of the longest repeating substring and the substring itself can be found using the length and the ending index of the common suffix.
 *
 * Time Complexity: O(n2)
 * Auxiliary Space: O(n2)
 *
 * Reference: https://www.geeksforgeeks.org/longest-repeating-and-non-overlapping-substring
 */

public class LongestRepeatingNonOverlappingSubstring {

    static String findSubstring(String str) {
        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        String result = "";     // To store result
        int resultLength = 0;   // To store length of result
        int endIndex = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // (j-i) > dp[i-1][j-1] to remove overlapping
                if (str.charAt(i - 1) == str.charAt(j - 1) && dp[i - 1][j - 1] < (j - i)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    // updating maximum length of the substring and updating the ending index of the suffix
                    if (dp[i][j] > resultLength) {
                        resultLength = dp[i][j];
                        endIndex = Math.max(i, endIndex);
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        // If we have non-empty result, then insert all characters from first character to last character of String
        if (resultLength > 0) {
            for (int i = endIndex - resultLength + 1; i <= endIndex; i++) {
                result += str.charAt(i - 1);
            }
        }

        return result;

    }

    public static void main(String[] args) {
        System.out.println(findSubstring("geeksforgeeks"));
    }

}
