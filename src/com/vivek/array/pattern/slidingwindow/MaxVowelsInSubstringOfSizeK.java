package com.vivek.array.pattern.slidingwindow;

import java.util.Set;

/**
 * 1456. Maximum Number of Vowels in a Substring of Given Length
 *
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 *
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 * Example 1:
 *
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 *
 * Example 2:
 *
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 *
 * Example 3:
 *
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 *
 * Reference: https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length
 */
public class MaxVowelsInSubstringOfSizeK {

    public int maxVowels(String s, int k) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        int begin = 0;
        int vowel = 0;
        int maxVowel = 0;

        for (int end = 0; end < s.length(); end++) {
            if (vowels.contains(s.charAt(end)))
                vowel++;

            if (end - begin + 1 == k) {
                maxVowel = Math.max(maxVowel, vowel);
                if (vowels.contains(s.charAt(begin)))
                    vowel--;
                begin++;
            }
        }

        return maxVowel;
    }

}
