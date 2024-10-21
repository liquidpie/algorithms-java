package com.vivek.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Length of the longest substring without repeating characters
 *
 * Given a string str, find the length of the longest substring without repeating characters.
 *
 *     For “ABDEFGABEF”, the longest substring are “BDEFGA” and “DEFGAB”, with length 6.
 *     For “BBBB” the longest substring is “B”, with length 1.
 *     For “GEEKSFORGEEKS”, there are two longest substrings shown in the below diagrams, with length 7
 *
 * The idea is to scan the string from left to right, keep track of the maximum length Non-Repeating Character Substring seen so far in res. When we traverse the string, to know the length of current window we need two indexes.
 * 1) Ending index ( j ) : We consider current index as ending index.
 * 2) Starting index ( i ) : It is same as previous window if current character was not present in the previous window. To check if the current character was present in the previous window or not, we store last index of every character in an `seen` map.
 *                           If we have seen the number, move the start pointer to position after the last occurrence
 *
 * Reference: https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 *
 * Grokking the Coding Interview
 * Pattern: Sliding Window Pattern
 */
public class LongestUniqueSubstring {

    static int longestUniqueSubstring(String str) {

        // Creating a set to store the last positions of occurrence
        Map<Character, Integer> seen = new HashMap<>();
        int max_length = 0;

        // starting the initial point of window to index 0
        int start = 0;

        for (int end = 0; end < str.length(); end++) {

            // Checking if we have already seen the element or not
            if (seen.containsKey(str.charAt(end))) {
                // If we have seen the number, move the start pointer
                // to position after the last occurrence
                start = Math.max(start, seen.get(str.charAt(end)) + 1);
            }

            // Updating the last seen value of the character
            seen.put(str.charAt(end), end);

            max_length = Math.max(max_length, end - start + 1);

        }

        return max_length;

    }

    public static void main(String[] args) {
        System.out.println(longestUniqueSubstring("loremipsum"));
    }

}
