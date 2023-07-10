package com.vivek.graph.pattern.toposort;

import java.util.*;

/**
 * Alien Dictionary
 *
 * There is a dictionary containing words from an alien language for which we don’t know the ordering of the
 * characters. Write a method to find the correct order of characters in the alien language.
 *
 * Example 1:
 * Input: Words: ["ba", "bc", "ac", "cab"]
 * Output: bac
 * Explanation: Given that the words are sorted lexicographically by the rules of the alien language, so
 * from the given words we can conclude the following ordering among its characters:
 * 1. From "ba" and "bc", we can conclude that 'a' comes before 'c'.
 * 2. From "bc" and "ac", we can conclude that 'b' comes before 'a'
 * From the above two points, we can conclude that the correct character order is: "bac"
 *
 * Example 2:
 * Input: Words: ["cab", "aaa", "aab"]
 * Output: cab
 * Explanation: From the given words we can conclude the following ordering among its characters:
 * 1. From "cab" and "aaa", we can conclude that 'c' comes before 'a'.
 * 2. From "aaa" and "aab", we can conclude that 'a' comes before 'b'
 * From the above two points, we can conclude that the correct character order is: "cab"
 *
 * Solution #
 * Since the given words are sorted lexicographically by the rules of the alien language, we can always compare
 * two adjacent words to determine the ordering of the characters. Take Example-1 above: [“ba”, “bc”, “ac”, “cab”]
 *
 * 1. Take the first two words “ba” and “bc”. Starting from the beginning of the words, find the first character
 * that is different in both words: it would be ‘a’ from “ba” and ‘c’ from “bc”. Because of the sorted order of
 * words (i.e. the dictionary!), we can conclude that ‘a’ comes before ‘c’ in the alien language.
 *
 * 2. Similarly, from “bc” and “ac”, we can conclude that ‘b’ comes before ‘a’.
 * These two points tell us that we are actually asked to find the topological ordering of the characters, and that
 * the ordering rules should be inferred from adjacent words from the alien dictionary.
 * This makes the current problem similar to Tasks Scheduling Order, the only difference being that we need to
 * build the graph of the characters by comparing adjacent words first, and then perform the topological sort for
 * the graph to determine the order of the characters.
 */
public class AlienDictionary {

    static String findOrder(String[] words) {
        if (words == null || words.length == 0)
            return null;

        StringBuilder sortOrder = new StringBuilder();

        // Initialize graph
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                inDegree.put(ch, 0);
                graph.put(ch, new ArrayList<>());
            }
        }

        // Build Graph
        for (int i = 1; i < words.length; i++) {
            int j = Math.min(words[i - 1].length(), words[i].length());
            for (int k = 0; k < j; k++) {
                char parent = words[i - 1].charAt(k), child = words[i].charAt(k);
                if (parent != child) {
                    inDegree.put(child, inDegree.get(child) + 1);
                    graph.get(parent).add(child);
                    break; // only the first different character between the two words will help us find the order
                }
            }
        }

        // Find sources
        Queue<Character> sources = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }

        // Find order
        while (!sources.isEmpty()) {
            Character s = sources.poll();
            sortOrder.append(s);
            for (Character child : graph.get(s)) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) {
                    sources.add(child);
                }
            }
        }

        if (sortOrder.length() != inDegree.size()) {
            return null;
        }

        return sortOrder.toString();
    }

    public static void main(String[] args) {
        String[] words = {"cab", "aaa", "aab"};

        System.out.println(findOrder(words));
    }

}
