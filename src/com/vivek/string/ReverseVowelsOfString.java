package com.vivek.string;

import java.util.Set;

/**
 * Reverse Vowels of a String
 *
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 *
 * Example 1:
 *
 * Input: s = "hello"
 * Output: "holle"
 *
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: "leotcede"
 *
 * https://leetcode.com/problems/reverse-vowels-of-a-string
 */
public class ReverseVowelsOfString {

    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode"));
    }

    static String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int l = 0;
        int r = s.length() - 1;

        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        while (l < r) {
            if (vowels.contains(arr[l]) && vowels.contains(arr[r])) {
                swap(arr, l, r);
                l++;
                r--;
            } else if (vowels.contains(s.charAt(l))) {
                r--;
            } else if (vowels.contains(s.charAt(r))) {
                l++;
            } else {
                l++;
                r--;
            }
        }
        return new String(arr);
    }

    static void swap(char[] arr, int l, int r) {
        char temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

}
