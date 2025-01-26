package com.vivek.array.examples;

/**
 * Given a list of words {"java", "c++", "c#","git","java"}.
 * The function takes 2 words that always belong to the list. Find the minimum distance between the words in terms of postition.
 *
 * Eg input->java,git
 * java found at indices 0,4
 * git at 3
 * so minimum distance is (4-3=1) and NOT (3-0=0)
 *
 * Approach:
 *
 * TC : O(N)
 * SC : O(1)
 * Dry run :
 * List: ["java", "c++", "c#", "git", "java"]
 * Target words: "java", "git"
 *
 * Index 0: "java" → last_w1_index = 0
 * Index 3: "git" → last_w2_index = 3 → distance = min(distance , abs(last_w2_index - last_w1_index)) = 3
 * Index 4: "java" → last_w1_index = 4 → distance = min(distance , abs(last_w2_index - last_w1_index)) = 1
 * Result: Minimum distance = 1
 *
 * Reference:
 * https://leetcode.com/discuss/interview-question/6128889/LinkedIn-Interview-question-for-Senior-Software-Engineer/
 */
public class MinDistanceBetweenTwoWords {

    static int minDistance(String[] words, String word1, String word2) {
        int word1LastIndex = -1;
        int word2LastIndex = -1;

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                word1LastIndex = i;
            }
            if (word2.equals(words[i])) {
                word2LastIndex = i;
            }
            minDistance = Math.min(minDistance, Math.abs(word1LastIndex - word2LastIndex));
        }

        return minDistance;
    }

    public static void main(String[] args) {
        String[] words = {"java", "c++", "c#", "git", "java"};
        System.out.println(minDistance(words, "java", "git"));
    }

}
