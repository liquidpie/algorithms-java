package com.vivek.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * We are given an array consisting of n integers and an integer k. We need to find the minimum range in array [l, r] (both l and r are inclusive) such that there are exactly k different numbers.
 * Examples:
 *
 * Input : arr[] = { 1, 1, 2, 2, 3, 3, 4, 5}
 *             k = 3
 * Output : 5 7
 *
 * Input : arr[] = { 1, 2, 2, 3 }
 *             k = 2
 * Output : 0 1
 *
 * Simple Solution:
 * A simple solution is to use two nested loops. The outer loop is used to pick a starting point and
 * the inner loop is used to pick an ending point. For every pair of starting-ending points,
 * we count distinct elements in it and update the result if the current window is smaller.
 * We use hashing to count distinct elements in a range.
 * Time Complexity : O(n2)
 *
 * Optimization over the above simple solution. The idea is to remove repetitions on the left side after we find k distinct elements.
 * Time complexity of this solution is O(n). In every nested iteration, we either add an element or remove an element.
 * Every element is inserted and removed at most once.
 *
 * Reference: https://www.geeksforgeeks.org/smallest-subarray-k-distinct-numbers
 */
public class SmallestSubArrayKdistinctElements {

    static void simpleSolution(int[] arr, int k) {
        int n = arr.length;

        int l = 0;
        int r = n;

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            int j;
            for (j = i; j < n; j++) {
                set.add(arr[j]);
                if (set.size() == k) {
                    if ((j - i) < (r - l)) {
                        l = i;
                        r = j;
                    }
                    break;
                }
            }
            // If there was no window with k distinct elements (k is greater than total distinct elements)
            if (j == n)
                break;
        }

        if (l == 0 && r == n) {
            System.out.println("No distinct elements");
        } else {
            System.out.println("[" + l + "," + r + "]");
        }
    }

    static void optimizedSolution(int[] arr, int k) {
        int n = arr.length;
        int l = 0, r = n;

        int j = -1;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            while (j < n) {
                j++;

                if (j < n && map.size() < k) {
                    map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
                }

                if (map.size() == k && (j - i) <= (r - l)) {
                    l = i;
                    r = j;
                    break;
                }
            }

            // If number of distinct elements less
            // than k, then break.
            if (map.size() < k)
                break;

            // If distinct elements equals to k then
            // try to increment left side.
            while (map.size() == k) {
                if (map.getOrDefault(arr[i], 0) == 1) {
                    map.remove(arr[i]);
                } else {
                    map.put(arr[i], map.getOrDefault(arr[i], 0) - 1);
                }
                i++;
                if (map.size() == k && (j - i) <= (r - l)) {
                    l = i;
                    r = j;
                }
            }

            if (map.getOrDefault(arr[i], 0) == 1) {
                map.remove(arr[i]);
            } else {
                map.put(arr[i], map.getOrDefault(arr[i], 0) - 1);
            }

        }

        if (l == 0 && r == n) {
            System.out.println("Invalid K");
        } else {
            System.out.println("[" + l + "," + r + "]");
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 2, 5, 4, 2, 1, 4, 3};
        simpleSolution(arr, 5);
        optimizedSolution(arr, 5);
    }

}
