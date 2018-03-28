package com.vivek.sort;

/**
 * Given an an unsorted array, sort the given array. You are allowed to do only following operation on array.
 *
 * flip(arr, i): Reverse array from 0 to i
 *
 * Unlike a traditional sorting algorithm, which attempts to sort with the fewest comparisons possible,
 * the goal is to sort the sequence in as few reversals as possible.
 *
 * The idea is to do something similar to Selection Sort. We one by one place maximum element at the end
 * and reduce the size of current array by one.
 *
 * Following are the detailed steps. Let given array be arr[] and size of array be n.
 * Start from current size equal to n and reduce current size by one while it’s greater than 1.
 * Let the current size be curr_size. Do following for every curr_size
 *
 * a) Find index of the maximum element in arr[0..curr_szie-1]. Let the index be ‘mi’
 * b) Call flip(arr, mi)
 * c) Call flip(arr, curr_size-1)
 *
 * See following video for visualization of the above algorithm.
 *
 * http://www.youtube.com/embed/kk-_DDgoXfk
 */
public class PancakeSort {

    void pancakeSort(int[] arr, int n) {

        // Start from the complete
        // array and one by one
        // reduce current size by one
        for (int curSize = n; curSize > 1; --curSize) {
            // Find index of the
            // maximum element in
            // arr[0..curr_size-1]
            int mi = findMax(arr, curSize);

            // Move the maximum element
            // to end of current array
            // if it's not already at
            // the end
            if (mi != curSize - 1) {
                // To move at the end,
                // first move maximum
                // number to beginning
                flip(arr, mi);
                // Now move the maximum
                // number to end by
                // reversing current array
                flip(arr, curSize - 1);
            }
        }
    }

    int findMax(int[] arr, int n) {
        int mi, i;
        for (mi = 0, i = 0; i < n; ++i) {
            if (arr[i] > arr[mi])
                mi = i;
        }
        return mi;
    }

    void flip(int[] arr, int i) {
        int temp, start = 0;
        while (start < i) {
            temp = arr[start];
            arr[start] = arr[i];
            arr[i] = temp;
            start++;
            i--;
        }
    }

    void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int arr[] = {23, 10, 20, 11, 12, 6, 7};
        int n = arr.length;

        PancakeSort sort = new PancakeSort();
        sort.pancakeSort(arr, n);
        sort.printArray(arr, n);
    }
}
