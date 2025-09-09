package com.vivek.string.examples;

/**
 * 520. Detect Capital
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 *     All letters in this word are capitals, like "USA".
 *     All letters in this word are not capitals, like "leetcode".
 *     Only the first letter in this word is capital, like "Google".
 *
 * Given a string word, return true if the usage of capitals in it is right.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "USA"
 * Output: true
 *
 * Example 2:
 *
 * Input: word = "FlaG"
 * Output: false
 *
 * Reference: https://leetcode.com/problems/detect-capital
 */
public class DetectCapital {

    public static void main(String[] args) {
        DetectCapital helper = new DetectCapital();
        System.out.println(helper.detectCapitalUse("USA"));
        System.out.println(helper.detectCapitalUse("leetcode"));
        System.out.println(helper.detectCapitalUse("Google"));
        System.out.println(helper.detectCapitalUse("FlaG"));
    }

    public boolean detectCapitalUse(String word) {
        if (word.length() == 1)
            return true;
        int count = 0; // count upper

        for (char ch : word.toCharArray()) {
            if (isUpper(ch))
                count++;
        }

        if (count == 1 && isUpper(word.charAt(0)))
            return true;
        if (count == 0 || count == word.length())
            return true;

        return false;
    }

    private boolean isUpper(char ch) {
        return ch - 'A' >= 0 && ch - 'A' < 26;
    }

}
