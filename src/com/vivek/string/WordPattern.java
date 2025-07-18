package com.vivek.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 290. Word Pattern
 *
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:
 *
 *     Each letter in pattern maps to exactly one unique word in s.
 *     Each unique word in s maps to exactly one letter in pattern.
 *     No two letters map to the same word, and no two words map to the same letter.
 *
 *
 *
 * Example 1:
 *
 * Input: pattern = "abba", s = "dog cat cat dog"
 *
 * Output: true
 *
 * Explanation:
 *
 * The bijection can be established as:
 *
 *     'a' maps to "dog".
 *     'b' maps to "cat".
 *
 * Example 2:
 *
 * Input: pattern = "abba", s = "dog cat cat fish"
 *
 * Output: false
 *
 * Example 3:
 *
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 *
 * Output: false
 *
 * Solution:
 * We need to check if a pattern string bijectively maps to words in a sentence.
 * That means each character in the pattern must map to a unique word and vice versa.
 * If either mapping fails, the pattern doesn't match.
 *
 *
 *
 * Reference:
 * https://leetcode.com/problems/word-pattern
 */
public class WordPattern {

    public static void main(String[] args) {
        WordPattern wordPattern = new WordPattern();
        {
            System.out.println(wordPattern.wordPattern("abba", "dog dog dog dog"));
        }
    }

    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" ");
        if (pattern.length() != arr.length)
            return false;
        Map<Character, String> matcher = new HashMap<>();
        Map<String, Character> reverse = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = arr[i];
            if (matcher.containsKey(ch) || reverse.containsKey(word)) {
                if (!word.equalsIgnoreCase(matcher.get(ch)))
                    return false;
            } else {
                matcher.put(ch, word);
                reverse.put(word, ch);
            }
        }

        return true;

    }

}
