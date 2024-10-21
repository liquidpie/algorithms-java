package com.vivek.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 *
 * Example 1:
 *      Input: String="araaci", K=2
 *      Output: 4
 *      Explanation: The longest substring with no more than '2' distinct characters is "araa".
 *
 * Example 2:
 *      Input: String="araaci", K=1
 *      Output: 2
 *      Explanation: The longest substring with no more than '1' distinct characters is "aa".
 *
 * Example 3:
 *      Input: String="cbbebi", K=3
 *      Output: 5
 *      Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".
 *
 * Solution #
 * This problem follows the Sliding Window pattern. We can use a HashMap to remember the frequency of each character we have processed.
 *
 * Here is how we will solve this problem:
 * 1. First, we will insert characters from the beginning of the string until we have ‘K’ distinct characters in the HashMap.
 * 2. These characters will constitute our sliding window. We are asked to find the longest such window having no more than
 *    ‘K’ distinct characters. We will remember the length of this window as the longest window so far.
 * 3. After this, we will keep adding one character in the sliding window (i.e. slide the window ahead), in a stepwise fashion.
 * 4. In each step, we will try to shrink the window from the beginning if the count of distinct characters in the HashMap
 *    is larger than ‘K’. We will shrink the window until we have no more than ‘K’ distinct characters in the HashMap.
 *    This is needed as we intend to find the longest window.
 * 5. While shrinking, we’ll decrement the frequency of the character going out of the window and remove it from the HashMap
 *    if its frequency becomes zero.
 * 6. At the end of each step, we’ll check if the current window length is the longest so far, and if so, remember its length.
 *
 * Time Complexity:
 * The time complexity of the above algorithm will be O(N )
 * Space Complexity:
 * The space complexity of the algorithm is O(K), as we will be storing a maximum of ‘K+1’ characters in the HashMap.
 *
 * Grokking the Coding Interview
 *
 * Pattern: Sliding Window
 */
public class LongestSubstringKDistinctCharacters {

    public static void main(String[] args) {
        System.out.println(findLength("araaci", 2));
        System.out.println(findLength("araaci", 1));
        System.out.println(findLength("cbbebi", 3));
    }

    static int findLength(String str, int k) {
        char[] arr = str.toCharArray();
        Map<Character, Integer> freq = new HashMap<>();
        int maxLen = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            freq.put(arr[windowEnd], freq.getOrDefault(arr[windowEnd], 0) + 1);

            while (freq.size() > k) {
                freq.put(arr[windowStart], freq.get(arr[windowStart]) - 1);
                if (freq.get(arr[windowStart]) == 0) {
                    freq.remove(arr[windowStart]);
                }
                windowStart++;
            }
            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
        }
        return maxLen;
    }

}
