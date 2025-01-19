package com.vivek.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 843. Guess the Word
 *
 * You are given an array of unique strings words where words[i] is six letters long. One word of words was chosen as a secret word.
 *
 * You are also given the helper object Master. You may call Master.guess(word) where word is a six-letter-long string,
 * and it must be from words. Master.guess(word) returns:
 *     -1 if word is not from words, or
 *     an integer representing the number of exact matches (value and position) of your guess to the secret word.
 *
 * There is a parameter allowedGuesses for each test case where allowedGuesses is the maximum number of times you can call Master.guess(word).
 *
 * For each test case, you should call Master.guess with the secret word without exceeding the maximum number of allowed guesses. You will get:
 *
 *     - "Either you took too many guesses, or you did not find the secret word." if you called Master.guess more than allowedGuesses times or
 *        if you did not call Master.guess with the secret word, or
 *     - "You guessed the secret word correctly." if you called Master.guess with the secret word with the number of calls to Master.guess
 *        less than or equal to allowedGuesses.
 *
 * The test cases are generated such that you can guess the secret word with a reasonable strategy (other than using the bruteforce method).
 *
 * Example 1:
 *
 * Input: secret = "acckzz", words = ["acckzz","ccbazz","eiowzz","abcczz"], allowedGuesses = 10
 * Output: You guessed the secret word correctly.
 *
 * Explanation:
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 * We made 5 calls to master.guess, and one of them was the secret, so we pass the test case.
 *
 * Example 2:
 *
 * Input: secret = "hamada", words = ["hamada","khaled"], allowedGuesses = 10
 * Output: You guessed the secret word correctly.
 * Explanation: Since there are two words, you can guess both.
 *
 * Constraints:
 *     1 <= words.length <= 100
 *     words[i].length == 6
 *     words[i] consist of lowercase English letters.
 *     All the strings of wordlist are unique.
 *     secret exists in words.
 *     10 <= allowedGuesses <= 30
 *
 * Approach:
 * ----------------------------------------------------------------
 * https://leetcode.com/problems/guess-the-word/solutions/133862/Random-Guess-and-Minimax-Guess-with-Comparison/
 *
 * There is no solution that can guarantee to find a secret word in 10 tries.
 * If I make up a test case with wordlist like ["aaaaaa", "bbbbbb" ...., "zzzzzz"],
 * it needs 26 tries to find the secret.
 *
 * So 10 tries is just a constraint to check a reasonable solution.
 * And instead of just finding right output from given input, it's more about a strategy.
 *
 * Intuition -->
 * Take a word from wordlist and guess it.
 * Get the matches of this word
 * Update our wordlist and keep only the same matches to our guess.
 *
 * This process is straight forward.
 * However, the key point is, which word should we guess from all of the wordlist?
 *
 * Solution 1: Always Guess the First One -->
 *
 * First of all, we just guessed the first word in the wordlist.
 * Unfortunately, it didn't get a lucky pass.
 * This problem has only 5 test cases but they are good.
 *
 * Time complexity O(N)
 * Space complexity O(N)
 *
 * Solution 2.1: Shuffle the Wordlist -->
 *
 * I didn't give up the previous idea, it's not that bad.
 * So I decided to try my luck by shuffling wordlist at the beginning.
 *
 * Note that it may sound some unreliable,
 * but actully randomicity is very useful trick in both competition and reality problem.
 *
 * Time complexity O(N)
 * Space complexity O(N)
 *
 * Solution 2.2: Guess a Random Word -->
 *
 * All words are generated randomly.
 * So why not we also guess a random word and let it be whatever will be.
 * This is actually the same as the previous solution.
 * Though we don't need one more O(N) operation to shuffle the wordlist at first.
 *
 * Time complexity O(N)
 * Space complexity O(N)
 *
 * Solution 3: Minimax -->
 *
 * Now we want to try a better solution.
 * Generally, we will get 0 matches from the master.guess.
 * As a result, the size of wordlist reduces slowly.
 *
 * Recall some math here, the possiblity that get 0 matched is:
 * (25/26) ^ 6 = 79.03%
 *
 * That is to say, if we make a blind guess,
 * we have about 80% chance to get 0 matched with the secret word.
 *
 * To simplify the model,
 * we're going to assume that,
 * we will always run into the worst case (get 0 matched).
 *
 * In this case,
 * we have 80% chance to eliminate the candidate word
 * as well as its close words which have at least 1 match.
 *
 * Additionally, in order to delete a max part of words,
 * we select a candidate who has a big "family",
 * (that is, the fewest 0 matched with other words.)
 * We want to guess a word that can minimum our worst outcome.
 *
 * So we compare each two words and count their matches.
 * For each word, we note how many word of 0 matches it gets.
 * Then we guess the word with minimum words of 0 matches.
 *
 * In this solution, we apply a minimax idea.
 * We minimize our worst case,
 * The worst case is max(the number of words with x matches),
 * and we assume it equal to "the number of words with 0 matches"
 *
 * Time complexity O(N^2)
 * Space complexity O(N)
 *
 * Solution 4: Count the Occurrence of Characters -->
 *
 * In the previous solution, we compared each two words.
 * This make the complexity O(N^2) for each turn.
 *
 * But actually we don't have to do that.
 * We just need to count the occurrence for each character on each position.
 *
 * If we can guess the word that not in the wordlist,
 * we can guess the word based on the most frequent character on the position.
 *
 * Here we have to guess a word from the list,
 * we still can calculate a score of similarity for each word,
 * and guess the word with highest score.
 *
 * Time complexity O(N)
 * Space complexity O(N)
 *
 * Reference:
 * https://leetcode.com/problems/guess-the-word/description/
 */
