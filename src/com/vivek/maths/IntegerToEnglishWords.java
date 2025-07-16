package com.vivek.maths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 273. Integer to English Words
 *
 * Convert a non-negative integer num to its English words representation.
 *
 * Example 1:
 *
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 *
 * Example 2:
 *
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 *
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Reference:
 * https://leetcode.com/problems/integer-to-english-words/description/
 *
 */
public class IntegerToEnglishWords {

    public static void main(String[] args) {
        IntegerToEnglishWords helper = new IntegerToEnglishWords();
        {
            int num = 100000000;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 3200348;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 200000;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 1234567891;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 100;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 0;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 120;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 123;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 1234;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 12345;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 100000;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 1000;
            System.out.println(helper.numberToWords(num));
        }
        {
            int num = 1234567;
            System.out.println(helper.numberToWords(num));
        }
    }

    public String numberToWords(int num) {
        Map<Integer, String> lookup = lookupTable();

        if (num / 100 == 0 && lookup.containsKey(num))
            return lookup.get(num);

        List<Integer> order = List.of(1_000_000_000, 100_000_000, 1_000_000, 100_000, 1_000, 100);
        StringBuilder builder = new StringBuilder();

        int val = num;
        for (int i = 0; i < order.size(); i++) {
            int pos = order.get(i);
            if (val / pos != 0) {
                int curr = val / pos;

                String word = lookup.get(curr);
                if (word == null) {
                    word = twoDigits(curr, lookup);
                }

                builder.append(word)
                        .append(" ")
                        .append(lookup.get(pos))
                        .append(" ");

                if ((i == 1 || i == 3) && (val / order.get(i + 1)) % (pos / order.get(i + 1)) == 0) { // special cases e.g. 100,000
                    builder.append(lookup.get(pos / 100))
                            .append(" ");
                }

                val %= pos;
            }
        }

        if (val > 0) {
            builder.append(twoDigits(val, lookup));
        }

        return builder.toString().trim();
    }

    private String twoDigits(int curr, Map<Integer, String> lookup) {
        if (lookup.containsKey(curr))
            return lookup.get(curr);
        int last = curr % 10;
        int beg = (curr / 10) * 10;
        String word = lookup.getOrDefault(last, "");
        if (beg > 0) {
            word = lookup.get(beg) + " " + word;
        }
        return word;
    }

    private Map<Integer, String> lookupTable() {
        Map<Integer, String> lookup = new HashMap<>();
        lookup.put(0, "Zero");
        lookup.put(1, "One");
        lookup.put(2, "Two");
        lookup.put(3, "Three");
        lookup.put(4, "Four");
        lookup.put(5, "Five");
        lookup.put(6, "Six");
        lookup.put(7, "Seven");
        lookup.put(8, "Eight");
        lookup.put(9, "Nine");
        lookup.put(10, "Ten");
        lookup.put(11, "Eleven");
        lookup.put(12, "Twelve");
        lookup.put(13, "Thirteen");
        lookup.put(14, "Fourteen");
        lookup.put(15, "Fifteen");
        lookup.put(16, "Sixteen");
        lookup.put(17, "Seventeen");
        lookup.put(18, "Eighteen");
        lookup.put(19, "Nineteen");
        lookup.put(20, "Twenty");
        lookup.put(30, "Thirty");
        lookup.put(40, "Forty");
        lookup.put(50, "Fifty");
        lookup.put(60, "Sixty");
        lookup.put(70, "Seventy");
        lookup.put(80, "Eighty");
        lookup.put(90, "Ninety");
        lookup.put(100, "Hundred");
        lookup.put(1000, "Thousand");
        lookup.put(100000, "Hundred");
        lookup.put(1000000, "Million");
        lookup.put(100000000, "Hundred");
        lookup.put(1000000000, "Billion");
        return lookup;
    }

}
