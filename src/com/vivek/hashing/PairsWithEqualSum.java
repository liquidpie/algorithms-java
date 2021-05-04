package com.vivek.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Find four elements a, b, c and d in an array such that a+b = c+d
 *
 * Given an array of distinct integers, find if there are two pairs (a, b) and (c, d) such that a+b = c+d, and a, b, c and d are distinct elements.
 * If there are multiple answers, then print any of them.
 *
 * Example:
 *
 * Input:   {3, 4, 7, 1, 2, 9, 8}
 * Output:  (3, 8) and (4, 7)
 * Explanation: 3+8 = 4+7
 *
 * Input:   {3, 4, 7, 1, 12, 9};
 * Output:  (4, 12) and (7, 9)
 * Explanation: 4+12 = 7+9
 *
 * Input:  {65, 30, 7, 90, 1, 9, 8};
 * Output:  No pairs found
 *
 * Expected Time Complexity: O(n2)
 *
 * An Efficient Solution can solve this problem in O(n2) time. The idea is to use hashing. We use sum as key and pair as value in hash table.
 *
 * Reference: https://www.geeksforgeeks.org/find-four-elements-a-b-c-and-d-in-an-array-such-that-ab-cd/
 *
 */
public class PairsWithEqualSum {

    static void findPairs(int[] arr) {

        Map<Integer, Pair> hashtable = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                if (hashtable.containsKey(sum)) {
                    var otherPair = hashtable.get(sum);
                    System.out.printf("(%d, %d), (%d, %d)", arr[i], arr[j], otherPair.first, otherPair.second);
                    return;
                } else {
                    hashtable.put(sum, new Pair(arr[i], arr[j]));
                }
            }
        }
    }

    private static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 7, 1, 2, 9, 8};
        findPairs(arr);
    }

}
