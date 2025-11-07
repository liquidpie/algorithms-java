package com.vivek.array.pattern.twopointer;

/**
 * 844. Backspace String Compare
 *
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 *
 *
 * Example 1:
 *
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 *
 * Example 2:
 *
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 *
 * Example 3:
 *
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 *
 * Solution:
 * The code is designed to determine whether two strings are equal after applying backspace operations.
 * It compares the two strings character by character after processing the backspace operations.
 *
 * https://leetcode.com/problems/backspace-string-compare/solutions/6280021/video-give-me-10-minutes-how-we-think-ab-fq2w/
 *
 * Reference: https://leetcode.com/problems/backspace-string-compare/
 */
public class BackspaceStringCompare {

    public static void main(String[] args) {
        BackspaceStringCompare helper = new BackspaceStringCompare();
//        String s = "bxj##tw", t = "bxo#j##tw";
        String s = "du###vu##v#fbtu", t = "du###vu##v##fbtu";
        System.out.println(helper.backspaceCompare(s, t));
    }

    public boolean backspaceCompare(String s, String t) {
        int ps = s.length() - 1;
        int pt = t.length() - 1;

        while (ps >= 0 || pt >= 0) {
            ps = nextValidChar(s, ps);
            pt = nextValidChar(t, pt);

            if (ps < 0 && pt < 0) {
                return true;
            }
            if (ps < 0 || pt < 0) {
                return false;
            }
            if (s.charAt(ps) != t.charAt(pt)) {
                return false;
            }
            ps--;
            pt--;
        }
        return true;
    }

    private int nextValidChar(String str, int end) {
        int backspace = 0;
        while (end >= 0) {
            if (str.charAt(end) == '#') {
                backspace++;
            } else if (backspace > 0) {
                backspace--;
            } else {
                break;
            }
            end--;
        }
        return end;
    }

}
