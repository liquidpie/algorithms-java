package com.vivek.hashtable;

import java.util.*;

/**
 * 763. Partition Labels
 *
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.
 *
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 *
 * Return a list of integers representing the size of these parts.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 *
 * Example 2:
 *
 * Input: s = "eccbbbbdec"
 * Output: [10]
 *
 * Solution 2:
 * https://leetcode.com/problems/partition-labels/solutions/1868842/java-c-visually-explaineddddd/?envType=study-plan-v2&envId=top-100-liked
 *
 *
 * Reference: https://leetcode.com/problems/partition-labels
 */
public class PartitionLabels {

    public static void main(String[] args) {
        PartitionLabels helper = new PartitionLabels();
        System.out.println(helper.partitionLabels("ababcbacadefegdehijhklij"));
    }

    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastPos = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            lastPos.put(ch, i);
        }

        List<Integer> result = new ArrayList<>();

        int prev = -1;
        int maxPos = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            maxPos = Math.max(maxPos, lastPos.get(ch));

            if (maxPos == i) {
                result.add(i - prev);
                prev = i;
            }
        }
        return result;
    }


    public List<Integer> partitionLabels1(String s) {
        int[] lastPos = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            lastPos[ch - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();

        Set<Character> set = new HashSet<>();
        int prev = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            set.add(ch);

            if (lastPos[ch - 'a'] == i) {
                set.remove(ch);
            }
            if (set.isEmpty()) {
                result.add(i - prev);
                prev = i;
            }
        }
        return result;
    }

}
