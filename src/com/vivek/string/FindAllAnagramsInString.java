package com.vivek.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Find All Anagrams in a String
 *
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 *          * Approach: We use a map to maintain the frequency of characters
 *          * in p. We then use a separate map to maintain the frequency of
 *          * characters in s string. After that, we keep on removing the
 *          * old character and adding new characters in the map.
 *          *
 *          * Time Complexity : O(n)
 *          * Space Complexity: O(n)
 *
 *
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsInString {

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        System.out.println(findAnagrams(s, p));
    }

    static List<Integer> findAnagrams(String s, String p) {
        if(s.length() < p.length()) {
            return new LinkedList<>();
        }
        Map<Character, Integer> patternMap = new HashMap<>();
        for (char pCh : p.toCharArray()) {
            patternMap.put(pCh, patternMap.getOrDefault(pCh, 0) + 1);
        }

        Map<Character, Integer> textMap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            textMap.put(s.charAt(i), textMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        List<Integer> result = new LinkedList<>();
        if (textMap.equals(patternMap))
            result.add(0);

        for (int i = p.length(); i < s.length(); i++) {
            char newChar = s.charAt(i);
            char oldChar = s.charAt(i - p.length());
            if (textMap.get(oldChar) == 1) {
                textMap.remove(oldChar);
            } else {
                textMap.put(oldChar, textMap.get(oldChar) - 1);
            }

            textMap.put(newChar, textMap.getOrDefault(newChar, 0) + 1);
            if (textMap.equals(patternMap))
                result.add(i - p.length() + 1);
        }

        return result;
    }

}
