package com.vivek.array;

/**
 * Given a bitonic sequence of n distinct elements, write a program to find a given element x in the bitonic sequence in O(log n) time.
 * A Bitonic Sequence is a sequence of numbers that is first strictly increasing then after a point strictly decreasing.
 *
 * Examples:
 *
 * Input :  arr[] = {-3, 9, 18, 20, 17, 5, 1};
 *          key = 20
 * Output : Found at index 3
 *
 * Input :  arr[] = {5, 6, 7, 8, 9, 10, 3, 2, 1};
 *          key = 30
 * Output : Not Found
 *
 * An efficient solution is based on Binary Search. The idea is to find the bitonic point k which is the index of the
 * maximum element of a given sequence (can be done in log(n) time by modifying the binary search algorithm).
 * If the element to be searched is greater than the maximum element return -1, else search the element in both halves.
 */
public class BitonicArray {

    private static int ascendingBinarySearch(int[] arr, int low, int high, int key) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int descendingBinarySearch(int[] arr, int low, int high, int key) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int findBitonicPoint(int[] arr, int n, int l, int r) {
        int mid;
        int bitonicPoint = 0;
        mid = (r + l) / 2;
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            return mid;
        } else {
            if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                bitonicPoint =  findBitonicPoint(arr, n, mid, r);
            } else if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                    bitonicPoint = findBitonicPoint(arr, n, l, mid);
            }
        }
        return bitonicPoint;
    }

    static int searchBitonic(int[] arr, int n, int key, int index) {
        if (key > arr[index]) {
            return -1;
        } else if (key == arr[index]) {
            return index;
        } else {
            int temp = ascendingBinarySearch(arr, 0, index - 1, key);
            if (temp != -1) {
                return temp;
            }

            return descendingBinarySearch(arr, index + 1, n - 1, key);
        }
    }

    public static void main(String[] args) {
        int[] arr = { -8, 1, 2, 3, 4, 5, -2, -3 };
        int key = 5;
        int n, l, r;
        n = arr.length;
        l = 0;
        r = n - 1;
        int index;
        index = findBitonicPoint(arr, n, l, r);

        int x = searchBitonic(arr, n, key, index);

        if (x == -1) {
            System.out.println("Element Not Found");
        }
        else {
            System.out.println("Element Found at index "
                    + x);
        }
    }

}
