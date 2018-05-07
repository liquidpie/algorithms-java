package com.vivek.array;

/**
 * Return a string of an array after range compaction
 * A range should contain at least 3 numbers.
 * For ex:
 * For an array: -3, -2, -1, 2, 3, 4, 7, 9, 10, 11
 * Output: -3--1,2-4,7,9-11
 */
public class ArrayRangeCompacting {

    public static void main(String[] args) {
        int[] arr = {-3, -2, -1, 2, 3, 4, 7, 9, 10, 11};
//        int[] arr = {12, 13, 14, 15, 16};
//        int[] arr = {12, 13, 15, 16, 17};
//        int[] arr = {12, 13, 15, 16, 18, 19};
        System.out.println(compact(arr));
    }

    static String compact(int[] array) {
        /*
         * Write your code here.
         */
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(array[0]));
        int rangeSize = 1;
        int prev = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] - prev == 1) {
                rangeSize++;
                if (i == array.length - 1) {
                    if (rangeSize > 2) {
                        sb.append("-").append(String.valueOf(array[i]));
                    } else {
                        sb.append(",").append(String.valueOf(array[i]));
                    }
                }
            } else {
                if (rangeSize > 2) {
                    sb.append("-").append(String.valueOf(prev));
                } else if (rangeSize == 2) {
                    sb.append(",").append(String.valueOf(prev));
                }
                sb.append(",").append(String.valueOf(array[i]));
                rangeSize = 1;
            }
            prev = array[i];
        }

        return sb.toString();
    }

}
