package com.vivek.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Construction of Longest Increasing Subsequence(LIS) and printing LIS sequence
 *
 * For example,
 * the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
 *
 * Other examples:
 * {3, 10, 2, 1, 20}: 3, 10, 20
 *
 * {50, 3, 10, 7, 40, 80}: 3, 10, 40, 80
 *
 * Reference: https://www.geeksforgeeks.org/construction-of-longest-increasing-subsequence-using-dynamic-programming
 */
public class LongestIncreasingSubsequenceConstruction {

    /**
     * Brute force solution
     */
    static void printLIS(int[] arr) {
        int n = arr.length;
        List<Integer> result = null;
        for (int i = 0; i < n; i++) {
            List<Integer> current = new ArrayList<>();
            current.add(arr[i]);
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > current.get(current.size() - 1)) {
                    current.add(arr[j]);
                }
            }
            if (result == null) {
                result = current;
            }
            if (current.size() > result.size()) {
                result = current;
            }
        }

        System.out.println(result);
    }

    static void printLisDP(int[] arr) {
        int n = arr.length;
        ArrayList<Integer>[] lis = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            lis[i] = new ArrayList<>();
        }

        // base case
        lis[0].add(arr[0]);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i].size() < lis[j].size() + 1) {
                    lis[i] = lis[j];
                }
            }
            // lis[i] ends with arr[i]
            lis[i].add(arr[i]);
        }

        ArrayList<Integer> result = lis[0];
        for (int i = 1; i < n; i++) {
            result = lis[i].size() > result.size() ? lis[i] : result;
        }

        System.out.println(result);

    }

    public static void main(String[] args) {
        int[] arr = {3, 10, 2, 1, 20};
        printLisDP(arr);
    }

}
