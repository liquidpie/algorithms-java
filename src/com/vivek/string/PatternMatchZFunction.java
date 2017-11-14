package com.vivek.string;

/**
 * Suppose we are given a string s of length n.
 * The Z-function for this string is an array of length n where the i-th element is equal to
 * the greatest number of characters starting from the position i that coincide with the first characters of s.
 *
 * In other words, z[i] is the length of the longest common prefix between s and the suffix of s starting at i.
 *
 * For ex:
 *
 * s = 'aaaaa'
 *
 * z[0] = 0,
 * z[1] = 4,
 * z[2] = 3,
 * z[3] = 2,
 * z[4] = 1.
 *
 * s = 'aaabaab'
 *
 * z[0] = 0,
 * z[1] = 2,
 * z[2] = 1,
 * z[3] = 0,
 * z[4] = 2,
 * z[5] = 1,
 * z[6] = 0.
 *
 * https://ivanyu.me/blog/2013/10/15/z-algorithm/
 *
 */
public class PatternMatchZFunction {

    int[] zArray(String s) {

        int n = s.length();
        int[] z = new int[n];

        for (int i = 1, l = 0, r = 0; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }

            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                ++z[i];
            }

            if (i + z[i] - 1 >  r) {
                l = i;
                r = i + z[i] - 1;
            }
        }

        return z;

    }

}
