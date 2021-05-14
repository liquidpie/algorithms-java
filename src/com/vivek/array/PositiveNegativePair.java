package com.vivek.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of distinct integers, print all the pairs having positive value and negative value of a number that exists in the array. We need to print pairs in order of their occurrences.
 *
 * Examples:
 *
 * Input  :  arr[] = { 1, -3, 2, 3, 6, -1 }
 * Output : -1 1 -3 3
 *
 * Input  :  arr[] = { 4, 8, 9, -4, 1, -1, -8, -9 }
 * Output : -1 1 -4 4 -8 8 -9 9
 */
public class PositiveNegativePair {

    static void printPairs(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                continue;
            set.add(-arr[i]);
            if (set.contains(arr[i]))
                System.out.printf("(%s, %s) " , arr[i], -arr[i]);
        }
    }

    /**
     * Returns the largest integer K > 0 such that both values K and -K exist in array A. If there is no such integer, the function should return 0.
     */
    static int largestNumber(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                continue;
            set.add(-arr[i]);
            if (set.contains(arr[i]))
                res = Math.max(res, Math.abs(arr[i]));
        }
        return res;
    }

    static int largestNumber2(int[] arr) {
        Arrays.sort(arr);

        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum == 0)
                return arr[r];

            if (sum < 0)
                l++;
            else
                r--;
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 3, -2, 2, 1, 0, -3, 4, -5};

        printPairs(arr);

        System.out.println();
        System.out.println(largestNumber(arr));
        System.out.println(largestNumber2(arr));
    }

}
