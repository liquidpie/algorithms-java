package com.vivek.array;

import java.util.Arrays;

/**
 * Merge two sorted arrays with O(1) extra space
 *
 * We are given two sorted arrays. We need to merge these two arrays such that the initial numbers (after complete sorting) are in the first array and the remaining numbers are in the second array. Extra space allowed in O(1).
 *
 * Example:
 *
 * Input: ar1[] = {10};
 *        ar2[] = {2, 3};
 * Output: ar1[] = {2}
 *         ar2[] = {3, 10}
 *
 * Input: ar1[] = {1, 5, 9, 10, 15, 20};
 *        ar2[] = {2, 3, 8, 13};
 * Output: ar1[] = {1, 2, 3, 5, 8, 9}
 *         ar2[] = {10, 13, 15, 20}
 *
 * https://www.geeksforgeeks.org/merge-two-sorted-arrays-o1-extra-space/
 */
public class MergeTwoSortedArrays {

    /**
     * Algorithm
     *
     * 1) Initialize i,j,k as 0,0,n-1 where n is size of arr1
     * 2) Iterate through every element of arr1 and arr2 using two pointers i and j respectively
     *     if arr1[i] is less than arr2[j]
     *         increment i
     *     else
     *         swap the arr2[j] and arr1[k]
     *         increment j and decrement k
     *
     * 3) Sort both arr1 and arr2
     *
     * Time Complexity: The time complexity while traversing the arrays in while loop is O(n+m) in worst case and sorting is O(nlog(n) + mlog(m)). So overall time complexity of the code becomes O((n+m)log(n+m)).
     * Space Complexity: As the function doesn’t use any extra array for any operations, the space complexity is O(1).
     */
    static void mergeArrays(int[] arr1, int[] arr2) {
        if (arr1.length < arr2.length) {
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }

        int m = arr1.length;
        int n = arr2.length;

        int i = 0;
        int j = 0;
        int k = m - 1;

        while (i <= k && j < n) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else {
                int temp = arr2[j];
                arr2[j] = arr1[k];
                arr1[k] = temp;

                j++;
                k--;
            }
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    public static void main(String[] args) {
        int[] arr1 = new int[] { 1, 5, 9, 10, 15, 20 };
        int[] arr2 = new int[] { 2, 3, 8, 13 };

        mergeArrays(arr1, arr2);

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

    }

}
