package com.vivek.maths;

/**
 * 168. Excel Sheet Column Title
 *
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 *
 * For example:
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 *
 *
 * Example 1:
 *
 * Input: columnNumber = 1
 * Output: "A"
 *
 * Example 2:
 *
 * Input: columnNumber = 28
 * Output: "AB"
 *
 * Example 3:
 *
 * Input: columnNumber = 701
 * Output: "ZY"
 *
 * Reference: https://leetcode.com/problems/excel-sheet-column-title/description/
 */
public class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        ExcelSheetColumnTitle helper = new ExcelSheetColumnTitle();
        System.out.println(helper.convertToTitle(702));
    }

    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        int num = columnNumber;

        while (num > 26) {
            int rem = num % 26;
            if (rem == 0) { // case when it's perfectly divisible, it happens Z is the last character for this number
                rem = 26;
                num -= rem;
            }
            sb.append(getLiteral(rem));
            num /= 26;
        }

        if (num > 0)
            sb.append(getLiteral(num));

        return sb.reverse().toString();
    }

    private char getLiteral(int num) {
        return (char) ('A' + num - 1);
    }

}
