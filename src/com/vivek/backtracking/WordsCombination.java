package com.vivek.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Given a two-dimensional array of strings, return all possible combination of words.
 *
 * Example:
 * ['grey','black']
 * ['fox','dog']
 * ['jumped','ran','growled']
 *
 * Return:
 * grey fox jumped
 * grey fox ran
 * grey fox growled
 * black fox jumped
 * ...
 * black dog growled
 *
 * https://leetcode.com/discuss/interview-question/1135847/facebook-phone-screen-combination
 */
public class WordsCombination {

    public static void main(String[] args) {
        List<List<String>> wordList = new ArrayList<>();
        wordList.add(List.of("grey", "black"));
        wordList.add(List.of("fox", "dog"));
        wordList.add(List.of("jumped", "ran", "growled"));

        System.out.println(wordCombination(wordList));

    }

    private static List<String> wordCombination(List<List<String>> wordList) {
        List<String> combinations = new ArrayList<>();

        backtrack(wordList, combinations, 0, new ArrayList<>());

        return combinations;
    }

    private static void backtrack(List<List<String>> wordList, List<String> combinations, int index, List<String> path) {
        if (path.size() == wordList.size()) {
            combinations.add(String.join(" ", path));
            return;
        }

        List<String> words = wordList.get(index);
        for (String word : words) {
            path.add(word);
            backtrack(wordList, combinations, index + 1, path);
            path.remove(path.size() - 1);
        }
    }

}
