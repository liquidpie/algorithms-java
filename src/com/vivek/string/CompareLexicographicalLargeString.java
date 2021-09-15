package com.vivek.string;

/**
 * Check if any permutation of a given string is lexicographically larger than the other given string
 *
 * Given two strings str1 and str2 of same length N, the task is to check if there exists any permutation possible in any of the given strings,
 * such that every character of one string is greater or equal to every character of the other string, at corresponding indices.
 * Return true if permutation exists otherwise false.
 *
 * Example:
 *
 *     Input: str1 = “adb”, str2 = “cda”
 *     Output: true
 *     Explanation: After permutation str1 = “abd” and str2 = “acd”, so every character from str2 is greater or equals
 *     to every character from s1.
 *
 *     Input: str1 = “gfg”, str2 = “agd”
 *     Output: true
 */
public class CompareLexicographicalLargeString {

    static boolean isPermutationLexicographicalLarge(String str1, String str2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (int i = 0; i < str1.length(); i++) {
            freq1[str1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < str2.length(); i++) {
            freq2[str2.charAt(i) - 'a']++;
        }

        int count1 = 0;
        int count2 = 0;

        boolean str1Smaller = false;
        boolean str2Smaller = false;

        for (int i = 0; i < 26; i++) {
            count1 += freq1[i];
            count2 += freq2[i];

            if (count1 < count2) {
                if (str2Smaller)
                    return false;

                str1Smaller = true;
            } else if (count2 < count1) {
                if (str1Smaller)
                    return false;

                str2Smaller = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPermutationLexicographicalLarge("ccccc", "aaa"));
    }

}
