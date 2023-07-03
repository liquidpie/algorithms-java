package com.vivek.array;

import com.vivek.search.BinarySearch;

import java.util.*;

public class CountPairs {

    /**
     *
     * Given an array of integers, and a number ‘sum’, find the number of pairs of integers in the array whose sum is equal to ‘sum’.
     */
    static int countPairsWithGivenSum(int[] arr, int sum) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int twiceCount = 0;

        // every pair is counted twice
        for (int num : arr) {
            if (freq.containsKey(sum - num)) {
                twiceCount += freq.get(sum - num);
                // if (arr[i], arr[i]) pair satisfies the condition, then we need to ensure that the
                // count is decreased by one such that the (arr[i], arr[i]) pair is not considered
                if (sum - num == num)
                    twiceCount--;
            }
        }
        // every pair is counted twice, so return half of it
        return twiceCount / 2;
    }

    /**
     * Count all distinct pairs with difference equal to k
     *
     * Given an integer array and a positive integer k, count all distinct pairs with differences equal to k.
     *
     * Examples:
     *
     * Input: arr[] = {1, 5, 3, 4, 2}, k = 3
     * Output: 2
     * There are 2 pairs with difference 3, the pairs are {1, 4} and {5, 2}
     */
    static int countDistinctPairsWithGivenDifference(int[] arr, int k) {

        /**
         * 1) Initialize count as 0
         * 2) Sort all numbers in increasing order.
         * 3) Remove duplicates from array.
         * 4) Do following for each element arr[i]
         *    a) Binary Search for arr[i] + k in subarray from i+1 to n-1.
         *    b) If arr[i] + k found, increment count.
         * 5) Return count.
         */
        class UseSorting {
             int countPairs(int[] arr, int k) {
                 Arrays.sort(arr);
                 // remove duplicates
                 int size = RemoveDuplicates.removeDuplicates(arr);
                 int count = 0;
                 // binary search
                 for (int i = 0; i < size - 1; i++) {
                     if (BinarySearch.iterative(arr, i + 1, size, arr[i] + k) != -1)
                         count++;
                 }
                 return count;
             }
        }

        class UseHashing {
            int countPairs(int[] arr, int k) {
                Set<Integer> table = new HashSet<>();
                int count = 0;
                for (int num : arr) {
                    if (table.add(num)) { // consider the element only when it's not already added
                        if (table.contains(num + k) ||
                                table.contains(num - k))
                            count++;
                    }
                }
                return count;
            }
        }

        UseHashing sol = new UseHashing();
        return sol.countPairs(arr, k);

    }

    /**
     * Find a pair with the given difference
     *
     * Given an unsorted array and a number n, find if there exists a pair of elements in the array whose difference is n.
     *
     * Examples:
     *
     * Input: arr[] = {5, 20, 3, 2, 50, 80}, n = 78
     * Output: Pair Found: (2, 80)
     *
     * Input: arr[] = {90, 70, 20, 80, 50}, n = 45
     * Output: No Such Pair
     */
    static void findPairWithGivenDifference(int[] arr, int k) {
        Arrays.sort(arr);

        int i = 0;
        int j = 1;
        int n = arr.length;
        while (i < n && j < n) {
            if (i != j && arr[j] - arr[i] == k) {
                System.out.println("Pair: (" + arr[i] + ", " + arr[j] + ")");
                return;
            } else if (arr[j] - arr[i] > k) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println("No pair found");
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 7, -1, 3, 3};

        System.out.println(countPairsWithGivenSum(arr, 6));

        int[] arr1 = {1, 5, 3, 4, 2, 3, 0, 3, 0};
        System.out.println(countDistinctPairsWithGivenDifference(arr1, 3));
    }

}
