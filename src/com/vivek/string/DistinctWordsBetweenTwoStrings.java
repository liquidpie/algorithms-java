package com.vivek.string;

import java.util.*;

/**
 * Given two statements/strings S and T, return a list of all the words in S that do not appear in T.
 */
public class DistinctWordsBetweenTwoStrings {

    public static void main(String[] args) {
        String s = "this apple is sweet a";
        String t = "this apple is sour d f g ";

        List<String> distinctWords = distinctWords(s, t);
        System.out.println(distinctWords);
    }

    static List<String> distinctWords(String s, String t) {
        String[] sArr = s.split("\\s+");
        String[] tArr = t.split("\\s+");
        Map<String, Integer> sArrFreq = new HashMap<>();

        for (String word : sArr) {
            sArrFreq.put(word, sArrFreq.getOrDefault(word, 0) + 1);
        }

        for (String word : tArr) {
            if (sArrFreq.containsKey(word)) {
                if (sArrFreq.get(word) == 1)
                    sArrFreq.remove(word);
                else
                    sArrFreq.put(word, sArrFreq.get(word) - 1);
            }
        }

        return new ArrayList<>(sArrFreq.keySet());
    }

}
