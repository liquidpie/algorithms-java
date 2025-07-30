package com.vivek.string.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 6. Zigzag Conversion
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * Example 3:
 *
 * Input: s = "A", numRows = 1
 * Output: "A"
 *
 * Reference:
 * https://leetcode.com/problems/zigzag-conversion
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        ZigZagConversion helper = new ZigZagConversion();

        System.out.println(helper.convert("PAYPALISHIRING", 3));
        System.out.println(helper.convert("PAYPALISHIRING", 4));
        System.out.println(helper.convert("A", 1));
    }

    public String convert(String s, int numRows) {
        if (numRows <= 1)
            return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int currentRow = 0;
        boolean reachedEdge = false;

        for (int i = 0; i < s.length(); i++) {
            if (currentRow == 0 || currentRow == numRows - 1)
                reachedEdge = !reachedEdge;

            rows.get(currentRow).append(s.charAt(i));

            if (reachedEdge) {
                currentRow++;
            } else {
                currentRow--;
            }
        }

        return rows.stream()
                .map(StringBuilder::toString)
                .collect(Collectors.joining(""));
    }

}
