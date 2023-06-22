package com.vivek.string;

import java.util.*;

/**
 * Word Break
 *
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 * Time Complexity: O(n^3)
 * Space Complexity: O(n^2 + Σ |wordDict[i]|)
 *
 * Solution: https://walkccc.me/LeetCode/problems/0139/
 *
 * https://leetcode.com/problems/word-break
 */
public class WordBreak {

    public static void main(String[] args) {
        String word = "leetcode";
        List<String> wordDict = List.of("leet", "code");
        System.out.println(wordBreak(word, wordDict));
    }

    static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, new HashSet<>(wordDict), new HashMap<>());
    }

    static boolean wordBreak(String word, Set<String> wordSet, Map<String, Boolean> memo) {
        if (memo.containsKey(word))
            return memo.get(word);
        if (wordSet.contains(word)) {
            memo.put(word, true);
            return true;
        }

        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if (wordSet.contains(prefix) && wordBreak(suffix, wordSet, memo)) {
                memo.put(word, true);
                return true;
            }
        }

        memo.put(word, false);
        return false;
    }

}
