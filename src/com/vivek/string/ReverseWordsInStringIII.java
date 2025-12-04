package com.vivek.string;

/**
 * 557. Reverse Words in a String III
 *
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 * Example 1:
 *
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 *
 * Example 2:
 *
 * Input: s = "Mr Ding"
 * Output: "rM gniD"
 *
 * Reference: https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
 */
public class ReverseWordsInStringIII {

    public String reverseWords(String s) {
        String[] words = s.split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (String word : words) {
            StringBuilder reversedWord = new StringBuilder(word);
            reversedWord.reverse();
            reversed.append(reversedWord).append(" ");
        }
        return reversed.toString().trim();
    }

}
