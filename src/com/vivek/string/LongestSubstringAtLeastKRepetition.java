package com.vivek.string;

/**
 *
 * Largest substring where all characters appear at least K times
 *
 * Given a string str and an integer K, the task is to find the length of the longest sub-string S such that every character in S appears at least K times.
 *
 * Examples:
 *
 *     Input: str = “aabbba”, K = 3
 *     Output: 6
 *     Explanation:
 *     In substring aabbba, each character repeats at least k times and its length is 6.
 *
 *     Input: str = “ababacb”, K = 3
 *     Output: 0
 *     Explanation:
 *     There is no substring where each character repeats at least k times.
 *
 * Approach: Using Divide and Conquer technique and Recursion. Below are the steps:
 *
 * - Store the frequency of each characters of the given string in a frequency array of size 26.
 * - Initialize two variables start with 0 and end which is the length of the string str.
 * - Iterate over the string from start to end and count the number of times each character repeats and store it in an array.
 * - If any character repeats less than K times, then Divide the string into two halves. If i is the index of the string where we found that the string[i] repeats less than K times, then we divide the string into two halves from start to i and i + 1 to end.
 * - Recursively call for the two halves in the above steps i.e., from start to i and i + 1 to end separately and repeat the Step 2 and 3 and return the maximum of the two values reutnr by the above recursive call.
 * - If all the characters between start and end is repeated at least K times, then the answer is end – start.
 *
 * Reference: https://www.geeksforgeeks.org/largest-substring-where-all-characters-appear-at-least-k-times-set-2/
 *
 */
public class LongestSubstringAtLeastKRepetition {

    static int longestSubstr(String str, int k) {
        return longestSubstr(0, str.length(), str, k);
    }

    private static int longestSubstr(int start, int end, String str, int k) {
        int[] freq = new int[26];

        // Store the frequency from str[start...end]
        for (int i = start; i < end; i++) {
            freq[str.charAt(i) - 'a']++;
        }

        for (int i = start; i < end; i++) {
            if (freq[str.charAt(i) - 'a'] < k) {
                int left = longestSubstr(start, i, str, k);
                int right = longestSubstr(i + 1, end, str, k);
                return Math.max(left, right);
            }
        }

        // If all the characters are repeated at least k times
        return end - start;
    }

    public static void main(String[] args) {
        String str = "aabbba";
        int k = 3;

        System.out.println(longestSubstr(str, k));

    }

}
