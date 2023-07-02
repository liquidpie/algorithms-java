package com.vivek.array;

/**
 * Given an array arr[] of size N, find the prefix sum of the array. A prefix sum array is another array prefixSum[] of the same size, such that the value of prefixSum[i] is arr[0] + arr[1] + arr[2] . . . arr[i].
 *
 * Examples:
 *
 * Input: arr[] = {10, 20, 10, 5, 15}
 * Output: prefixSum[] = {10, 30, 40, 45, 60}
 * Explanation: While traversing the array, update the element by adding it with its previous element.
 * prefixSum[0] = 10,
 * prefixSum[1] = prefixSum[0] + arr[1] = 30,
 * prefixSum[2] = prefixSum[1] + arr[2] = 40 and so on.
 *
 * https://www.geeksforgeeks.org/prefix-sum-array-implementation-applications-competitive-programming/
 */
public class PrefixSum {

    // Fills prefix sum array
    static void fillPrefixSum(int[] arr, int n, int[] prefixSum) {
        prefixSum[0] = arr[0];
        // Adding present element with previous element
        for (int i = 1; i < n; ++i)
            prefixSum[i] = prefixSum[i - 1] + arr[i];
    }

    // Driver code
    public static void main(String[] args) {
        int[] arr = { 10, 4, 16, 20 };
        int n = arr.length;
        int[] prefixSum = new int[n];

        // Function call
        fillPrefixSum(arr, n, prefixSum);
        for (int i = 0; i < n; i++)
            System.out.print(prefixSum[i] + " ");
        System.out.println();
    }
}
