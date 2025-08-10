package com.vivek.string;

/**
 * 1768. Merge Strings Alternately
 *
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1.
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 *
 * Return the merged string.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 * Explanation: The merged string will be merged as so:
 * word1:  a   b   c
 * word2:    p   q   r
 * merged: a p b q c r
 *
 * Example 2:
 *
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 * Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 * word1:  a   b
 * word2:    p   q   r   s
 * merged: a p b q   r   s
 *
 * Example 3:
 *
 * Input: word1 = "abcd", word2 = "pq"
 * Output: "apbqcd"
 * Explanation: Notice that as word1 is longer, "cd" is appended to the end.
 * word1:  a   b   c   d
 * word2:    p   q
 * merged: a p b q c   d
 *
 * Reference:
 * https://leetcode.com/problems/merge-strings-alternately
 */
public class MergeStringsAlternately {

    public static void main(String[] args) {
        MergeStringsAlternately helper = new MergeStringsAlternately();
        String word1 = "ab", word2 = "pqrs";
        System.out.println(helper.mergeAlternately(word1, word2));
    }


    public String mergeAlternately(String word1, String word2) {
        StringBuilder builder = new StringBuilder();

        int i = 0;
        for (; i < word1.length() && i < word2.length(); i++) {
            builder.append(word1.charAt(i))
                    .append(word2.charAt(i));
        }

        if (i < word1.length()) {
            builder.append(word1.substring(i));
        }

        if (i < word2.length()) {
            builder.append(word2.substring(i));
        }

        return builder.toString();
    }

}
