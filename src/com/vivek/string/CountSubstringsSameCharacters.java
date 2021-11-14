package com.vivek.string;

/**
 * Count number of substrings of a string consisting of same characters
 *
 * Given a string. The task is to find out the numbers of substrings consisting of the same characters.
 *
 * Examples:
 * Input: abba
 * Output: 5
 * The desired substrings are {a}, {b}, {b}, {a}, {bb}
 *
 * Input: bbbcbb
 * Output: 10
 *
 * Approach: It is known for a string of length n, there are a total of n*(n+1)/2 number of substrings.
 * Let’s initialize the result to 0. Traverse the string and find the number of consecutive element(let’s say count) of same characters.
 * Whenever we find another character, increment the result by count*(count+1)/2, set count to 1, and from that index, repeat the above process.
 * Remember, for each different character, the number of our desired substring is 1.
 *
 * Reference: https://www.geeksforgeeks.org/count-number-of-substrings-of-a-string-consisting-of-same-characters
 */
public class CountSubstringsSameCharacters {

    static int countSubstringOfSameChars(String str) {
        int n = str.length();
        int count = 1;
        int result = 0;

        int left = 0;
        int right = 1;
        while (right < n) {
            if (str.charAt(left) == str.charAt(right)) {
                count++;
            } else {
                result += ((count * (count + 1)) / 2);
                count = 1;
                left = right;
            }
            right++;
        }
        // Store the final value of result
        result += count * (count + 1) / 2;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(countSubstringOfSameChars("abba"));
    }

}
