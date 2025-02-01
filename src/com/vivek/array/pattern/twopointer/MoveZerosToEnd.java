package com.vivek.array.pattern.twopointer;

import com.vivek.utils.Utils;

/**
 * Level: Easy
 * Given an array of integers, rearrange the elements such that all zeros are moved to the end of the array.
 * For example,
 * a = [4,2,0,1,0,3,0] -> [0,0,0,4,1,2,3]
 *
 * Questions to Clarify:
    Q. Does it matter what order we place the non-zero numbers? A. No, you can place them in any order.
    Q. What if there are no zeroes? A. Keep the array as it is.
    Solution:
    Keep a pointer named 'boundary' that tracks the zero-boundary at the end of the array.
    Everything after this boundary contains only zeros. We loop through the array from the end and place all zeroes into this boundary.
 *
 * Test Cases:
 * Edge Cases: Empty array, null array
 * Base Cases: one element (zero/non-zero)
 * Regular Cases: All zeroes, all non-zeroes, mix of zeroes and non-zeroes
 *
 * Time Complexity: O(n) Space Complexity: O(1)
 */
public class MoveZerosToEnd {

    public static void main(String[] args) {
        int[] arr = {4, 2, 0, 3, 0, 1, -1, 0};
        moveZerosToEnd(arr);
        Utils.printArray(arr);
    }

    static void moveZerosToEnd(int[] arr) {
        if (arr == null || arr.length == 0)
            return;

        int boundary = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                Utils.swapNumbers(arr, i, boundary);
                boundary--;
            }
        }
    }

}
