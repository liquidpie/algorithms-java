package com.vivek.array;

/**
 * Created by VJaiswal on 26/11/17.
 */
public class SortZeroOneTwoArray {

    static void sort(int[] arr) {

        int low = 0;
        int high = arr.length - 1;

        int mid = 0;
        int temp;

        while (mid <= high) {

            switch (arr[mid]) {

                case 0:
                    temp = arr[mid];
                    arr[mid] = arr[low];
                    arr[low] = temp;
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    temp = arr[mid];
                    arr[mid] = arr[high];
                    arr[high] = temp;
                    mid++;
                    high--;
                    break;
            }
        }
    }

}
