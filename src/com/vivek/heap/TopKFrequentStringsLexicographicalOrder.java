package com.vivek.heap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Top K Frequent Words
 *
 * Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 * Example 1:
 *
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 *
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * https://leetcode.com/problems/top-k-frequent-words
 */
public class TopKFrequentStringsLexicographicalOrder {

    public static void main(String[] args) {
        String[] words = {"i","love","leetcode","i","love","coding"};
        int k = 2;
        System.out.println(topKFrequent(words, k));
    }

    static List<String> topKFrequent(String[] words, int k) {
        Map<String, Word> map = new HashMap<>();
        for (String word : words) {
            map.putIfAbsent(word, new Word(word));
            map.get(word).freq++;
        }

        PriorityQueue<Word> pq = new PriorityQueue<>((w1, w2) -> {
            // frequency is highest to lowest and lexicographical ordering is lowest to highest
            int compare = Integer.compare(w1.freq, w2.freq);
            return compare == 0 ? w2.word.compareTo(w1.word) : compare;
        });
        pq.addAll(map.values());

        while (pq.size() > k) {
            pq.poll();
        }

        List<String> out = new LinkedList<>();
        while (!pq.isEmpty()) {
            out.add(0, pq.poll().word);
        }
        return out;
    }

    static class Word {
        String word;
        int freq;

        Word(String word) {
            this.word = word;
        }
    }

}
