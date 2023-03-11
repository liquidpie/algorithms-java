package com.vivek.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find all possible palindromic substrings in it.
 *
 * The problem differs from the problem of finding the possible palindromic subsequence.
 * Unlike subsequences, substrings are required to occupy consecutive positions within the original string.
 *
 * For example,
 * Input:  str = google
 *
 * Output: e l g o oo goog
 *
 * A simple solution would be to generate all substrings of the given string and print substrings that are palindromes.
 * The time complexity of this solution would be O(n3), where n is the length of the input string.
 *
 * We can solve this problem in O(n2) time and O(1) space. The idea is inspired by the Longest Palindromic Substring problem.
 * For each character in the given string, consider it as the midpoint of a palindrome and
 * expand in both directions to find all palindromes that have it as midpoint.
 * For an even length palindrome, consider every adjacent pair of characters as the midpoint.
 * We use a set to store all unique palindromic substrings.
 *
 * https://www.techiedelight.com/find-possible-palindromic-substrings-string/
 */
public class PrintPalindromicSubstrings {

    public static void main(String[] args) {
        findPalindromicSubstrings("google");
    }

    static void findPalindromicSubstrings(String str) {
        if (str == null)
            return;

        // set to store all palindromic substrings
        Set<String> set = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            // find all odd length palindrome with str[i] as a midpoint
            expand(str, i, i, set);
            // find all even length palindrome with str[i] and str[i+1] as it's midpoints
            expand(str, i, i + 1, set);
        }

        // print all unique palindromic substrings
        System.out.println(set);
    }

    // Expand in both directions of low & high to find all palindromes
    static void expand(String str, int low, int high, Set<String> set) {
        // run till str[low-high] is not a palindrome
        while (low >= 0 && high < str.length() &&
                str.charAt(low) == str.charAt(high)) {
            // push all palindromes into a set
            set.add(str.substring(low, high + 1));

            // expand in both directions
            low--;
            high++;
        }
    }

}
