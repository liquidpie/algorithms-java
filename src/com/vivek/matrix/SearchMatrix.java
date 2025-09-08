package com.vivek.matrix;

/**
 * 74. Search a 2D Matrix
 *
 * You are given an m x n integer matrix matrix with the following two properties:
 *
 *     Each row is sorted in non-decreasing order.
 *     The first integer of each row is greater than the last integer of the previous row.
 *
 * Given an integer target, return true if target is in matrix or false otherwise.
 *
 * You must write a solution in O(log(m * n)) time complexity.
 *
 * Example 1:
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 *
 * Example 2:
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 * Reference: https://leetcode.com/problems/search-a-2d-matrix
 */
public class SearchMatrix {

    public static void main(String[] args) {
        SearchMatrix helper = new SearchMatrix();
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,50}
        };

        System.out.println(helper.searchMatrix(matrix, 11));

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // first search in first column, use binary search
        int l = 0;
        int r = m - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (matrix[mid][0] == target)
                return true;
            else if (matrix[mid][0] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }

        int row = r;
        if (row < 0 || row > m - 1)
            return false;

        // second search in the given row
        l = 0;
        r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (matrix[row][mid] == target)
                return true;
            else if (matrix[row][mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }

        return false;
    }

}
