package com.vivek.string;

/**
 * 2000. Reverse Prefix of Word
 *
 * Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.
 *
 *     For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
 *
 * Return the resulting string.
 *
 * Example 1:
 *
 * Input: word = "abcdefd", ch = "d"
 * Output: "dcbaefd"
 * Explanation: The first occurrence of "d" is at index 3.
 * Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
 *
 * Example 2:
 *
 * Input: word = "xyxzxe", ch = "z"
 * Output: "zxyxxe"
 * Explanation: The first and only occurrence of "z" is at index 3.
 * Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".
 *
 * Example 3:
 *
 * Input: word = "abcd", ch = "z"
 * Output: "abcd"
 *
 * Reference: https://leetcode.com/problems/reverse-prefix-of-word/description/
 */
public class ReversePrefixOfWord {

    public static void main(String[] args) {
        ReversePrefixOfWord helper = new ReversePrefixOfWord();
        System.out.println(helper.reversePrefix("abcdefd", 'd'));
    }

    public String reversePrefix(String word, char ch) {
        char[] arr = word.toCharArray();
        int i = 0;
        while (i < arr.length && arr[i] != ch)
            i++;

        if (i == arr.length)
            return word;

        for (int j = 0; j <= i / 2; j++) {
            char temp = arr[j];
            arr[j] = arr[i - j];
            arr[i - j] = temp;
        }

        return new String(arr);
    }

}
