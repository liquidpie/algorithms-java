package com.vivek.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 833. Find And Replace in String
 *
 * You are given a 0-indexed string s that you must perform k replacement operations on.
 * The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 *
 * To complete the ith replacement operation:
 *
 *     1. Check if the substring sources[i] occurs at index indices[i] in the original string s.
 *     2. If it does not occur, do nothing.
 *     3. Otherwise, if it does occur, replace that substring with targets[i].
 *
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
 *
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other.
 * The testcases will be generated such that the replacements will not overlap.
 *
 *     - For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
 *
 * Return the resulting string after performing all replacement operations on s.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 * Example 1:
 * Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
 * Output: "eeebffff"
 * Explanation:
 * "a" occurs at index 0 in s, so we replace it with "eee".
 * "cd" occurs at index 2 in s, so we replace it with "ffff".
 *
 * Example 2:
 * Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation:
 * "ab" occurs at index 0 in s, so we replace it with "eee".
 * "ec" does not occur at index 2 in s, so we do nothing.
 *
 * Approach:
 * https://leetcode.com/problems/find-and-replace-in-string/solutions/134758/java-o-n-solution/
 *
 * The technique is like "piece table", which is used in editors.
 * We record all the valid operations first and put them into a piece table, then iterate the string index to "apply" these operations.
 *
 * Reference:
 * https://leetcode.com/problems/find-and-replace-in-string/description/
 */
public class FindAndReplaceMultipleTargets {

    public static void main(String[] args) {
        String s = "abcd";
        int[] indices = {0, 2};
        String[] sources = {"a", "cd"};
        String[] targets = {"eee", "ffff"};
        System.out.println(findReplaceString(s, indices, sources, targets)); // Output: "eeebffff"
    }

    static String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < indices.length; i++) {
            // if a match is found in the original string, record it
            if (s.startsWith(sources[i], indices[i])) {
                table.put(indices[i], i);
            }
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); ) {
            if (table.containsKey(i)) {
                // if a replacement was recorded before
                builder.append(targets[table.get(i)]);
                i += sources[table.get(i)].length();
            } else {
                // if no replacement happened at this index
                builder.append(s.charAt(i));
                i++;
            }
        }

        return builder.toString();
    }

}
