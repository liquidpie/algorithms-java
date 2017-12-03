package com.vivek.array;

import java.util.Arrays;

/**
 * Created by VJaiswal on 04/04/17.
 *
 * Given an array, we need to find maximum sum subarray, removing one element is allowed to get the maximum sum.
 * Not consecutive numbers could be removed
 *
 * Ex:
 * 1. array = {9, -1, -3, 4, 5}, result = 17;
 * 2. array = {-1, -2, -3, -4, -5}, result = -6;
 */
public class MaxSumSubArrayDelAtMostOneElement {

    public static void main(String... args) {
        int arr[] = {9, -1, -3, 4, 5};
        int arr1[] = {-1, -2, -3, -4, -5};

        System.out.println(findMaxSum(arr));
    }

    static int findMaxSum(int[] arr) {
        int n = arr.length;
//        Maximum sum subarrays in forward and backward directions
        int[] fwdArr = new int[n];
        int[] bkdArr = new int[n];

        Arrays.fill(fwdArr, 0);
        Arrays.fill(bkdArr, 0);

//        Initialize current max and max so far.
        int curMax = arr[0];
        int maxSoFar = arr[0];

//        calculating maximum sum subarrays in forward direction
        for (int i = 0; i < n; i++) {
            curMax = Math.max(arr[i], curMax + arr[i]);
            maxSoFar = Math.max(maxSoFar, curMax);

//        storing current maximum till ith, in forward array
            fwdArr[i] = curMax;
        }

//        calculating maximum sum subarrays in backward direction
        curMax = arr[n - 1];
        maxSoFar = arr[n - 1];
        bkdArr[n - 1] = arr[n - 1];
        int i = n - 2;
        while (i >= 0) {
            curMax = Math.max(arr[i], curMax + arr[i]);
            maxSoFar = Math.max(maxSoFar, curMax);

//        storing current maximum till ith, in forward array
            bkdArr[i] = curMax;
            i--;
        }

        int result = maxSoFar;

        for (int j = 1; j < n - 1; j++) {
            result = Math.max(result, fwdArr[j - 1] + bkdArr[j + 1]);
        }

        return result;

    }
}
