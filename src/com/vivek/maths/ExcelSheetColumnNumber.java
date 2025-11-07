package com.vivek.maths;

/**
 * 171. Excel Sheet Column Number
 *
 * Given a string columnTitle that represents the column title as appears in an Excel sheet, return its corresponding column number.
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
 * Input: columnTitle = "A"
 * Output: 1
 *
 * Example 2:
 *
 * Input: columnTitle = "AB"
 * Output: 28
 *
 * Example 3:
 *
 * Input: columnTitle = "ZY"
 * Output: 701
 *
 * Reference: https://leetcode.com/problems/excel-sheet-column-number/description/
 */
public class ExcelSheetColumnNumber {

    public static void main(String[] args) {
        ExcelSheetColumnNumber helper = new ExcelSheetColumnNumber();
        System.out.println(helper.titleToNumber("A"));
        System.out.println(helper.titleToNumber("AB"));
        System.out.println(helper.titleToNumber("ZY"));
    }

    public int titleToNumber(String columnTitle) {
        int colNum = 0;
        for (char c : columnTitle.toCharArray()) {
            int num = c - 'A' + 1;
            colNum = colNum * 26 + num;
        }

        return colNum;
    }

}
