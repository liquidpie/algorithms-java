package com.vivek.heap;

import java.util.PriorityQueue;

/**
 * Kth Smallest Element in a Sorted Matrix
 *
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 *
 * Example 2:
 *
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 *          * Approach: We will iterate through the elements in the matrix and add it
 *          * to a max heap. We keep on adding the first k elements to the queue.
 *          * After that, if we encounter an element smaller than the root of the heap
 *          * then we pop the root of the heap and add the new element to the heap.
 *          *
 *          * Time Complexity: O(n^2 log(k)) where n^2 is the number of elements in the matrix
 *          * Space Complexity: O(k)
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestSortedMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                { 1,5,9 },
                { 10,11,13 },
                { 12,13,15 }
        };
        System.out.println(kthSmallestUsingBinarySearch(matrix, 3));
    }

    static int kthSmallest(int[][] matrix, int k) {
        // max heap
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (k-- > 0) {
                    priorityQueue.add(matrix[i][j]);
                } else if (!priorityQueue.isEmpty() && priorityQueue.peek() > matrix[i][j]) {
                    priorityQueue.poll();
                    priorityQueue.add(matrix[i][j]);
                }
            }
        }

        return priorityQueue.peek();
    }

    /**
     * Algorithm
     *
     *     Initialize two integers, low and high, which will initially contain the minimum element of the matrix and maximum element of the matrix, respectively.
     *     Iterate, while low is less than high.
     *     Calculate the middle integer (it might or might not be in the matrix) with the help of low and high.
     *     Count the number of elements in the arrays that are less than or equal to the middle.
     *     If the count is less than k, then the Kth smallest element will be in the second half, so we change low to middle+1.
     *     But if the count is greater than or equal to k, then the Kth smallest element will be in the first half, so we change high to mid.
     *     Finally, we return low.
     *
     *     Time Complexity:O(N) //N== n^2
     *     Auxiliary Space Complexity: O(1)
     */
    static int kthSmallestUsingBinarySearch(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int low = matrix[0][0];
        int high = matrix[rows - 1][cols  -1];

        while (low <= high) {
            int mid = (low + high) / 2;

            // count of elements lesser than or equal to mid
            int count = 0;
            int maxNum = low;
            int col = cols - 1;
            for (int row = 0; row < rows; row++) {
                while (col >= 0 && matrix[row][col] > mid)
                    col--;

                if (col >= 0) {
                    count += col + 1;
                    maxNum = Math.max(maxNum, matrix[row][col]);
                    // mid might not be value in matrix, we need to record actual max num
                } else {
                    break;
                }
            }

            if (count == k) {
                return maxNum;
            }
            else if (count < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

}
