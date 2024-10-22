package com.vivek.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Minimum Window Substring (Shortest subarray containing all chars of another string)
 *
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring (or subarray) of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 *
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 *
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 * Solution #
 * This problem follows the Sliding Window pattern and has a lot of similarities with Permutation in a String with one difference.
 * In this problem, we need to find a substring having all characters of the pattern which means that the required substring
 * can have some additional characters and doesn’t need to be a permutation of the pattern.
 *
 * Here is how we will manage these differences:
 * 1. We will keep a running count of every matching instance of a character.
 * 2. Whenever we have matched all the characters, we will try to shrink the window from the beginning,
 * keeping track of the smallest substring that has all the matching characters.
 * 3. We will stop the shrinking process as soon as we remove a matched character from the sliding window.
 * One thing to note here is that we could have redundant matching characters, e.g., we might have two ‘a’ in
 * the sliding window when we only need one ‘a’. In that case, when we encounter the first ‘a’, we will simply shrink
 * the window without decrementing the matched count. We will decrement the matched count when the second ‘a’ goes out of the window.
 *
 * Time Complexity #
 * The time complexity of the above algorithm will be O(N + M ) where ‘N’ and ‘M’ are the number of
 * characters in the input string and the pattern respectively.
 *
 * Space Complexity #
 * The space complexity of the algorithm is O(M ) since in the worst case, the whole pattern can have distinct characters
 * which will go into the HashMap. In the worst case, we also need O(N ) space for the resulting substring,
 * which will happen when the input string is a permutation of the pattern.
 *
 * Approach:
 * For most substring problem, we are given a string and need to find a substring of it which satisfy some restrictions.
 * A general way is to use a hashmap assisted with two pointers. The template is given below.
 *
 * 1. Use two pointers: start and end to represent a window.
 * 2. Move end to find a valid window.
 * 3. When a valid window is found, move start to find a smaller window.
 *
 * To check if a window is valid, we use a map to store (char, count) for chars in t.
 * And use counter for the number of chars of t to be found in s. The key part is m[s[end]]--;.
 * We decrease count for each char in s. If it does not exist in t, the count will be negative.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Sliding Window
 *
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(findSubstring("ADOBECODEBANC", "ABC"));
        System.out.println(findSubstring("aabdec", "abc"));
        System.out.println(findSubstring("abdabca", "abc"));
        System.out.println(findSubstring("adcad", "abc"));
    }

    private static String findSubstring(String str, String pattern) {
        int minLen = Integer.MAX_VALUE;
        int windowStart = 0;
        int subStrStart = 0;
        int matched = 0;

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (freqMap.containsKey(rightChar)) {
                freqMap.put(rightChar, freqMap.get(rightChar) - 1);
                if (freqMap.get(rightChar) >= 0) { // count every matching of a character
                    matched++;
                }
            }

            // shrink the window if we can, finish as soon as we remove a matched character
            while (matched == pattern.length()) {
                if (minLen > windowEnd - windowStart + 1) {
                    minLen = windowEnd - windowStart + 1;
                    subStrStart = windowStart;
                }

                char leftChar = str.charAt(windowStart);
                if (freqMap.containsKey(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll decrement the
                    // matched count only when a useful occurrence of a matched character is going out of the window
                    if (freqMap.get(leftChar) == 0) {
                        matched--;
                    }
                    freqMap.put(leftChar, freqMap.get(leftChar) + 1);
                }
                windowStart++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : str.substring(subStrStart, subStrStart + minLen);
    }

    // Template -->
//    int findSubstring(string s){
//        vector<int> map(128,0);
//        int counter; // check whether the substring is valid
//        int begin=0, end=0; //two pointers, one point to tail and one  head
//        int d; //the length of substring
//
//        for() { /* initialize the hash map here */ }
//
//        while(end<s.size()){
//
//            if(map[s[end++]]-- ?){  /* modify counter here */ }
//
//            while(/* counter condition */){
//
//                /* update d here if finding minimum*/
//
//                //increase begin to make it invalid/valid again
//
//                if(map[s[begin++]]++ ?){ /*modify counter here*/ }
//            }
//
//            /* update d here if finding maximum*/
//        }
//        return d;
//    }

}
