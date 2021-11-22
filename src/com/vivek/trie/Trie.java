package com.vivek.trie;

/**
 * Created by VJaiswal on 13/10/17.
 */
public class Trie {

    public enum CHAR_CASE {LOWERCASE, UPPERCASE}

    // Alphabet Size - Number of symbols
    private static final int ALPHABET_SIZE = 26;

    private final TrieNode root = new TrieNode();

    private final char base;

    public Trie() {
        this.base = 'a';
    }

    public Trie(CHAR_CASE char_case) {
        this.base = char_case == CHAR_CASE.UPPERCASE ? 'A' : 'a';
    }

    // trie node
    public static class TrieNode {

        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // true if the node represents end of a word
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }

        public TrieNode getChild(int i) {
            return children[i];
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String key) {

        int level;
        int length = key.length();
        int index;

        TrieNode node = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - base;
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }

        // mark last node as leaf node
        node.isEndOfWord = true;
    }

    public boolean search(String key) {

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
