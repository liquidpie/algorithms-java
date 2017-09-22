package com.vivek.sort;

import java.util.Arrays;

/**
 * Created by VJaiswal on 18/03/17.
 */
public class HeapSort {
    private static int n;

    public static void sort(int[] arr) {
        heapify(arr);
        for (int i = n; i > 0; i--) {
            swap(arr, 0, i);
            n = n - 1;
            maxheap(arr, 0);
        }
    }

    private static void heapify(int[] arr) {
        n = arr.length - 1;
        for (int i = n/2; i >= 0; i--) {
            maxheap(arr, i);
        }
    }

    private static void maxheap(int[] arr, int i) {
        int left = 2*i;
        int right = 2*i + 1;
        int max = i;
        if (left <= n && arr[left] > arr[i]) {
            max = left;
        }
        if (right <= n && arr[right] > arr[i]) {
            max = right;
        }

        if (max != i) {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String... args) {
        int[] arr = {9, 7, 5, 2, 6, 4};
        Arrays.stream(arr).forEach(System.out::print);
        HeapSort.sort(arr);
        System.out.println();
        Arrays.stream(arr).forEach(System.out::print);
    }
}
