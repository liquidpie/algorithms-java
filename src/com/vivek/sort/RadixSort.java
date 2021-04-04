package com.vivek.sort;

import java.util.Arrays;

/**
 * The radix-sort algorithm sorts a sequence S of entries with keys that are pairs,
 * by applying a stable bucket-sort on the sequence twice; first using one component
 * of the pair as the key when ordering and then using the second component.
 *
 * The idea of Radix Sort is to do digit by digit sort starting from least significant digit to most significant digit.
 * Radix sort uses counting sort as a subroutine to sort.
 *
 * Radix sort is a sorting technique that sorts the elements by first grouping the individual digits of the same place value.
 * Then, sort the elements according to their increasing/decreasing order.
 *
 * Suppose, we have an array of 8 elements. First, we will sort elements based on the value of the unit place.
 * Then, we will sort elements based on the value of the tenth place. This process goes on until the last significant place.
 *
 */
public class RadixSort {

    static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(arr, place, 10);
        }
    }

    private static void countingSort(int[] arr, int place, int radix) {
        int n = arr.length;
        int[] counts = new int[radix]; // Range from 0 - (radix-1)

        // count the occurrences
        for (int i = 0; i < n; i++) {
            ++counts[(arr[i] / place) % 10];
        }

        // cumulative sum of counts
        for (int i = 1; i < radix; i++) {
            counts[i] += counts[i - 1];
        }

        int[] sorted = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            sorted[--counts[(arr[i] / place) % 10]] = arr[i];
        }

        for (int i = 0; i < n; i++) {
            arr[i] = sorted[i];
        }

    }

    public static void main(String[] args) {
        int[] arr = { 121, 432, 564, 23, 1, 45, 788 };
        System.out.println(Arrays.toString(arr));

        radixSort(arr);

        System.out.println(Arrays.toString(arr));
    }

}
