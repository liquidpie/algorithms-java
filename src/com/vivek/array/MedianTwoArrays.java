package com.vivek.array;

import java.util.Arrays;

/**
 * Find median of two sorted arrays
 */
public class MedianTwoArrays {

    /**
     * Find median of two sorted arrays when both the arrays are of same size
     *
     * Since size of the set for which we are looking for median is even (2n),
     * we need take average of middle two numbers and return floor of the average.
     *
     * Algorithm:
     1) Calculate the medians m1 and m2 of the input arrays ar1[]
     and ar2[] respectively.
     2) If m1 and m2 both are equal then we are done.
     return m1 (or m2)
     3) If m1 is greater than m2, then median is present in one
     of the below two subarrays.
        a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
        b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
     4) If m2 is greater than m1, then median is present in one
     of the below two subarrays.
        a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
        b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
     5) Repeat the above process until size of both the subarrays
     becomes 2.
     6) If size of the two arrays is 2 then use below formula to get
     the median.
     Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
     */
    int getMedianSameSizeArray(int[] arr1, int[] arr2) {
        int n = arr1.length;

        if (n == 0)
            return -1;
        if (n == 1)
            return (arr1[0] + arr2[0]) / 2;
        if (n == 2)
            return (Math.max(arr1[0], arr2[0]) + Math.min(arr1[1], arr2[1])) / 2;

        int m1 = median(arr1, n);
        int m2 = median(arr2, n);

        if (m1 == m2)
            return m1;

        /* if m1 < m2 then median must exist in arr1[m1....] and arr2[....m2] */
        int mid = n / 2;
        if (m1 < m2) {

            if (n % 2 == 0) {
                return getMedianSameSizeArray(Arrays.copyOfRange(arr1, mid - 1, n), Arrays.copyOfRange(arr2, 0, mid + 1));
            }
            return getMedianSameSizeArray(Arrays.copyOfRange(arr1, mid, n), Arrays.copyOfRange(arr2, 0, mid + 1));
        }
        /* if m1 > m2 then median must exist in arr1[....m1] and arr2[m2...] */
        if (n % 2 == 0) {
            return getMedianSameSizeArray(Arrays.copyOfRange(arr2, mid - 1, n), Arrays.copyOfRange(arr1, 0, mid + 1));
        }
        return getMedianSameSizeArray(Arrays.copyOfRange(arr2, mid, n), Arrays.copyOfRange(arr1, 0, mid + 1));

    }

    int median(int[] arr, int n) {
        int mid = n / 2;
        if (n % 2 == 0)
            return (arr[mid] + arr[mid - 1]) / 2;
        return arr[mid];
    }

    public static void main(String[] args) {
        MedianTwoArrays test = new MedianTwoArrays();
        int[] arr1 = {1, 12, 15, 26, 38};
        int[] arr2 = {2, 13, 17, 30, 45};
        int median = test.getMedianSameSizeArray(arr1, arr2);
        System.out.println(median);
    }

}
