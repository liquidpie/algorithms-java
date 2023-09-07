package com.vivek.array.pattern.leftright;

import java.util.Arrays;

/**
 * Squaring a Sorted Array
 *
 * Given a sorted array, create a new array containing squares of all the number of the input array in the
 * sorted order.
 *
 * Example 1:
 * Input: [-2, -1, 0, 2, 3]
 * Output: [0, 1, 4, 4, 9]
 *
 * Example 2:
 * Input: [-3, -1, 0, 1, 2]
 * Output: [0 1 1 4 9]
 *
 * Solution:
 * An easier approach could be to first find the index of the first non-negative number in the array.
 * After that, we can use Two Pointers to iterate the array.
 * One pointer will move forward to iterate the non-negative numbers and the other pointer will move backward to iterate the negative numbers.
 * At any step, whichever number gives us a bigger square will be added to the output array.
 * For the above-mentioned Example-1, we will do something like this:
 *     <- ptr1 - | - ptr2 ->
 *      -2    -1   0   2   3
 *
 * Since the numbers at both the ends can give us the largest square, an alternate approach could be to use two pointers
 * starting at both the ends of the input array. At any step, whichever pointer gives us the bigger square we add it to
 * the result array and move to the next/previous number according to the pointer. For the above- mentioned Example-1,
 * we will do something like this:
 *      ptr1              ptr2
 *       '                 '
 *      -2    -1   0   2   3
 */
public class SquaringSortedArray {

    public static void main(String[] args) {
        int[] arr = {-2, -1, 0, 2, 3};
        System.out.println(Arrays.toString(makeSquares(arr)));
    }

    static int[] makeSquares(int[] arr) {
        int[] result = new int[arr.length];
        int left = 0;
        int right = arr.length - 1;
        int idx = arr.length - 1;
        while (left < right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];
            if (leftSquare >= rightSquare) {
                result[idx--] = leftSquare;
                left++;
            } else {
                result[idx--] = rightSquare;
                right--;
            }
        }
        return result;
    }

}
