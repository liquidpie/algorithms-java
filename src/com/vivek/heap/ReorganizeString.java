package com.vivek.heap;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;

/**
 * Reorganize String
 *
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: "aba"
 *
 * Example 2:
 *
 * Input: s = "aaab"
 * Output: ""
 *
 * https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {

    public static void main(String[] args) {
        String s = "aab";
        System.out.println(reorganizeString(s));
    }

    static String reorganizeString(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            if (freq.get(ch) > (s.length() + 1) / 2) {
                return "";
            }
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((ch1, ch2) -> freq.get(ch2) - freq.get(ch1));
        maxHeap.addAll(freq.keySet());
        StringBuilder sb = new StringBuilder();

        while (maxHeap.size() >= 2) {
            char ch1 = maxHeap.poll();
            char ch2 = maxHeap.poll();
            sb.append(ch1).append(ch2);
            freq.put(ch1, freq.get(ch1) - 1);
            freq.put(ch2, freq.get(ch2) - 1);
            if (freq.get(ch1) > 0)
                maxHeap.add(ch1);
            if (freq.get(ch2) > 0)
                maxHeap.add(ch2);
        }

        if (maxHeap.size() > 0)
            sb.append(maxHeap.peek());

        return sb.toString();
    }

}
