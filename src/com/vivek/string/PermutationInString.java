package com.vivek.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Permutation in a String (hard) #
 *
 * Given a string and a pattern, find out if the string contains any permutation of the pattern.
 * Permutation is defined as the re-arranging of the characters of the string. For example, “abc” has the following six permutations:
 * 1. abc 2. acb 3. bac 4. bca 5. cab 6. cba
 * If a string has ‘n’ distinct characters it will have n! permutations.
 *
 * Example 1:
 *      Input: String="oidbcaf", Pattern="abc"
 *      Output: true
 *      Explanation: The string contains "bca" which is a permutation of the given pattern.
 *
 * Example 2:
 *      Input: String="odicf", Pattern="dc"
 *      Output: false
 *      Explanation: No permutation of the pattern is present in the given string as a substring.
 *
 * Example 3:
 *      Input: String="bcdxabcdy", Pattern="bcdyabcdx"
 *      Output: true
 *      Explanation: Both the string and the pattern are a permutation of each other.
 *
 * Example 4:
 *      Input: String="aaacb", Pattern="abc"
 *      Output: true
 *      Explanation: The string contains "acb" which is a permutation of the given pattern.
 *
 *
 */
public class PermutationInString {

    public static void main(String[] args) {
        System.out.println(findPermutation("oidbcaf", "abc"));
        System.out.println(findPermutation("odicf", "dc"));
    }

    public static boolean findPermutation(String str, String pattern) {
        Map<Character, Integer> patternFreq = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            patternFreq.put(ch, patternFreq.getOrDefault(ch, 0) + 1);
        }

        int windowStart = 0;
        int matched = 0;

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (patternFreq.containsKey(rightChar)) {
                patternFreq.put(rightChar, patternFreq.get(rightChar) - 1);
                if (patternFreq.get(rightChar) == 0) {
                    matched++;
                }
            }

            if (matched == patternFreq.size()) {
                return true;
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

        return false;
    }

}
