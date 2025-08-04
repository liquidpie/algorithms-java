package com.vivek.array.pattern.slidingwindow;

import java.util.*;

/**
 * 30. Substring with Concatenation of All Words
 *
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 *
 * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
 *
 *     For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are
 *     all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
 *
 * Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.
 *
 *
 * Example 1:
 *
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 *
 * Output: [0,9]
 *
 * Explanation:
 *
 * The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
 * The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
 *
 * Example 2:
 *
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 *
 * Output: []
 *
 * Explanation:
 *
 * There is no concatenated substring.
 *
 * Example 3:
 *
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 *
 * Output: [6,9,12]
 *
 * Explanation:
 *
 * The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
 * The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
 * The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
 *
 *
 * Solution ::
 * ----------
 * Q1) How do we even find a permutation?
 * Ans: We iterate through our string s in question, one word at a time. We know that in the words array, each word is of the same size (lets say wordSize).
 * So if we iterate on our string s by a length of wordSize, then on each iteration we get a word. So we start the window and
 * on each iteration keep adding a word to it and VOILA! you have got a permutation.
 *
 * Q2) How do we know if the permutation is valid?
 * Ans: So, a valid permutation will have all the words of the words array in any possible order. So, to check if our current window is a possible permutation, we check:-
 *
 *     If our current window has all the words from words array,
 *     AND if the count of each of these words in the window is the same as the count in words array. For example,
 *
 * words = [bar, foo, bar]
 * permutation1 = foobarfoo //NOT VALID since foo is twice and bar is once
 * permutation2 = barbabyfoobar //NOT VALID since we have 'baby', which is not even in the words array.
 * permutation3 = barfoobar //VALID, since in words bar is twice and foo is once
 *
 * Q3) How do we keep track if the counts are correct?
 * Ans: We maintain TWO hashmaps. One hashmap originalCount keeps the original counts of each word from the words array.
 * The second hashmap currentCount keeps the count of the words in our current window. So each time we get a new word,
 * we first check if the word is in the originalCount. If it is, we add and keep count of the current word currWord in our hashmap currentCount.
 * ALSO we keep a variable count, which is the number of valid words in our window. Everytime we encounter a valid word, we increment the count variable by 1.
 * So, a window is a valid permutation only if count is equal to length of words.
 *
 * Q4) What if we encounter a word several times in a window?
 * Ans: Lets say, we encounter currWord in the window more times than it was in the original words array.
 * Thus, in our window map currentCount the count of currWord is more than its respective count in the map originalCount.
 * Well we just shorten the window from the left and every word we encounter, we respectively decrement its count by 1 from currentCount map.
 * We shorten the window and do this whole operation until the count of currWord is same in both the maps.
 *
 * Q5) What if we encounter a word that is not in the originalCount map?
 * Ans: This means current window is invalid and we discared this whole window and start building a new window again from the next word.
 * We reset count to 0. We reset the whole currentCount map.
 *
 * THE TWIST
 *
 * Well, this was the happy-go-lucky barbie-in-fairyland kinda flow. We considered that the first word we get in the string WILL be a valid word.
 * BUT what if it isnt. For example what if it is xfoobarbar.
 * Since we are iterating a word at a time, the first word is xfo, the second word is oba and so on.
 * To tackle this, here comes the last piece of our puzzle: OFFSET
 * We know that each word in words array is same length, lets consider it wordSize
 *
 * Lets consider this example xfoobarbar here we know the valid word starts at 1st index, the next valid at 4th index, and so on.
 * For xxfoobarbar here the valid word starts at 2nd index, the next valid at 5th index, and so on.
 * For foobarbar here the valid word starts at 0th index, the next valid at 3rd index, and so on.
 * So basically we need to iterate over the string s first starting from 0th index, then 1st, then 2nd...then (wordSize-1)th.
 * Thus, all the 5 operations discussed above need to be done wordSize-1 times so that we do not miss any possible permutation.
 *
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/solutions/6613074/sliding-window-hashmap-solution-no-tle-i-promise-beats-over-96/?envType=study-plan-v2&envId=top-interview-150
 *
 * Reference:
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words
 */
public class SubstringWithConcatOfAllWords {

    public static void main(String[] args) {
        SubstringWithConcatOfAllWords helper = new SubstringWithConcatOfAllWords();
        {
            String s = "barfoothefoobarman";
            String[] words = {"foo", "bar"};
            System.out.println(helper.findSubstring(s, words));
        }
        {
            String s = "wordgoodgoodgoodbestword";
            String[] words = {"word","good","best","word"};
            System.out.println(helper.findSubstring(s, words));
        }
        {
            String s = "barfoofoobarthefoobarman";
            String[] words = {"bar","foo","the"};
            System.out.println(helper.findSubstring(s, words));
        }
        {
            String s = "wordgoodgoodgoodbestword";
            String[] words = {"word","good","best","good"};
            System.out.println(helper.findSubstring(s, words)); // [8]
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> indices = new ArrayList<>();

        if (words.length == 0 || s.isEmpty()) {
            return indices;
        }

        Map<String, Integer> originalCount = new HashMap<>();
        for (String string : words) {
            originalCount.put(string, originalCount.getOrDefault(string, 0) + 1);
        }

        int wordSize = words[0].length();
        int wordCount = words.length;
        int n = s.length();

        for (int offset = 0; offset < wordSize; offset++) {
            Map<String,Integer> currentCount = new HashMap<>();
            int start = offset;
            int count = 0;

            for (int end = offset; end + wordSize <= n; end += wordSize) {
                String currWord = s.substring(end, end + wordSize);
                if (originalCount.containsKey(currWord)) {
                    currentCount.put(currWord, currentCount.getOrDefault(currWord,0) + 1);
                    count++;

                    while (currentCount.get(currWord) > originalCount.get(currWord)) {
                        String startWord = s.substring(start, start + wordSize);
                        currentCount.put(startWord, currentCount.get(startWord) - 1);
                        start += wordSize;
                        count--;
                    }

                    if (count == wordCount) {
                        indices.add(start);
                    }
                }
                else{
                    count = 0;
                    start = end + wordSize;
                    currentCount.clear();
                }
            }
        }

        return indices;
    }

}
