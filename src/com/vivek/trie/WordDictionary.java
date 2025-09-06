package com.vivek.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 211. Design Add and Search Words Data Structure
 *
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 *     WordDictionary() Initializes the object.
 *     void addWord(word) Adds word to the data structure, it can be matched later.
 *     bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 *     word may contain dots '.' where dots can be matched with any letter.
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * Reference: https://leetcode.com/problems/design-add-and-search-words-data-structure
 */
public class WordDictionary {

    private WordDictionary[] children;
    boolean isEndOfWord;

    public WordDictionary() {
        children = new WordDictionary[26];
        isEndOfWord = false;
    }

    public void addWord(String word) {
        WordDictionary current = this;
        for (char ch : word.toCharArray()) {
            if (current.children[ch - 'a'] == null)
                current.children[ch - 'a'] = new WordDictionary();

            current = current.children[ch - 'a'];
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        WordDictionary current = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch == '.') {
                for (WordDictionary child : current.children)
                    if (child != null && child.search(word.substring(i + 1)))
                        return true;
                return false;
            }

            if (current.children[ch - 'a'] == null)
                return false;
            current = current.children[ch - 'a'];

        }
        return current != null && current.isEndOfWord;
    }


    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
//        wordDictionary.addWord("xgvk");
//        wordDictionary.addWord("wykzbvwdsoyfowqicymzd");
//        wordDictionary.addWord("xajbtjyjuwgoynjgu");
//
//        System.out.println(wordDictionary.search("wykzbvwdso..owqicymzd"));
//        System.out.println(wordDictionary.search("..ha"));
//        wordDictionary.addWord("qsibzxaorktypkfg");
//        System.out.println(wordDictionary.search("xgvk"));
//        wordDictionary.addWord("vbycuvrkbcq");
//        System.out.println(wordDictionary.search("qsibz.aorkty.kfg"));
//        wordDictionary.addWord("sm");
//        wordDictionary.addWord("fkqclfmvzpzpnbvz");
//
//        System.out.println(wordDictionary.search("vb..uvrkbcq"));
//        wordDictionary.addWord("jpnneostllnnma");
//        wordDictionary.addWord("zvmtfg");
//        System.out.println(wordDictionary.search("g.."));
//        System.out.println(wordDictionary.search(".kqclfmvzpzpnbvz"));

        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }

}
