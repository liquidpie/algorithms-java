package com.vivek.array;

/**
 * Created by VJaiswal on 26/11/17.
 */
public class SegregateZeroOneArray {

    static void segregateZeroAndOne(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            // keep traversing while you find 0
            while (arr[left] == 0 && left < right) {
                left++;
            }

            // keep traversing while you find 1
            while (arr[right] == 1 && left < right) {
                right--;
            }

            // If left is smaller than right then there is a 1 at left
            // and a 0 at right.  Exchange arr[left] and arr[right]
            if (left < right) {
                arr[left] = 0;
                arr[right] = 1;
                left++;
                right--;
            }
        }
    }

}
