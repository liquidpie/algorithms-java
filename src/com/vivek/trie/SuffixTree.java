package com.vivek.trie;

/**
 * A Suffix Tree for a given text is a compressed trie for all suffixes of the given text.
 *
 * Suffix tree can be used for a wide range of problems.
 * Following are some famous problems where Suffix Trees provide optimal time complexity solution.
 * 1) Pattern Searching
 * 2) Finding the longest repeated substring
 * 3) Finding the longest common substring
 * 4) Finding the longest palindrome in a string
 *
 * https://www.geeksforgeeks.org/pattern-searching-using-suffix-tree/
 */
public class SuffixTree {

    /**
     *
     * Given a string of length n of lowercase alphabet characters, we need to count total number of distinct substrings of this string.
     * Examples:
     *
     * Input  : str = “ababa”
     * Output : 10
     * Total number of distinct substring are 10, which are,
     * "", "a", "b", "ab", "ba", "aba", "bab", "abab", "baba"
     * and "ababa"
     *
     * The idea is create a Trie of all suffixes of given string. Once the Trie is constricted, our answer is total number of nodes in the constructed Trie.
     *
     * https://www.geeksforgeeks.org/count-distinct-substrings-string-using-suffix-trie/
     */
    static int countDistinctSubstring(String str) {
        SuffixTrie trie = new SuffixTrie(str);
        return trie.countNodesInTrie();
    }

    private static class SuffixTrieNode {
        static final int MAX_CHAR = 26;
        SuffixTrieNode[] children = new SuffixTrieNode[MAX_CHAR];

        SuffixTrieNode() {
            for (int i = 0; i < MAX_CHAR; i++) {
                children[i] = null;
            }
        }

        // A recursive function to insert a suffix of the s in subtree rooted with this node
        void insertSuffix(String s) {
            if (s.length() > 0) {
                int index = s.charAt(0) - 'a';

                // If there is no edge for this character, add a new edge
                if (children[index] == null) {
                    children[index] = new SuffixTrieNode();
                }

                // recur for next suffix
                children[index].insertSuffix(s.substring(1));
            }
        }
    }

    private static class SuffixTrie {
        static final int MAX_CHAR = 26;
        SuffixTrieNode root;

        public SuffixTrie(String s) {
            root = new SuffixTrieNode();

            // Consider all suffixes of given string and insert them into the Suffix Trie using recursive function
            // insertSuffix() in SuffixTrieNode class
            for (int i = 0; i < s.length(); i++) {
                root.insertSuffix(s.substring(i));
            }
        }

        public int countNodesInTrie() {
            return countNodesInSubTrie(root);
        }

        // A recursive function to count nodes in sub-trie
        private int countNodesInSubTrie(SuffixTrieNode node) {
            // If all characters of pattern have been processed,
            if (node == null)
                return 0;

            int count  = 0;
            for (int i = 0; i < MAX_CHAR; i++) {
                // if children is not NULL then find count of all nodes in this subtrie
                if (node.children[i] != null) {
                    count += countNodesInSubTrie(node.children[i]);
                }
            }

            // return count of nodes of subtrie and plus 1 because of node's own count
            return (1 + count);
        }
    }

    public static void main(String[] args) {
        String str = "ababa";
        System.out.println(countDistinctSubstring(str));
    }

}
