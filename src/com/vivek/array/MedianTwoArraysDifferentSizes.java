package com.vivek.array;

import java.util.Arrays;

/**
 * Median of two sorted arrays of different sizes
 *
 * Approach:The idea is simple, calculate the median of both the arrays and discard one half of each array.
 * Now, there are some basic corner cases. For array size less than or equal to 2
 *
 *     Suppose there are two arrays and the size of both the arrays is greater than 2.
 *     Find the middle element of the first array and middle element of the second array (the first array is smaller than the second) if the middle element of the smaller array is less than the second array, then it can be said that all elements of the first half of smaller array will be in the first half of the output (merged array). So, reduce the search space by ignoring the first half of the smaller array and the second half of the larger array. Else ignore the second half of the smaller array and first half of a larger array.
 *
 * In addition to that there are more basic corner cases:
 *
 *     If the size of smaller array is 0. Return the median of a larger array.
 *     if the size of smaller array is 1.
 *         The size of the larger array is also 1. Return the median of two elements.
 *         If the size of the larger array is odd. Then after adding the element from 2nd array, it will be even so the median will be an average of two mid elements. So the element from the smaller array will affect the median if and only if it lies between (m/2 – 1)th and (m/2 + 1)th element of the larger array. So, find the median in between the four elements, the element of the smaller array and (m/2)th, (m/2 – 1)th and (m/2 + 1)th element of a larger array
 *         Similarly, if the size is even, then check for the median of three elements, the element of the smaller array and (m/2)th, (m/2 – 1)th element of a larger array
 *     If the size of smaller array is 2
 *         If the larger array also has two elements, find the median of four elements.
 *         If the larger array has an odd number of elements, then the median will be one of the following 3 elements
 *             Middle element of larger array
 *             Max of the first element of smaller array and element just before the middle, i.e M/2-1th element in a bigger array
 *             Min of the second element of smaller array and element
 *             just after the middle in the bigger array, i.e M/2 + 1th element in the bigger array
 *         If the larger array has even number of elements, then the median will be one of the following 4 elements
 *             The middle two elements of the larger array
 *             Max of the first element of smaller array and element just before the first middle element in the bigger array, i.e M/2 – 2nd element
 *             Min of the second element of smaller array and element just after the second middle in the bigger array, M/2 + 1th element
 *
 * How can one half of each array be discarded?
 *
 *     Let’s take an example to understand this
 *     Input :arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
 *     brr[] = { 11, 12, 13, 14, 15, 16, 17, 18, 19 }
 *     Dry Run of the code:
 *     Recursive call 1:
 *     smaller array[] = 1 2 3 4 5 6 7 8 9 10, mid = 5
 *     larger array[] = 11 12 13 14 15 16 17 18 19 , mid = 15
 *
 *     5 < 15
 *     Discard first half of the first array and second half of the second array
 *
 *     Recursive call 2:
 *     smaller array[] = 11 12 13 14 15, mid = 13
 *     larger array[] = 5 6 7 8 9 10, mid = 7
 *
 *     7 < 13
 *     Discard first half of the second array and second half of the first array
 *
 *     Recursive call 3:
 *     smaller array[] = 11 12 13 , mid = 12
 *     larger array[] = 7 8 9 10 , mid = 8
 *
 *     8 < 12
 *     Discard first half of the second array and second half of the first array
 *
 *     Recursive call 4:
 *     smaller array[] = 11 12
 *     larger array[] = 8 9 10
 *
 *     Size of the smaller array is 2 and the size of the larger array is odd
 *     so, the median will be the median of max( 11, 8), 9, min( 10, 12)
 *     that is 9, 10, 11, so the median is 10.
 *
 *     Output:10.000000
 *
 * Algorithm:
 *
 *
 *     Create a recursive function that takes two arrays and the sizes of both the arrays.
 *     Take care of the base cases for the size of arrays less than 2. (previously discussed in Approach).Note: The first array is always the smaller array.
 *     Find the middle elements of both the arrays. i.e element at (n – 1)/2 and (m – 1)/2 of first and second array respectively. Compare both the elements.
 *     If the middle element of the smaller array is less than the middle element of the larger array then the first half of smaller array is bound to lie strictly in the first half of the merged array. It can also be stated that there is an element in the first half of the larger array and second half of the smaller array which is the median. So, reduce the search space to the first half of the larger array and second half of the smaller array.
 *     Similarly, If the middle element of the smaller array is greater than the middle element of the larger array then reduce the search space to the first half of the smaller array and second half of the larger array.
 *
 * Reference:
 * https://redquark.org/leetcode/0004-median-of-two-sorted-arrays/
 * https://medium.com/@hazemu/finding-the-median-of-2-sorted-arrays-in-logarithmic-time-1d3f2ecbeb46
 */
