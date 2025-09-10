package com.vivek.matrix;

/**
 * 240. Search a 2D Matrix II
 *
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 *     Integers in each row are sorted in ascending from left to right.
 *     Integers in each column are sorted in ascending from top to bottom.
 *
 * Example 1:
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 *
 * Example 2:
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 * Solution:
 *
 * O(m+n) Approach:
 *
 *     1. Start at the top-right corner of the matrix (i.e., the element in the first row and last column). We will call this element current.
 *     2. Compare current with the target value target. If current is equal to target, return true since we have found the target in the matrix.
 *     3. If current is greater than target, we can eliminate the entire last column (i.e., all elements in the last column are greater than
 *        current and therefore greater than target). Move one column to the left to consider the next element in that row.
 *        We will call this new element current.
 *     4. If current is less than target, we can eliminate the entire first row (i.e., all elements in the first row are less than
 *        current and therefore less than target). Move one row down to consider the next element in that column.
 *        We will call this new element current.
 *     5. Repeat steps 2-4 until current is equal to target or we reach the end of the matrix.
 *     6. If we reach the end of the matrix without finding the target, return false.
 *
 * The idea behind this algorithm is to take advantage of the sorted property of the matrix to eliminate entire rows or columns at once,
 * and narrow down the search space for the target value. Since we start at the top-right corner,
 * we can eliminate rows or columns as we move left or down, respectively.
 *
 * The time complexity of this algorithm is O(m + n) since we move at most m + n steps in the matrix.
 * This is much more efficient than a naive approach of iterating over every element in the matrix, which would take O(m x n) time.
 *
 * Reference: https://leetcode.com/problems/search-a-2d-matrix-ii
 */
public class SearchMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0;
        int j = n - 1;

        while (i < m && j >= 0) {
            int current = matrix[i][j];
            if (current == target) {
                return true;
            } else if (current > target) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }

}
