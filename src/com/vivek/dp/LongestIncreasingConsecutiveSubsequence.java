package com.vivek.dp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Given N elements, write a program that prints the length of the longest increasing subsequence whose adjacent element difference is one.
 * Examples:
 *
 * Input : a[] = {3, 10, 3, 11, 4, 5, 6, 7, 8, 12}
 * Output : 6
 * Explanation: 3, 4, 5, 6, 7, 8 is the longest increasing subsequence whose adjacent element differs by one.
 * Input : a[] = {6, 7, 8, 3, 4, 5, 9, 10}
 * Output : 5
 * Explanation: 6, 7, 8, 9, 10 is the longest increasing subsequence
 *
 * Dynamic Programming Approach:
 * Let DP[i] store the length of the longest subsequence which ends with A[i].
 * For every A[i], if A[i]-1 is present in the array before i-th index, then A[i] will add to the increasing subsequence which has A[i]-1.
 * Hence, DP[i] = DP[ index(A[i]-1) ] + 1. If A[i]-1 is not present in the array before i-th index,
 * then DP[i]=1 since the A[i] element forms a subsequence which starts with A[i]. Hence, the relation for DP[i] is:
 *
 * If A[i]-1 is present before i-th index:
 *
 *      DP[i] = DP[ index(A[i]-1) ] + 1
 *          else:
 *      DP[i] = 1
 *
 * Reference: https://www.geeksforgeeks.org/longest-increasing-consecutive-subsequence/
 */
public class LongestIncreasingConsecutiveSubsequence {

    static int lics(int[] arr) {
        int n = arr.length;

        // create hashmap to save latest consequent number as "key" and its length as "value"
        Map<Integer, Integer> dp = new HashMap<>();

        dp.put(arr[0], 1);
        for (int i = 1; i < n; i++) {
            if (dp.containsKey(arr[i] - 1)) {
                dp.put(arr[i], dp.get(arr[i] - 1) + 1);
                // remove the last consequent number
                dp.remove(arr[i] - 1);
            } else {
                dp.put(arr[i], 1);
            }
        }

        return Collections.max(dp.values());

    }

    public static void main(String[] args) {
        int[] arr = {3, 10, 3, 11, 4, 5, 6, 7, 8, 12};
        System.out.println(lics(arr));
    }

}
