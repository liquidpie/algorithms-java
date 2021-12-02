package com.vivek.string;

import java.util.*;

/**
 * Given a sequence of words, print all anagrams together
 *
 * Using hashmap
 * Here, we first sort each word, use <sorted word> as key and then put original word in a map.
 * The value of the map will be a list containing all the words which have same word after sorting.
 */
public class PrintAllAnagramsTogether {

    static List<List<String>> printAnagrams(String[] words) {
        Map<String, List<String>> hashtable = new HashMap<>();
        for (String word : words) {
            String sorted = sort(word);
            List<String> strings = hashtable.getOrDefault(sorted, new ArrayList<>());
            strings.add(word);
            hashtable.put(sorted, strings);
        }

        return new ArrayList<>(hashtable.values());
    }

    private static String sort(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        String[] words = {"cat", "tac", "act", "st", "ts", "aa"};
        System.out.println(printAnagrams(words));
    }

}
