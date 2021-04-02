package com.vivek.sort;

import java.util.Arrays;

public class QuickSortInPlace {

    static void quickSort(int[] arr, int start, int end) {
        if (start >= end)  // subarray is trivially sorted
            return;

        int left = start;
        int right = end - 1;
        int pivot = arr[end];

        while (left <= right) {

            while (left <= right && arr[left] < pivot)
                left++;

            while (left <= right && arr[right] > pivot)
                right--;

            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }

        }
        // put pivot into it's final place (currently marked by left index)
        swap(arr, left, end);

        // make recursive calls
        quickSort(arr, start, left - 1);
        quickSort(arr, left + 1, end);

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 8, 7, 12, 10, 9, 1, 2};
        System.out.println(Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));

    }

}
