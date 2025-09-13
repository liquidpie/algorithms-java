package com.vivek.string.examples;

/**
 * 925. Long Pressed Name
 *
 * Your friend is typing his name into a keyboard. Sometimes, when typing a character c, the key might get long pressed,
 * and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard. Return True if it is possible that it was your friends name,
 * with some characters (possibly none) being long pressed.
 *
 * Example 1:
 *
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 *
 * Example 2:
 *
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it was not in the typed output.
 *
 * Reference: https://leetcode.com/problems/long-pressed-name
 */
public class LongPressedName {

    public static void main(String[] args) {
        LongPressedName helper = new LongPressedName();
        System.out.println(helper.isLongPressedName("saeed", "ssaaedd"));
        System.out.println(helper.isLongPressedName("vtkgn", "vttkgnn"));
    }

    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;

        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
            } else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }
            j++;
        }

        return i == name.length();
    }

}
