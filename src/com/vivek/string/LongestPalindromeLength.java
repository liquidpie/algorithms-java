package com.vivek.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Palindrome
 *
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 *
 * Example 1:
 *
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * Example 2:
 *
 * Input: s = "a"
 * Output: 1
 * Explanation: The longest palindrome that can be built is "a", whose length is 1.
 *
 * https://leetcode.com/problems/longest-palindrome/
 *
 */
public class LongestPalindromeLength {

    public static void main(String[] args) {
        String s = "aaabbbccccddd";
        System.out.println(longestPalindrome(s));
    }

    static int longestPalindrome(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
        }

        int len = 0;
        boolean singleLenAdded = false;
        for (int count : freq.values()) {
            int incr = (count / 2) * 2;
            len += incr;
            if (count - incr == 1 && !singleLenAdded) {
                len++;
                singleLenAdded = true;
            }
        }

        return len;
    }

}
