package com.vivek.numbers;

import java.util.Arrays;

/**
 * Given an integer array and a positive integer k, count all distinct pairs with difference equal to k.
 *
 * Examples:
 *
 * Input: arr[] = {1, 5, 3, 4, 2}, k = 3
 * Output: 2
 * There are 2 pairs with difference 3, the pairs are {1, 4} and {5, 2}
 *
 * Input: arr[] = {8, 12, 16, 4, 0, 20}, k = 4
 * Output: 5
 * There are 5 pairs with difference 4, the pairs are {0, 4}, {4, 8},
 * {8, 12}, {12, 16} and {16, 20}
 *
 */
public class DistinctPairSameDifference {

    /**
     * 1) Initialize count as 0
     * 2) Sort all numbers in increasing order.
     * 3) Remove duplicates from array.
     * 4) Do following for each element arr[i]
     *    a) Binary Search for arr[i] + k in subarray from i+1 to n-1.
     *    b) If arr[i] + k found, increment count.
     * 5) Return count.
     */
    static int bySorting(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        Arrays.sort(arr);

        for (int i = 0; i < n - 1; i++) {
            if (Arrays.binarySearch(arr, i + 1, n - 1, arr[i] + k) != -1)
                count++;
        }

        return count;
    }

    /**
     * 1) Initialize count as 0.
     * 2) Insert all elements of arr[] in an AVL tree. While inserting,
     *    ignore an element if already present in AVL tree.
     * 3) Do following for each element arr[i].
     *    a) Search for arr[i] + k in AVL tree, if found then increment count.
     *    b) Search for arr[i] - k in AVL tree, if found then increment count.
     *    c) Remove arr[i] from AVL tree.
     */
    static int bySelfBalancingBST(int[] arr, int k) {
        // ToDo
        return bySorting(arr, k);
    }

    /**
     *     Sort the array arr
     *     Take two pointers, l and r, both pointing to 1st element
     *     Take the difference arr[r] – arr[l]
     *         If value diff is K, increment count and move both pointers to next element
     *         if value diff > k, move l to next element
     *         if value diff < k, move r to next element
     *     return count
     */
    static int bySortingPointer(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        Arrays.sort(arr);

        int l = 0;
        int r = 0;

        while (r < n) {
            if (arr[r] - arr[l] == k) {
                count++;
                l++;
                r++;
            } else if (arr[r] - arr[l] < k) {
                r++;
            } else {
                l++;
            }
        }

        return count;
    }

}