public class WordleGuessTheWord {

    public static void main(String[] args) {
        {
            String[] words = {"acckzz", "ccbazz", "eiowzz", "abcczz"};
            Master master = new Master("acckzz");

            findSecretWordUsingMinimax(words, master);
        }
        {
            String[] words = {"abcdef","acdefg","adefgh","aefghi","afghij","aghijk","ahijkl","aijklm","ajklmn","aklmno","almnoz","anopqr","azzzzz"};
            Master master = new Master("azzzzz");

            findSecretWordUsingMinimax(words, master);
        }
    }

    static void findSecretWordUsingMinimax(String[] words, Master master) {
        List<String> wordList = new ArrayList<>(Arrays.asList(words));
        int matchLength = 6;
        for (int guessCount = 0; guessCount < 30; guessCount++) {
            int[][] countPos = new int[matchLength][26];

            for (String word : wordList) {
                for (int i = 0; i < matchLength; i++) {
                    countPos[i][word.charAt(i) - 'a']++;
                }
            }

            int bestMatch = 0;
            String guessedWord = null;
            for (String word : wordList) {
                int score = 0;
                for (int i = 0; i < matchLength; i++) {
                    score += countPos[i][word.charAt(i) - 'a'];
                }
                if (score > bestMatch) {
                    bestMatch = score;
                    guessedWord = word;
                }
            }

            int matches = master.guess(guessedWord);

            if (matches == matchLength) {
                System.out.println("You guessed the secret word correctly.");
                return;
            }

            wordList = filteredList(wordList, guessedWord, matches);
        }
        System.out.println("Unable to guess the secret word");
    }

    static void findSecretWordUsingRandomGuess(String[] words, Master master) {
        int matchLength = 6;
        int guessCount = 0;
        Random random = new Random();
        List<String> wordList = new ArrayList<>(Arrays.asList(words));

        while (guessCount < 10) {
            guessCount++;

            String guessedWord = wordList.get(random.nextInt(wordList.size()));
            int matches = master.guess(guessedWord);

            if (matches == matchLength) {
                System.out.println("You guessed the secret word correctly.");
                return;
            }

            wordList = filteredList(wordList, guessedWord, matches);
        }
        System.out.println("Unable to guess the secret word");
    }

    private static List<String> filteredList(List<String> words, String guessedWord, int matches) {
        return words.stream().filter(word -> match(word, guessedWord) == matches).toList();
    }

    private static int match(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i))
                count++;
        }
        return count;
    }

    static class Master {
        private final String secret;
        private final int allowedGuesses;
        private int guessCount;

        public Master(String secret) {
            this.secret = secret;
            this.allowedGuesses = 10;
        }

        public int guess(String word) {
            guessCount++;
            if (guessCount > allowedGuesses) {
                throw new IllegalArgumentException("Exhausted guesses");
            }
            // determine the number of exact matches (value and position) of the guess word to the secret word
            // Return the number of matches
            int matches = 0;
            for (int i = 0; i < secret.length(); i++) {
                if (word.charAt(i) == secret.charAt(i)) {
                    matches++;
                }
            }
            return matches == 0 ? -1 : matches;
        }
    }

}
