package com.vivek.string;

/**
 * Implementation of Knuth-Morris-Pratt (KMP) algorithm
 * for Pattern matching
 *
 * Created by VJaiswal on 13/10/17.
 */
public class PatternMatchKMP {

    public static void main(String[] args) {
        String text = "jkamalgamalgamationnjmasjak";
        String pattern = "amalgamation";

        int index = kmpPatternMatching(text, pattern);
        System.out.println(index == -1 ? "No match found" : ("Match found at " + index));
    }

    private static int kmpPatternMatching(String text, String pattern) {
        char[] txt = text.toCharArray();
        char[] pat = pattern.toCharArray();
        int n = txt.length; // text's length
        int m = pat.length; // pattern's length

        if (m == 0) return -1;                  // check for empty string

        int[] failAlign = computeFailKMP(pat);  //

        int i = 0;                              // an index into text
        int k = 0;                              // an index into pattern

        while (i < n) {
            if (txt[i] == pat[k]) {             // a matching character found/not
                if (k == m - 1) {
                    return i - m + 1;           // match found
                }
                i++;
                k++;
            } else if (k > 0) {
                k = failAlign[k - 1];           // shift to next alignment
            } else {
                i++;
            }
        }

        return -1;                              // no match found
    }

    private static int[] computeFailKMP(char[] pattern) {
        int m = pattern.length;
        int[] failAlign = new int[m];               // by default, all overlaps are zero

        int i = 1;
        int k = 0;

        while (i < m) {
            if (pattern[i] == pattern[k]) {         // k + 1 characters match so far
                failAlign[i] = k + 1;
                i++;
                k++;
            } else if (k > 0) {                     // k follows a matching prefix
                k = failAlign[k - 1];
            } else {
                i++;
            }
        }

        return failAlign;
    }


}
