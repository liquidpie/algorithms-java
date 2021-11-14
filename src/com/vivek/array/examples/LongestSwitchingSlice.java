package com.vivek.array.examples;

/**
 * We call an array switching if all numbers in even positions are equal and odd positions are equal.
 * For example: [-3, 7, -3, 7, -3] and [4, 4, 4] are switching but [5, 5, 4, 5, 4] are not switching.
 *
 * Find the length of the longest switching slice (continuous fragment) in a given array
 *
 * Examples:
 *
 * 1. arr = {7, 4, -2, 4, -2, 9}, the function should return 4. The longest switching slice is {4, -2, 4, -2}
 * 2. arr = {4}, result = 1, A single-element slice is also a switching slice
 * 3. arr = {7, -5, -5, -5, 7, -1, 7}, result = 3. There are two slices of equal length: {-5, -5, -5} and {7, -1, 7}
 */
public class LongestSwitchingSlice {

    public int solution(int[] A) {
        if (A.length == 1) {
            return A.length;
        }
        int result = 0;
        int odd = A[0];
        int even = A[1];

        int counter = 0;
        for (int i = 2; i < A.length; i++) {
            if (i % 2 == 1) {
                if (A[i] == even) {
                    counter = counter == 0 ? 3 : (counter + 1);
                } else {
                    even = A[i];
                    counter = 0;
                }
            }
            if (i % 2 == 0) {
                if (A[i] == odd) {
                    counter = counter == 0 ? 3 : (counter + 1);
                } else {
                    odd = A[i];
                    counter = 0;
                }
            }

            result = Math.max(result, counter);

        }

        return result;

    }

    public static void main(String[] args) {
        LongestSwitchingSlice lss = new LongestSwitchingSlice();
//        int[] arr = {7, 4, -2, 4, -2, 9};
        int[] arr = {4};
        System.out.println(lss.solution(arr));
    }

}
