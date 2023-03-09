package com.vivek.array.pattern.partitioning;

import com.vivek.utils.Utils;

/**
 * Level: Medium
 * You are given an array of integers and a pivot. Rearrange the array in the
 * following order:
 * [all elements less than pivot, elements equal to pivot, elements greater than pivot]
 * For example,
 * a = [5,2,4,4,6,4,4,3] and pivot = 4 --> result = [3,2,4,4,4,4,5,6].
 *  Questions to Clarify:
 * Q. Do numbers on each side need to be sorted? A. No, they need not be sorted
 * Q. What if there are no numbers less than pivot? A. Then that portion (< a[X]) will not exist.
 * Q. Do the numbers need to be in original order after re-arrangement?
 * e.g, if [4,1,4,3,5] and pivot=4, does 1 need to be before 3? E.g [1,3,4,4,5] or can it also be [3,1,4,4,5]
 * A. They need not be in original order. Any order is ok.
 * Solution:
 * In this problem, we have to divide the array into 3 sections.
 * We keep 2 boundaries - low and high. Low starts at 0, High starts at the end of the array.
 * Low contains numbers less than pivot. High contains numbers greater than pivot. We walk through the array.
 * If we encounter a number less than pivot, we put it in the low boundary.
 * If we encounter a number greater than pivot, we put it in the high boundary.
 * If the number is equal to pivot, we ignore it, effectively placing it in the middle of the two boundaries.
 *
 * Test Cases:
 * Edge Cases: empty array, null array, pivot not in array
 * Base Cases: single element, two elements
 * Regular Cases: Single element in pivot, multiple pivots, pivot is max/min element
 * Time Complexity: O(n) Space Complexity: O(1)
 */
public class DutchNationalFlag {

    public static void main(String[] args) {
        int[] arr = {4,1,4,3,5};
        rearrangeByPivot(arr, 4);
        Utils.printArray(arr);
    }

    static void rearrangeByPivot(int[] arr, int pivot) {
        if (arr == null || arr.length == 0)
            return;

        int low = 0; int high = arr.length - 1; int i = 0;

        while (i <= high) {
            if (arr[i] < pivot) {
                Utils.swapNumbers(arr, low, i);
                low++;
                i++;
            } else if (arr[i] > pivot) {
                Utils.swapNumbers(arr, high, i);
                high--;
                // don't increment i because the current i needs to be processed
                // as it came from ahead
            } else {
                i++;
            }
        }
    }

}
