package com.vivek.string.examples;

import com.vivek.string.WordBreak;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Text Justification
 *
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line does not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 *
 * Note:
 *
 *     A word is defined as a character sequence consisting of non-space characters only.
 *     Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 *     The input array words contains at least one word.
 *
 * Example 1:
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 *
 * Example 2:
 *
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 *
 * Example 3:
 *
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 * Reference:
 * https://leetcode.com/problems/text-justification
 */
public class TextJustification {

    public static void main(String[] args) {
        TextJustification helper = new TextJustification();

        {
            String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
            System.out.println(helper.fullJustify(words, 16));
        }
        {
            String[] words = {"What","must","be","acknowledgment","shall","be"};
            System.out.println(helper.fullJustify(words, 16));
        }
        {
            String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
            System.out.println(helper.fullJustify(words, 20));
        }
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int idx = 0;

        List<String> line = new ArrayList<>();
        int lineLength = 0;
        while (idx < words.length) {
            String word = words[idx];
            if (word.length() == maxWidth) {
                if (!line.isEmpty()) {
                    result.add(justify(maxWidth, lineLength, line));
                    line.clear();
                    lineLength = 0;
                }
                result.add(word);
                idx++;
            } else {
                if (word.length() + lineLength <= maxWidth) {
                    line.add(word);
                    lineLength += word.length() + 1;
                    idx++;
                } else {
                    result.add(justify(maxWidth, lineLength, line));
                    line.clear();
                    lineLength = 0;
                }
            }
        }

        if (!line.isEmpty()) {
            result.add(leftjustify(maxWidth, line));
        }

        return result;
    }

    private String justify(int width, int lineLength, List<String> words) {
        if (words.size() == 1)
            return leftjustify(width, words);

        StringBuilder builder = new StringBuilder();
        int totalSpaces = width - lineLength + words.size();
        int equalSpaces = totalSpaces / (words.size() - 1);
        int unequalSpaces = totalSpaces - (equalSpaces * (words.size() - 1));

        StringBuilder space = new StringBuilder();
        for (String word : words) {
            builder.append(space)
                    .append(word);
            space = new StringBuilder();
            space.append(" ".repeat(Math.max(0, equalSpaces)));
            if (unequalSpaces > 0) {
                space.append(" ");
                unequalSpaces--;
            }
        }

        return builder.toString();
    }

    private String leftjustify(int width, List<String> words) {
        String left = String.join(" ", words);
        left += " ".repeat(width - left.length());
        return left;
    }

}
