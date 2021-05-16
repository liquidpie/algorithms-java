package com.vivek.array;

import java.util.Arrays;

/**
 * Find the smallest positive number missing from an unsorted array
 *
 * You are given an unsorted array with both positive and negative elements. You have to find the smallest positive number missing from the array in O(n) time using constant extra space. You can modify the original array.
 *
 * Examples
 *
 *  Input:  {2, 3, 7, 6, 8, -1, -10, 15}
 *  Output: 1
 *
 *  Input:  { 2, 3, -7, 6, 8, 1, -10, 15 }
 *  Output: 4
 *
 *  Input: {1, 1, 0, -1, -2}
 *  Output: 2
 *
 *  Approach:
 *  We use array elements as index. To mark presence of an element x, we change the value at the index x to negative. But this approach doesn’t work if there are non-positive (-ve and 0) numbers. So we segregate positive from negative numbers as first step and then apply the approach.
 *
 * Following is the two step algorithm.
 * 1) Segregate positive numbers from others i.e., move all non-positive numbers to left side. In the following code, segregate() function does this part.
 * 2) Now we can ignore non-positive elements and consider only the part of array which contains all positive elements. We traverse the array containing all positive numbers and to mark presence of an element x, we change the sign of value at index x to negative. We traverse the array again and print the first index which has positive value. In the following code, findMissingPositive() function does this part. Note that in findMissingPositive, we have subtracted 1 from the values as indexes start from 0 in C.
 *
 *  Reference:
 *  https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/
 *  https://stackoverflow.com/questions/51346136/given-an-array-of-integers-find-the-first-missing-positive-integer-in-linear-ti
 */
public class FirstMissingPositive {

    static int findMissingPositive(int[] arr) {
        int n = arr.length;
        int start = segregate(arr, n);

        for (int i = start; i < n; i++) {
            int x = Math.abs(arr[i]);
            int index = x - 1 + start;
            if (index < n && index >= 0 && arr[index] > 0) {
                arr[index] = -arr[index];
            }
        }

        for (int i = start; i < n; i++) {
            if (arr[i] > 0) {
                return i - start + 1;
            }
        }

        return n - start + 1;

    }

    private static int segregate(int[] arr, int n) {
        int pivot = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] < 1) {
                swap(arr, i, pivot);
                pivot++;
            }
        }
        return pivot;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
//        int[] arr = {2, 3, 7, 6, 8, -1, -10, 15};
//        int[] arr = {1, 1, 0, -1, -2};
        int[] arr = {1, 2, 0};
        System.out.println(findMissingPositive(arr));
        System.out.println(Arrays.toString(arr));
    }

}