public class MedianTwoArraysDifferentSizes {

    // ToDo: this solution doesn't work for the given input. check references to update the code

    static double getMedianDifferentSizeArray(int[] arr1, int[] arr2) {
        return arr1.length < arr2.length
                ? findMedian(arr1, arr2, arr1.length, arr2.length)
                : findMedian(arr2, arr1, arr2.length, arr1.length);
    }

    private static double findMedian(int[] arr1, int[] arr2, int m, int n) {
        if (m == 0) // If smaller array is empty, return median from second array
            return medianOfSingleArray(arr2);
        else if (m == 1) { // If the smaller array has only one element
            // Case 1: If the larger array also has one element
            // Take median of both arrays
            if (n == 1)
                return medianOfTwo(arr1[0], arr2[0]);

            // Case 2: If the larger array has odd number of elements,
            // then consider the middle 3 elements of larger array and the only element of smaller array.
            // element from the smaller array will affect the median if and only if it lies between (m/2 – 1)th and (m/2 + 1)th element of the larger array
            if (n % 2 == 1)
                return medianOfTwo(arr2[n / 2], (int) medianOfThree(arr2[n / 2 - 1], arr1[0], arr2[n / 2 + 1]));

            // Case 3: If the larger array has even number of element
            // then median will be one of the following 3 elements
            // ... The middle two elements of larger array
            // ... The only element of smaller array
            return medianOfThree(arr1[0], arr2[n / 2], arr2[n / 2 - 1]);
        } else if (m == 2) { // If the smaller array has two elements
            // Case 4: If the larger array also has two elements,
            // Take median of two arrays
            if (n == 2)
                return medianOfFour(arr1[0], arr1[1], arr2[0], arr2[1]);

            // Case 5: If the larger array has odd number of elements,
            // then median will be one of the following 3 elements
            // 1. Middle element of larger array
            // 2. Max of first element of smaller array and element just before the middle in bigger array
            // 3. Min of second element of smaller array and element just after the middle in bigger array
            if (n % 2 == 1)
                return medianOfThree(arr2[n / 2], Math.max(arr1[0], arr2[n / 2 - 1]), Math.min(arr1[1], arr2[n / 2 + 1]));

            // Case 6: If the larger array has even number of elements,
            // then median will be one of the following 4 elements
            // 1) & 2) The middle two elements of larger array
            // 3) Max of first element of smaller array and element just before the first middle element in bigger array
            // 4. Min of second element of smaller array and element just after the second middle in bigger array
            return medianOfFour(arr2[n / 2], arr2[n / 2 - 1], Math.max(arr1[0], arr2[n / 2 - 2]), Math.min(arr1[1], arr2[n / 2 + 1]));
        }

        int idxA = (m - 1) / 2;
        int idxB = (n - 1) / 2;
        int[] firstArr, secondArr;

        // if A[idxA] <= B[idxB], then median must exist in A[idxA....] and B[....idxB]
        if (arr1[idxA] <= arr2[idxB]) {
            firstArr = Arrays.copyOfRange(arr1, idxA, m);
            secondArr = Arrays.copyOfRange(arr2, 0, idxB + 1);
            return findMedian(firstArr, secondArr, firstArr.length, secondArr.length);
        }


        // if A[idxA] > B[idxB], then median must exist in A[...idxA] and B[idxB....]
        firstArr = Arrays.copyOfRange(arr1, 0, idxA + 1);
        secondArr = Arrays.copyOfRange(arr2, idxB, n);
        return findMedian(firstArr, secondArr, firstArr.length, secondArr.length);
    }

    private static double medianOfTwo(int a, int b) {
        return (a + b) / 2.0;
    }

    private static double medianOfThree(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        return a + b + c - max - min;
    }

    private static double medianOfFour(int a, int b, int c, int d) {
        int max = Math.max(a, Math.max(b, Math.max(c, d)));
        int min = Math.min(a, Math.min(b, Math.min(c, d)));
        return (a + b + c + d - max - min) / 2.0;
    }

    private static double medianOfSingleArray(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return -1;
        if (n % 2 == 0)
            return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;

        return arr[n / 2];
    }

    public static void main(String[] args) {
//        int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
//        int[] arr2 = { 11, 12, 13, 14, 15, 16, 17, 18, 19 };
        int[] arr1 = { 1,5,6 };
        int[] arr2 = { 2,3,4,7,8 };

        System.out.println(getMedianDifferentSizeArray(arr1, arr2));
    }

}
