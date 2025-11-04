package com.vivek.hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * 500. Keyboard Row
 *
 * Given an array of strings words, return the words that can be typed using letters of the alphabet on only one row of American keyboard like the image below.
 *
 * Note that the strings are case-insensitive, both lowercased and uppercased of the same letter are treated as if they are at the same row.
 *
 * In the American keyboard:
 *
 *     the first row consists of the characters "qwertyuiop",
 *     the second row consists of the characters "asdfghjkl", and
 *     the third row consists of the characters "zxcvbnm".
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["Hello","Alaska","Dad","Peace"]
 *
 * Output: ["Alaska","Dad"]
 *
 * Explanation:
 *
 * Both "a" and "A" are in the 2nd row of the American keyboard due to case insensitivity.
 *
 * Example 2:
 *
 * Input: words = ["omk"]
 *
 * Output: []
 *
 * Example 3:
 *
 * Input: words = ["adsdf","sfd"]
 *
 * Output: ["adsdf","sfd"]
 *
 * Reference: https://leetcode.com/problems/keyboard-row/description/
 */
public class KeyboardRow {

    public String[] findWords(String[] words) {
        int[] rows = rowIndices();
        List<String> result = new ArrayList<>();

        for (String _word : words) {
            String word = _word.toLowerCase();
            int prevRow = -1;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (prevRow == -1) {
                    prevRow = rows[ch - 'a'];
                } else if (rows[ch - 'a'] != prevRow) {
                    break;
                }
                if (i == word.length() - 1) {
                    result.add(_word);
                }
            }
        }

        return result.toArray(new String[0]);
    }

    private int[] rowIndices() {
        int[] rows = new int[26];
        for (char ch : "qwertyuiop".toCharArray()) {
            rows[ch - 'a'] = 1;
        }
        for (char ch : "asdfghjkl".toCharArray()) {
            rows[ch - 'a'] = 2;
        }
        for (char ch : "zxcvbnm".toCharArray()) {
            rows[ch - 'a'] = 3;
        }
        return rows;
    }

}
