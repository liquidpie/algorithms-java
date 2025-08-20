package com.vivek.string.examples;

/**
 * 2047. Number of Valid Words in a Sentence
 *
 * A sentence consists of lowercase letters ('a' to 'z'), digits ('0' to '9'), hyphens ('-'), punctuation marks ('!', '.', and ','), and spaces (' ') only. Each sentence can be broken down into one or more tokens separated by one or more spaces ' '.
 *
 * A token is a valid word if all three of the following are true:
 *
 *     It only contains lowercase letters, hyphens, and/or punctuation (no digits).
 *     There is at most one hyphen '-'. If present, it must be surrounded by lowercase characters ("a-b" is valid, but "-ab" and "ab-" are not valid).
 *     There is at most one punctuation mark. If present, it must be at the end of the token ("ab,", "cd!", and "." are valid, but "a!b" and "c.," are not valid).
 *
 * Examples of valid words include "a-b.", "afad", "ba-c", "a!", and "!".
 *
 * Given a string sentence, return the number of valid words in sentence.
 *
 *
 * Example 1:
 *
 * Input: sentence = "cat and  dog"
 * Output: 3
 * Explanation: The valid words in the sentence are "cat", "and", and "dog".
 *
 * Example 2:
 *
 * Input: sentence = "!this  1-s b8d!"
 * Output: 0
 * Explanation: There are no valid words in the sentence.
 * "!this" is invalid because it starts with a punctuation mark.
 * "1-s" and "b8d" are invalid because they contain digits.
 *
 * Example 3:
 *
 * Input: sentence = "alice and  bob are playing stone-game10"
 * Output: 5
 * Explanation: The valid words in the sentence are "alice", "and", "bob", "are", and "playing".
 * "stone-game10" is invalid because it contains digits.
 *
 * Reference: https://leetcode.com/problems/number-of-valid-words-in-a-sentence/description/
 */
public class ValidWordsInSentence {

    public static void main(String[] args) {
        ValidWordsInSentence helper = new ValidWordsInSentence();
//        {
//            String sentence = "cat and  dog";
//            System.out.println(helper.countValidWords(sentence));
//        }
//        {
//            String sentence = "!this  1-s b8d!";
//            System.out.println(helper.countValidWords(sentence));
//        }
//        {
//            String sentence = "alice and  bob are playing stone-game10";
//            System.out.println(helper.countValidWords(sentence));
//        }
        {
            String sentence = " 62   nvtk0wr4f  8 qt3r! w1ph 1l ,e0d 0n 2v 7c.  n06huu2n9 s9   ui4 nsr!d7olr  q-, vqdo!btpmtmui.bb83lf g .!v9-lg 2fyoykex uy5a 8v whvu8 .y sc5 -0n4 zo pfgju 5u 4 3x,3!wl  fv4   s  aig cf j1 a i  8m5o1  !u n!.1tz87d3 .9    n a3  .xb1p9f  b1i a j8s2 cugf l494cx1! hisceovf3 8d93 sg 4r.f1z9w   4- cb r97jo hln3s h2 o .  8dx08as7l!mcmc isa49afk i1 fk,s e !1 ln rt2vhu 4ks4zq c w  o- 6  5!.n8ten0 6mk 2k2y3e335,yj  h p3 5 -0  5g1c  tr49, ,qp9 -v p  7p4v110926wwr h x wklq u zo 16. !8  u63n0c l3 yckifu 1cgz t.i   lh w xa l,jt   hpi ng-gvtk8 9 j u9qfcd!2  kyu42v dmv.cst6i5fo rxhw4wvp2 1 okc8!  z aribcam0  cp-zp,!e x  agj-gb3 !om3934 k vnuo056h g7 t-6j! 8w8fncebuj-lq    inzqhw v39,  f e 9. 50 , ru3r  mbuab  6  wz dw79.av2xp . gbmy gc s6pi pra4fo9fwq k   j-ppy -3vpf   o k4hy3 -!..5s ,2 k5 j p38dtd   !i   b!fgj,nx qgif ";
            System.out.println(helper.countValidWords(sentence));
        }
    }

    public int countValidWords(String sentence) {
        String[] words = sentence.split(" ");
        int validCount = 0;
        for (String word : words) {
            if (!word.isEmpty() && isValid(word)) {
                System.out.println(word);
                validCount++;
            }
        }
        return validCount;
    }

    private boolean isValid(String word) {
        char[] arr = word.toCharArray();

        int hyphens = 0;
        int punctuations = 0;
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch - '0' >= 0 && ch - '0' <= 9)
                return false;
            if (ch == '-') {
                hyphens++;
                if (i == 0 || i == arr.length - 1 || hyphens > 1) {
                    return false;
                }
                if ((arr[i - 1] - 'a' < 0 || arr[i - 1] - 'a' > 25) || (arr[i + 1] - 'a' < 0 || arr[i + 1] - 'a' > 25))
                    return false;
            }
            if (ch == '!' || ch == '.' || ch == ',') {
                punctuations++;
                if (i != arr.length - 1 || punctuations > 1)
                    return false;
            }
        }
        return true;
    }

}
