package com.vivek.array;

/**
 * Returns the size of the new array after removing duplicates
 */
public class RemoveDuplicates {

    static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;

        int j = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1])
                arr[j++] = arr[i];
        }
        arr[j++] = arr[arr.length - 1];
        return j;
    }

}
