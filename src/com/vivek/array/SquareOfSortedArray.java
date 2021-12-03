package com.vivek.array;

import java.util.Arrays;

/**
 * There's a sorted array containing positive and negative numbers.
 * Return an array containing the square of each element sorted in ascending manner
 *
 * Example:
 * Input: [-9, -8, -1, 1, 2, 4, 6, 10]
 * Output: [1, 1, 4, 16, 36, 64, 81, 100]
 *
 * Approach:
 * 1. Divide the array into two-part “Negative and positive “.
 * 2. Use merge function to merge two sorted arrays into a single sorted array.
 */
public class SquareOfSortedArray {

    public static void main(String[] args) {
        int[] input = { -9, -8, -1, 1, 2, 4, 6, 10 };
        int[] result = squares(input);
        System.out.println(Arrays.toString(result));
    }

    static int[] squares(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;
        int[] result = new int[n];

        int splitIdx = findSplitIndex(arr);

        int i = splitIdx - 1;
        int j = splitIdx;
        int k = 0;

        while (i >= 0 && j < n) {
            if ((-1 * arr[i]) <= arr[j]) {
                result[k++] = arr[i] * arr[i];
                i--;
            } else {
                result[k++] = arr[j] * arr[j];
                j++;
            }
        }

        while (i >= 0) {
            result[k++] = arr[i] * arr[i];
            i--;
        }

        while (j < n) {
            result[k++] = arr[j] * arr[j];
            j++;
        }

        return result;
    }

    static int findSplitIndex(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if ((mid == 0) || (mid == arr.length - 1) || (arr[mid] > 0 && arr[mid - 1] < 0)) {
                return mid;
            } else if (arr[mid] > 0 && arr[mid - 1] > 0) {
                end = mid - 1;
            } else if (arr[mid] < 0 && arr[mid + 1] < 0) {
                start = mid + 1;
            }
        }
        return mid;
    }

}
