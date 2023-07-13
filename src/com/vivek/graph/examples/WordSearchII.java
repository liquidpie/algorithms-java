package com.vivek.graph.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Word Search II
 *
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 * Example 1:
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 * Example 2:
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 * Solution: https://leetcode.com/problems/word-search-ii/solutions/59780/java-15ms-easiest-solution-100-00/
 *
 * Backtracking + Trie
 *
 * Intuitively, start from every cell and try to build a word in the dictionary.
 * Backtracking (dfs) is the powerful way to exhaust every possible ways.
 * Apparently, we need to do pruning when current character is not in any word.
 *
 * - How do we instantly know the current character is invalid? HashMap?
 * - How do we instantly know what's the next valid character? LinkedList?
 * - But the next character can be chosen from a list of characters. "Mutil-LinkedList"?
 *
 * Combing them, Trie is the natural choice. Notice that:
 *
 * - TrieNode is all we need. search and startsWith are useless.
 * - No need to store character at TrieNode. c.next[i] != null is enough.
 * - Never use c1 + c2 + c3. Use StringBuilder.
 * - No need to use O(n^2) extra space visited[m][n].
 * - No need to use StringBuilder. Storing word itself at leaf node is enough.
 * - No need to use HashSet to de-duplicate. Use "one time search" trie.
 *
 * https://leetcode.com/problems/word-search-ii/description/
 */
public class WordSearchII {

    private static final int[] ROWS = {1, -1, 0, 0};
    private static final int[] COLS = {0, 0, 1, -1};

    public static void main(String[] args) {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        System.out.println(findWords(board, words));
    }

    static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        return result;
    }

    private static void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c = board[i][j];
        if (c == '*' || node.next[c - 'a'] == null)
            return;

        node = node.next[c - 'a'];

        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        board[i][j] = '*';
        for (int k = 0; k < 4; k++) {
            int row = i + ROWS[k];
            int col = j + COLS[k];
            if (row >= 0 && row < board.length && col >= 0 && col < board[0].length)
                dfs(board, row, col, node, result);
        }
        board[i][j] = c;
    }

    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                    node = node.next[c - 'a'];
                }
            }
            node.word = word;
        }
        return root;
    }

    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

}
