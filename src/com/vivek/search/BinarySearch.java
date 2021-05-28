package com.vivek.search;

public class BinarySearch {

    /**
     * Returns index of key if it is present in arr[low..high], else return -1
     *
     * Requires O(1) space
     */
    public static int iterative(int[] arr, int low, int high, int key) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key)
                return mid;
            else if (key < arr[mid])
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    /**
     * Returns index of key if it is present in arr[low..high], else return -1
     *
     * Requires O(Log n) space
     */
    public static int recursive(int[] arr, int low, int high, int key) {
        if (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key)
                return mid;
            else if (key < arr[mid])
                return recursive(arr, low, mid - 1, key);
            else
                return recursive(arr, mid + 1, high, key);
        }
        return -1;
    }

}
