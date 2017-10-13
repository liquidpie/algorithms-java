package com.vivek.trie;

/**
 * Created by VJaiswal on 13/10/17.
 */
public class Trie {

    // Alphabet Size - Number of symbols
    private static final int ALPHABET_SIZE = 26;

    private static TrieNode root = new TrieNode();

    // trie node
    static class TrieNode {

        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // true if the node represents end of a word
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }
    }

    void insert(String key) {

        int level;
        int length = key.length();
        int index;

        TrieNode node = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }

        // mark last node as leaf node
        node.isEndOfWord = true;
    }

    boolean search(String key) {

        int level;
        int length = key.length();
        int index;

        TrieNode node = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }

        return (node != null && node.isEndOfWord);
    }

    public static void main(String[] args) {
        String[] keys = {"quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        Trie trie = new Trie();

        for (int i = 0; i < keys.length; i++)
            trie.insert(keys[i]);

        System.out.println(trie.search("quick"));
    }

}
