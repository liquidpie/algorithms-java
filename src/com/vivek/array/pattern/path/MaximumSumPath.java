package com.vivek.array.pattern.path;

/**
 * Maximum Sum Path in Two Arrays
 *
 * Given two sorted arrays, such that the arrays may have some common elements.
 * Find the sum of the maximum sum path to reach from the beginning of any array to end of any of the two arrays.
 * We can switch from one array to another array only at common elements.
 *
 * Note: The common elements do not have to be at the same indexes.
 *
 * Expected Time Complexity: O(m+n), where m is the number of elements in ar1[] and n is the number of elements in ar2[].
 *
 * Input: ar1[] = {2, 3, 7, 10, 12}
 *        ar2[] = {1, 5, 7, 8}
 * Output: 35
 *
 * Explanation: 35 is sum of 1 + 5 + 7 + 10 + 12.
 *
 * Approach:
 * Efficient Approach: The idea is to do something similar to merge process of merge sort.
 * This involves calculating the sum of elements between all common points of both arrays.
 * Whenever there is a common point, compare the two sums and add the maximum of two to the result.
 *
 *
 *
 * Reference: https://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/
 */
public class MaximumSumPath {

    static int maxSumPath(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;

        int result = 0;
        int sum1 = 0;
        int sum2 = 0;

        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (arr1[i] == arr2[j]) {
                result += Math.max(sum1, sum2) + arr1[i];
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                sum1 += arr1[i];
                i++;
            } else {
                sum2 += arr2[j];
                j++;
            }
        }

        while (i < n) {
            sum1 += arr1[i];
            i++;
        }

        while (j < m) {
            sum2 += arr2[j];
            j++;
        }

        result += Math.max(sum1, sum2);

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 7, 10, 12};
        int[] arr2 = {1, 5, 7, 8};

        System.out.println(maxSumPath(arr1, arr2));
    }

}
