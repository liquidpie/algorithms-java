package com.vivek.sort;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=pEJiGC-ObQE
 * https://www.geeksforgeeks.org/counting-sort/
 *
 * Time Complexity: O(n+k) where n is the number of elements in input array and k is the range of input.
 * Auxiliary Space: O(n+k)
 */
public class CountingSort {

    static int[] countingSort(int[] arr) {
        int n = arr.length;
        int k = max(arr);

        int[] counts = new int[k]; // Range from 1-k

        // count the occurrences
        for (int i = 0; i < n; i++) {
            ++counts[arr[i] - 1];
        }

        // cumulative sum of counts
        for (int i = 1; i < k; i++) {
            counts[i] += counts[i - 1];
        }

        // put elements into new array from right to left to maintain Stable Sort
        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        int[] sorted = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            sorted[--counts[arr[i] - 1]] = arr[i];
        }

        return sorted;
    }

    static int[] countingSortWithNegativeValues(int[] arr) {
        int n = arr.length;
        int max = max(arr);
        int min = min(arr);

        int[] counts = new int[max - min + 1]; // Range from min-max including zero

        // count the occurrences
        for (int i = 0; i < n; i++) {
            ++counts[arr[i] - min];
        }

        // cumulative sum of counts
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // put elements into new array from right to left to maintain Stable Sort
        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        int[] sorted = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            sorted[--counts[arr[i] - min]] = arr[i];
        }

        return sorted;
    }

    private static int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static int min(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 8, 7, 12, 10, 9, 1, 2, 1};
        System.out.println(Arrays.toString(arr));

        arr = countingSort(arr);

        System.out.println(Arrays.toString(arr));

        int[] arr2 = { -5, -10, 0, -3, 8, 5, -1, 10 };
        System.out.println(Arrays.toString(arr2));

        arr2 = countingSortWithNegativeValues(arr2);

        System.out.println(Arrays.toString(arr2));

    }

}
