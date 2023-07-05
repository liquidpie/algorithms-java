package com.vivek.string;

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
 * Approach:
 *
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
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }

    static String minWindow(String s, String t) {
        int[] map = new int[128];
        for (char ch : t.toCharArray()) {
            map[ch]++;
        }

        int start = 0;
        int end = 0;
        int minStart = 0;
        int minLen = Integer.MAX_VALUE;
        int counter = t.length();

        while (end < s.length()) {
            char curr = s.charAt(end);
            // If char in s exists in t, decrease counter
            if (map[curr] > 0 ) {
                counter--;
            }
            // Decrease map[curr]. If char does not exist in t, map[curr] will be negative.
            map[curr]--;
            end++;

            // When we found a valid window, move start to find smaller window.
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }

                char startChar = s.charAt(start);
                map[startChar]++;

                // When char exists in t, increase counter.
                if (map[startChar] > 0) {
                    counter++;
                }
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
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
