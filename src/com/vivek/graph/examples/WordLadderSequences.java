package com.vivek.graph.examples;

import java.util.*;

/**
 * Word Ladder II
 *
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * Solution: https://walkccc.me/LeetCode/problems/0126/
 *
 * https://leetcode.com/problems/word-ladder-ii/
 */
public class WordLadderSequences {

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = List.of("hot","dot","dog","lot","log","cog");

        System.out.println(findLadders(beginWord, endWord, wordList));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return new ArrayList<>();

        // {"hit": ["hot"], "hot": ["dot", "lot"], ...}
        Map<String, List<String>> graph = new HashMap<>();

        // Build graph from beginWord -> endWord
        if (!bfs(beginWord, endWord, wordSet, graph))
            return new ArrayList<>();

        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>(Arrays.asList(beginWord));
        dfs(graph, beginWord, endWord, path, ans);
        return ans;
    }

    private static boolean bfs(final String beginWord, final String endWord, Set<String> wordSet,
                        Map<String, List<String>> graph) {
        Set<String> currentLevelWords = new HashSet<>();
        currentLevelWords.add(beginWord);
        boolean reachEndWord = false;

        while (!currentLevelWords.isEmpty()) {
            for (final String word : currentLevelWords)
                wordSet.remove(word);
            Set<String> nextLevelWords = new HashSet<>();
            for (final String parent : currentLevelWords) {
                graph.putIfAbsent(parent, new ArrayList<>());
                for (final String child : getChildren(parent, wordSet)) {
                    if (wordSet.contains(child)) {
                        nextLevelWords.add(child);
                        graph.get(parent).add(child);
                    }
                    if (child.equals(endWord))
                        reachEndWord = true;
                }
            }
            if (reachEndWord)
                return true;
            currentLevelWords = nextLevelWords;
        }

        return false;
    }

    private static List<String> getChildren(final String parent, Set<String> wordSet) {
        List<String> children = new ArrayList<>();
        StringBuilder sb = new StringBuilder(parent);

        for (int i = 0; i < sb.length(); ++i) {
            final char cache = sb.charAt(i);
            for (char c = 'a'; c <= 'z'; ++c) {
                if (c == cache)
                    continue;
                sb.setCharAt(i, c);
                final String child = sb.toString();
                if (wordSet.contains(child))
                    children.add(child);
            }
            sb.setCharAt(i, cache);
        }

        return children;
    }

    private static void dfs(Map<String, List<String>> graph, final String word, final String endWord,
                     List<String> path, List<List<String>> ans) {
        if (word.equals(endWord)) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (!graph.containsKey(word))
            return;

        for (final String child : graph.get(word)) {
            path.add(child);
            dfs(graph, child, endWord, path, ans);
            path.remove(path.size() - 1);
        }
    }

}
