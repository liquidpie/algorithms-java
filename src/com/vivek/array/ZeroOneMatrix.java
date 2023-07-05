package com.vivek.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 01 Matrix
 *
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 *
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Example 2:
 *
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * Solution:
 *
 * Approach 1: Using BFS
 * It’s trivial that we can do BFS to update path to zero of each element.
 * However, if one performs BFS start from each non-zero element, the time complexity will be O((mn)²),
 * where m and n denote to counts of rows and columns correspondingly. It’s too slow and we can’t tolerant it.
 *
 * On the other hand, if we can perform BFS start from all 0s and update its neighbors, all elements will only be reached once.
 * It’s will be better than previous idea.
 *
 * Complexity:
 * As we said, each element will be only update once due to checking on matrix[row][col] > matrix[i][j] + 1,
 * which means only the closest zero can update the element. Thus, time complexity will be only O(mn),
 * where m and n denote to counts of rows and columns in this matrix.
 *
 * For space complexity, the queue will use O(mn) extra space.
 *
 * Approach 2: Dynamic Programming
 * If we take a look more carefully, we will find that after scanning from top-left,
 * the path of top and left elements of all elements are determined by their top and left elements.
 * Hence we have a formula:
 *      matrix[i][j] = min(matrix[i][j], matrix[i-1][j], matrix[i][j-1])
 *
 * And because we have four directions, we should also take care about bottom and left:
 *      matrix[i][j] = min(matrix[i][j], matrix[i+1][j], matrix[i][j+1])
 *
 * Therefore, four directions are all considered after these two scans.
 *
 * Complexity
 * Because of two scans with different directions, it takes O(mn + mn) = O(mn)
 * Because it performs update in place, so its space complexity is only O(1).
 *
 * Solution: https://lenchen.medium.com/leetcode-542-01-matrix-b85e06193ec8
 * https://leetcode.com/problems/01-matrix/
 */
public class ZeroOneMatrix {

    public static void main(String[] args) {
        int[][] mat = {
                {0,0,0},
                {0,1,0},
                {1,1,1}};
        System.out.println(Arrays.deepToString(updateMatrixUsingBFS(mat)).replaceAll("],", "]\n"));
    }

    static int[][] updateMatrixUsingBFS(int[][] mat) {
        int m = mat.length;;
        int n = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // for reusing original matrix, convert 1 to infinity at initialization
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1)
                    mat[i][j] = Integer.MAX_VALUE;
                else
                    queue.add(new int[] {i, j});
            }
        }

        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] indices = queue.poll();
            int r = indices[0];
            int c = indices[1];

            for (int[] direction : directions) {
                int row = r + direction[0];
                int col = c + direction[1];

                if (row >= 0 && row < m && col >= 0 && col < n) {
                    if (mat[row][col] > mat[r][c] + 1) {
                        mat[row][col] = mat[r][c] + 1;
                        queue.add(new int[]{row, col});
                    }
                }
            }
        }

        return mat;
    }

    static int[][] updateMatrixUsingDP(int[][] mat) {
        int m = mat.length;;
        int n = mat[0].length;

        // scan from top-bottom
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int top = i > 0 ? mat[i - 1][j] + 1 : Integer.MAX_VALUE;
                int left = j > 0 ? mat[i][j - 1] + 1 : Integer.MAX_VALUE;
                mat[i][j] = Math.min(top, left);
            }
        }

        // scan from bottom-top
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j] == 1) {
                    int bottom = i < m - 1 ? mat[i + 1][j] + 1 : Integer.MAX_VALUE;
                    int right = j < n - 1 ? mat[i][j + 1] + 1 : Integer.MAX_VALUE;
                    mat[i][j] = Math.min(mat[i][j], Math.min(bottom, right));
                }
            }
        }

        return mat;
    }

}
