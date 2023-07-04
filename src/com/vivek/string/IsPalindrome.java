package com.vivek.string;

/**
 * Valid Palindrome
 *
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example 1:
 *
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 *
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * Example 3:
 *
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 * https://leetcode.com/problems/valid-palindrome/
 */
public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome("0P"));
    }

    static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int n = s.length();
        int l = 0;
        int r = n - 1;
        while (l < r) {
            char lChar = s.charAt(l);
            char rChar = s.charAt(r);
            if (!Character.isLetterOrDigit(lChar))
                l++;
            else if (!Character.isLetterOrDigit(rChar))
                r--;
            else if (lChar != rChar) {
                return false;
            } else {
                l++;
                r--;
            }
        }

        return true;
    }

}
