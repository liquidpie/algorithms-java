package com.vivek.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 12. Integer to Roman
 *
 * Seven different symbols represent Roman numerals with the following values:
 * Symbol	Value
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 *
 * Roman numerals are formed by appending the conversions of decimal place values from highest to lowest.
 * Converting a decimal place value into a Roman numeral has the following rules:
 *
 *     - If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input,
 *     append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
 *     - If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol,
 *     for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX.
 *     Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
 *     - Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10.
 *     You cannot append 5 (V), 50 (L), or 500 (D) multiple times.
 *     If you need to append a symbol 4 times use the subtractive form.
 *
 * Given an integer, convert it to a Roman numeral.
 *
 * Example 1:
 *
 * Input: num = 3749
 * Output: "MMMDCCXLIX"
 *
 * Explanation:
 *
 * 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
 *  700 = DCC as 500 (D) + 100 (C) + 100 (C)
 *   40 = XL as 10 (X) less of 50 (L)
 *    9 = IX as 1 (I) less of 10 (X)
 * Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places
 *
 * Example 2:
 *
 * Input: num = 58
 * Output: "LVIII"
 *
 * Explanation:
 *
 * 50 = L
 *  8 = VIII
 *
 * Example 3:
 *
 * Input: num = 1994
 * Output: "MCMXCIV"
 *
 * Explanation:
 *
 * 1000 = M
 *  900 = CM
 *   90 = XC
 *    4 = IV
 *
 * Reference: https://leetcode.com/problems/integer-to-roman
 */
public class IntegerToRoman {

    private static final Map<Integer, Character> symbols = symbols();

    public static void main(String[] args) {
        IntegerToRoman helper = new IntegerToRoman();

        System.out.println(helper.intToRoman(3749)); // MMMDCCXLIX
        System.out.println(helper.intToRoman(58)); // LVIII
        System.out.println(helper.intToRoman(1994)); // MCMXCIV
    }

    public String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        int[] places = {1000, 100, 10, 1};

        for (int place : places) {
            if (num / place != 0) {
                roman.append(posToRoman(num / place, place));
                num %= place;
            }
        }
        return roman.toString();
    }

    private String posToRoman(int digit, int place) {
        StringBuilder numeral = new StringBuilder();
        if (digit < 4) {
            for (int i = 0; i < digit; i++) {
                numeral.append(symbols.get(place));
            }
        } else if (digit == 4) {
            numeral.append(symbols.get(place))
                    .append(symbols.get(5 * place));
        } else if (digit == 9) {
            numeral.append(symbols.get(place))
                    .append(symbols.get(10 * place));
        } else {
            numeral.append(symbols.get(5 * place));
            for (int i = 0; i < digit - 5; i++) {
                numeral.append(symbols.get(place));
            }
        }
        return numeral.toString();
    }

    private static Map<Integer, Character> symbols() {
        Map<Integer, Character> symbols = new HashMap<>();
        symbols.put(1, 'I');
        symbols.put(5, 'V');
        symbols.put(10, 'X');
        symbols.put(50, 'L');
        symbols.put(100, 'C');
        symbols.put(500, 'D');
        symbols.put(1000, 'M');
        return symbols;
    }

}
