package com.vivek.array;

/**
 * Find the index of first 1 in a sorted array of 0’s and 1’s
 *
 * Use the technique of binary search on the sorted array, so as to find the index of first ‘1’.
 */
public class IndexOfFirstOne {

    static int indexOfFirstOne(int[] arr, int low, int high) {

        int mid = (low + high) / 2;

        while (low < high) {

            if (arr[mid] == 1 && (mid == 0 || arr[mid - 1] == 0)) {
                return mid;
            } else if (arr[mid] == 1) { // first 1 lies on left
                high = mid - 1;
            } else { // first 1 lies on right
                low = mid + 1;
            }
        }

        return -1;
    }

}
