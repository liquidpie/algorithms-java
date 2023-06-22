package com.vivek.graph.examples;

import java.util.*;

/**
 * Word Ladder
 *
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * Idea:
 *  1. Since we need to find the shortest path length from source to target, the key idea is to use Breadth-First Search (BFS) for a Graph.
 *  2. First, store all words from wordList to a set, to remove duplicate words.
 *  3. Perform BFS from beginWord and each time, pop out the front element string from the queue and
 *      visit all adjacent strings of current string(strings differing by one character and are present in wordList)
 *      push them into the queue, and, mark them as visited by removing its entry from words set.
 *  4. For finding adjacent strings, replace every character at every index of a current string with a new character and
 *      check its entry in the words set(intermediate words).
 *  5. Whenever we’ll find endWord, return the level of the endWord since BFS always yields the shortest path.
 *
 * Solution: https://tutorialcup.com/leetcode-solutions/word-ladder-leetcode-solution.htm
 *
 * Time Complexity ::
 * The time complexity of the above code is O(n*m^2) since the queue operates each word in the worst case(there are n-words),
 * and for each word has max length as m, so a total number of iterations is n*m. Also, for each character of the string,
 * we are making a copy of the current string which takes O(m) time again.
 * Hence, the overall complexity of the solution is O(n*m^2)
 *
 * Space Complexity ::
 * The space complexity of the above code is O(n*m^2) since we have total n-words and for each is of max length as m.
 * Also, for each word, we’re forming the intermediate word which takes O(m) space.
 *
 * https://leetcode.com/problems/word-ladder
 */
public class WordLadder {

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = List.of("hot","dot","dog","lot","log","cog");

        System.out.println(ladderLength(beginWord, endWord, wordList));
    }

    static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord))
            return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int level = 0;
        words.remove(beginWord);

        while (!queue.isEmpty()) {
            level++;
            for (int i = 0; i < queue.size(); i++) {
                StringBuilder word = new StringBuilder(queue.poll());
                for (int j = 0; j < word.length(); j++) {
                    char cache = word.charAt(j);
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        word.setCharAt(j, ch);
                        String newWord = word.toString();
                        if (newWord.equals(endWord))
                            return level + 1;
                        if (words.remove(newWord)) {
                            queue.add(newWord);
                        }
                    }
                    word.setCharAt(j, cache);
                }
            }
        }

        return 0;
    }

}
