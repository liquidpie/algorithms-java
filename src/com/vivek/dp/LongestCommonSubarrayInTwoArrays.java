package com.vivek.dp;

/**
 * Longest common subarray in the given two arrays
 *
 * Given two arrays A[] and B[] of N and M integers respectively, the task is to find the maximum length of equal subarray or
 * the longest common subarray between the two given array.
 *
 * Input: A[] = {1, 2, 8, 2, 1}, B[] = {8, 2, 1, 4, 7}
 * Output: 3
 * Explanation:
 * The subarray that is common to both arrays are {8, 2, 1} and the length of the subarray is 3.
 * Input: A[] = {1, 2, 3, 2, 1}, B[] = {8, 7, 6, 4, 7}
 * Output: 0
 * Explanation:
 * There is no such subarrays which are equal in the array A[] and B[].
 *
 * Approach:
 *
 * Let the input sequences are A[0..n-1] and B[0..m-1] of lengths m & n respectively. Following is the recursive implementation of the equal subarrays:
 *
 * Since common subarray of A[] and B[] must start at some index i and j such that A[i] is equals to B[j]. Let dp[i][j] be the longest common subarray of A[i…] and B[j…].
 * Therefore, for any index i and j, if A[i] is equals to B[j], then dp[i][j] = dp[i+1][j+1] + 1.
 * The maximum of all the element in the array dp[][] will give the maximum length of equal subarrays.
 * For Example:
 * If the given array A[] = {1, 2, 8, 2, 1} and B[] = {8, 2, 1, 4, 7}. If the characters match at index i and j for the array A[] and B[] respectively, then dp[i][j] will be updated as 1 + dp[i+1][j+1].
 * Below is the updated dp[][] table for the given array A[] and B[].
 *
 * Time Complexity: O(N*M), where N is the length of array A[] and M is the length of array B[].
 * Auxiliary Space: O(N*M)
 *
 * Reference: https://www.geeksforgeeks.org/longest-common-subarray-in-the-given-two-arrays
 */
public class LongestCommonSubarrayInTwoArrays {

    static int findMaxLength(int[] A, int[] B, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                if (A[i] == B[j])
                    dp[i][j] = dp[i + 1][j + 1] + 1;
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] A = { 1, 2, 8, 2, 1 };
        int[] B = { 8, 2, 1, 4, 7 };

        System.out.println(findMaxLength(A, B, A.length, B.length));
    }

}
