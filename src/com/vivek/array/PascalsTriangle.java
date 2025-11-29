package com.vivek.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 *
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * Example 1:
 *
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * Example 2:
 *
 * Input: numRows = 1
 * Output: [[1]]
 *
 * Reference: https://leetcode.com/problems/pascals-triangle/description/
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        PascalsTriangle helper = new PascalsTriangle();
        System.out.println(helper.generate(7));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                int num = (j - 1 >= 0 && j < i)
                        ? triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j)
                        : 1;
                row.add(num);
            }
            triangle.add(row);
        }
        return triangle;
    }

}
