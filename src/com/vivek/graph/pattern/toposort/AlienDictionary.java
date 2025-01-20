package com.vivek.graph.pattern.toposort;

import java.util.*;

/**
 * Alien Dictionary
 *
 * There is a dictionary containing words from an alien language for which we don’t know the ordering of the
 * characters. Write a method to find the correct order of characters in the alien language.
 *
 * You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in words are
 * sorted lexicographically by the rules of this new language.
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
 *
 * Solution Excerpt from Leetcode:
 * ----------------------------------------------------------------
 * A few things to keep in mind:
 *
 *  - The letters within a word don't tell us anything about the relative order.
 *    For example, the presence of the word kitten in the list does not tell us that the letter k is before the letter i.
 *  - The input can contain words followed by their prefix, for example, abcd and then ab.
 *    These cases will never result in a valid alphabet (because in a valid alphabet, prefixes are always first).
 *    You'll need to make sure your solution detects these cases correctly.
 *  - There can be more than one valid alphabet ordering. It is fine for your algorithm to return any one of them.
 *  - Your output string must contain all unique letters that were within the input list,
 *    including those that could be in any position within the ordering. It should not contain any additional letters that were not in the input.
 *
 * When we have a set of relations, often drawing a graph is the best way to visualize them.
 * The nodes are the letters, and an edge between two letters, A and B represents that A is before B in the "alien alphabet".
 *
 * One edge case we need to be careful of is where a word is followed by its own prefix. In these cases, it is impossible to come up with a valid ordering and so we should return "".
 * The best place to detect it is in the loop that compares each adjacent pair of words.
 *
 * https://leetcode.com/problems/alien-dictionary/solutions/562919/alien-dictionary/
 *
 * Complexity Analysis:
 *
 *  - Let N be the total number of strings in the input list.
 *  - Let C be the total length of all the words in the input list, added together.
 *  - Let U be the total number of unique letters in the alien alphabet.
 *
 *  Time Complexity: O(C)
 *      - In the worst case, the first and second parts require checking every letter of every word (if the difference between two words was always in the last letter). This is O(C).
 *      - For the third part, recall that a breadth-first search has a cost of O(V+E), where V is the number of vertices and E is the number of edges.
 *         Nodes: We know that there is one vertex for each unique letter, i.e. O(U) vertices.
 *         Edges: Each edge in the graph was generated from comparing two adjacent words in the input list. There are N−1 pairs of adjacent words, and only one edge can be generated from each pair.
 *         There is an additional upper limit on the number of edges too—it is impossible for there to be more than one edge between each pair of nodes. With U nodes, this means there can't be more than U^2 edges.
 *         Mathematically, we can say this is min(U^2,N−1).
 *
 *         O(V+E)=O(U+min(U^2,N−1))=O(U+min(U^2,N)).
 *
 *         Adding them together, we initially get the following:
 *              O(C)+O(U+min(U^2,N))=O(C+U+min(U^2,N)).
 *
 *         In summary, C is the biggest of the three, and N and U are smaller, although we don't know which is smaller out of those two.
 *
 *         So in all cases, we know that C>min(U^2,N). This gives us a final time complexity of O(C).
 *
 *  Space Complexity: O(1) or O(U+min(U^2,N)).
 *
 *      - The adjacency list uses the most auxiliary memory. This list uses O(V+E) memory, where V is the number of unique letters,
 *        and E is the number of relations.
 *      - So for the question we're given, where U is a constant fixed at a maximum of 26, the space complexity is simply O(1).
 *      This is because U is fixed at 26, and the number of relations is fixed at 26^2, so O(min(26^2,N))=O(26^2)=O(1).
 *
 *
 * Reference:
 * https://leetcode.com/problems/alien-dictionary/description/
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
            // Check that word2 is not a prefix of word1.
            String word1 = words[i - 1];
            String word2 = words[i];
            if (word1.length() > word2.length() && word1.startsWith(word2))
                return null;
            int j = Math.min(word1.length(), word2.length()); // find the shorter word between consecutive words
            for (int k = 0; k < j; k++) {
                char parent = word1.charAt(k), child = word2.charAt(k);
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
        {
            String[] words = {"cab", "aaa", "aab"};
            System.out.println(findOrder(words));
        }
        {
            String[] words = {"wrt","wrf","er","ett","rftt"};
            System.out.println(findOrder(words));
        }
        {
            String[] words = {"z","x"};
            System.out.println(findOrder(words));
        }
        {
            String[] words = {"z","x"};
            System.out.println(findOrder(words));
        }
        {
            String[] words = {"ac","ab","zc","zb"};
            System.out.println(findOrder(words));
        }
        {
            String[] words = {"abc","ab"};
            System.out.println(findOrder(words));
        }
    }

}
