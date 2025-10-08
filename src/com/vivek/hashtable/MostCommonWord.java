package com.vivek.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 819. Most Common Word
 *
 * Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned. It is guaranteed there is at least one word that is not banned, and that the answer is unique.
 *
 * The words in paragraph are case-insensitive and the answer should be returned in lowercase.
 *
 * Note that words can not contain punctuation symbols.
 *
 *
 *
 * Example 1:
 *
 * Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 *
 * Example 2:
 *
 * Input: paragraph = "a.", banned = []
 * Output: "a"
 *
 * Reference:
 * https://leetcode.com/problems/most-common-word/description/
 */
public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {
        String mostCommon = null;
        int maxfreq = 0;
        Map<String, Integer> wordFreq = new HashMap<>();
        Set<String> bannedWords = new HashSet<>();

        for (String word : banned)
            bannedWords.add(word.toLowerCase());

        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(paragraph);

        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            if (bannedWords.contains(word))
                continue;
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
            if (wordFreq.get(word) > maxfreq) {
                maxfreq = wordFreq.get(word);
                mostCommon = word;
            }
        }

        return mostCommon;
    }

}
