package com.vivek.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 *
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * Example 1:
 *
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 *
 * Example 2:
 *
 * Input: rowIndex = 0
 * Output: [1]
 *
 * Example 3:
 *
 * Input: rowIndex = 1
 * Output: [1,1]
 *
 * Reference: https://leetcode.com/problems/pascals-triangle-ii/description/
 */
public class PascalsTriangle2 {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        long ans = 1;
        row.add((int) ans);

        for (int i = 1; i <= rowIndex; i++) {
            ans = ans * (rowIndex + 1 - i) / i;
            row.add((int) ans);
        }
        return row;
    }

}
