package com.vivek.search;

/**
 * The name of this searching algorithm may be misleading as it works in O(Log n) time. The name comes from the way it searches an element.
 *
 * Exponential search involves two steps:
 *
 *    - Find range where element is present
 *    - Do Binary Search in above found range.
 *
 * How to find the range where element may be present?
 * The idea is to start with subarray size 1, compare its last element with x, then try size 2, then 4 and so on until last element of a subarray is not greater.
 * Once we find an index i (after repeated doubling of i), we know that the element must be present between i/2 and i (Why i/2? because we could not find a greater value in previous iteration)
 *
 * Time Complexity : O(Log n)
 * Auxiliary Space : With recursive Binary Search we need O(Log n) space. With iterative Binary Search, we need only O(1) space.
 * Applications of Exponential Search:
 *
 *     Exponential Binary Search is particularly useful for unbounded searches, where size of array is infinite. Please refer Unbounded Binary Search for an example.
 *     It works better than Binary Search for bounded arrays, and also when the element to be searched is closer to the first element.
 */
public class ExponentialSearch {

    public static int search(int[] arr, int key) {
        if (arr == null || arr.length == 0)
            return -1;

        int n = arr.length;

        // If key is present at first location itself
        if (arr[0] == key)
            return 0;

        // Find range for binary search by repeated doubling
        int i = 1;
        while (i < n && key >= arr[i]) {
            i = i * 2;
        }

        // Call binary search for the found range
        return BinarySearch.iterative(arr, i / 2, Math.min(i, n - 1), key);

    }

    public static void main(String[] args) {
        int[] arr = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        System.out.println(search(arr, 13));
    }

}
