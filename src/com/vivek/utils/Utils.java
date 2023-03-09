package com.vivek.utils;

import java.util.Arrays;

public class Utils {

    public static void swapNumbers(int[] arr, int indexA, int indexB) {
        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    public static void swap(Object[] arr, int indexA, int indexB) {
        Object temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    public static void printArray(Object[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}
