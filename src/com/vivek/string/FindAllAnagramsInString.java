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
 *  Solution #
 * This problem follows the Sliding Window pattern and is very similar to Permutation in a String.
 * In this problem, we need to find every occurrence of any permutation of the pattern in the string.
 * We will use a list to store the starting indices of the anagrams of the pattern in the string.
 *
 * Time Complexity #
 * The time complexity of the above algorithm will be O(N + M ) where ‘N’ and ‘M’ are the number of
 * characters in the input string and the pattern respectively.
 *
 * Space Complexity #
 * The space complexity of the algorithm is O(M ) since in the worst case, the whole pattern can have distinct characters which will go into the HashMap.
 * In the worst case, we also need O(N ) space for the result list, this will happen when the pattern has only one character and the string contains only that character.
 *
 * Reference:
 * 1. https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * 2. Grokking the Coding Interview
 * Pattern: Sliding Window
 */
public class FindAllAnagramsInString {

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("ppqp", "pq"));
        System.out.println(findAnagrams("abbcabc", "abc"));
    }

    public static List<Integer> findAnagrams(String str, String pattern) {
        Map<Character, Integer> patternFreq = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            patternFreq.put(ch, patternFreq.getOrDefault(ch, 0) + 1);
        }

        int windowStart = 0;
        int matched = 0;
        List<Integer> resultIndices = new LinkedList<>();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (patternFreq.containsKey(rightChar)) {
                patternFreq.put(rightChar, patternFreq.get(rightChar) - 1);
                if (patternFreq.get(rightChar) == 0) {
                    matched++;
                }
            }

            if (matched == patternFreq.size()) {
                resultIndices.add(windowStart);
            }

            if (windowEnd >= pattern.length() - 1) { // shrink the window by one char
                char leftChar = str.charAt(windowStart);
                if (patternFreq.containsKey(leftChar)) {
                    if (patternFreq.get(leftChar) == 0)
                        matched--;  // before putting the character back, decrement the matched count
                    // put the character back for matching
                    patternFreq.put(leftChar, patternFreq.get(leftChar) + 1);
                }
                windowStart++;
            }
        }

        return resultIndices;
    }

}
