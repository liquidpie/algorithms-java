package com.vivek.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VJaiswal on 12/10/17.
 */
public class PatternMatchBoyerMoore {

    public static void main(String[] args) {
        String text = "ggdskjsadasdmnbsad";
        String pattern = "sad";

        int index = boyerMoorePatternMatching(text, pattern);
        System.out.println(index == -1 ? "No match found" : ("Match found at " + index));
    }

    private static int boyerMoorePatternMatching(String text, String pattern) {
        char[] txt = text.toCharArray();
        char[] pat = pattern.toCharArray();
        int n = txt.length; // text's length
        int m = pat.length; // pattern's length

        if (m == 0) return -1;                  // check for empty string

        Map<Character, Integer> last = new HashMap<>(); // map to store char indexes

        for (int i = 0; i < n; i++) {
            last.put(txt[i], -1);               // set -1 as default for all text chars
        }

        for (int i = 0; i < m; i++) {
            last.put(pat[i], i);                // rightmost occurrence in pattern is last
        }

        int i = m - 1;                          // an index into text
        int k = m - 1;                          // an index into pattern

        while (i < n) {
            if (txt[i] == pat[k]) {             // a matching character found/not
                if (k == 0) {
                    return i;                   // match found
                }
                i--;
                k--;
            } else {
                i += m - Math.min(k, 1 + last.get(txt[i])); // jump step
                k = m - 1;                      // restart at the end of pattern
            }
        }

        return -1;                              // no match found
    }

}
