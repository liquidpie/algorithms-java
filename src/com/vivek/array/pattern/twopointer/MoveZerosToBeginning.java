package com.vivek.array.pattern.twopointer;

import com.vivek.utils.Utils;

/**
 * Level: Easy
 * You are given an array of integers. Rearrange the array so that all zeroes are at the beginning of the array.
 * For example,
 * a = [4,2,0,1,0,3,0] -> [0,0,0,4,1,2,3]
 *
 * Questions to Clarify:
 * Q. What if there are no zeroes?
 * A. Then the array will be unchanged.
 * Q. After the re-arrangement, do non-zero elements need to be in the same order as they were before? A. No, they need not be in the same order.
 *
 * Variation of this Problem: If you want to keep non-zero elements in the same order, you can modify this algorithm a bit -
 * instead of moving zeros to the beginning (creating a beginning partition), you move all non-zeros to the end (creating an end partition).
 *
 * Solution:
 * We keep one variable to track the boundary. The boundary represents the partition between zero and non-zero elements.
 * We loop through the array's elements. For every zero we encounter, we move it into the boundary and expand the boundary by 1.
 * At the end, all elements in the boundary will be zeroes.
 *
 * Test Cases:
 * Edge Cases: Empty array, null array
 * Base Cases: one element (zero/non-zero)
 * Regular Cases: All zeroes, all non-zeroes, mix of zeroes and non-zeroes
 *
 * Time Complexity: O(n) Space Complexity: O(1)
 */
public class MoveZerosToBeginning {

    public static void main(String[] args) {
        int[] arr = {4, 2, 0, 3, 0, 1, -1, 0};
        moveZerosToBeginning(arr);
        Utils.printArray(arr);
    }

    static void moveZerosToBeginning(int[] arr) {
        if (arr == null || arr.length == 0)
            return;

        int boundary = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                Utils.swapNumbers(arr, i, boundary);
                boundary++;
            }
        }
    }

}
