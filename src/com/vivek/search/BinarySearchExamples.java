package com.vivek.search;

public class BinarySearchExamples {

    /**
     * Given a sorted array of N distinct integers, find floor value of input ‘key’.
     * Say, A = {-1, 2, 3, 5, 6, 8, 9, 10} and key = 7, we should return 6 as outcome.
     */
    static class Floor {
        static int floorValue(int[] arr, int key) {
            if (arr == null || arr.length == 0 || key < arr[0])
                return -1;

            int index = floor(arr, 0, arr.length - 1, key);
            return index != -1 ? arr[index] : -1;
        }

        private static int floor(int[] arr, int low, int high, int key) {
            if (key >= arr[high])
                return high;

            while (low < high) {
                int mid = (low + high) / 2;

                // If middle point is floor
                if (key == arr[mid])
                    return mid;
                // If key lies between mid-1 and mid
                else if (mid > 0 && arr[mid - 1] <= key && key < arr[mid])
                    return mid - 1;
                else if (key < arr[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {-1, 2, 3, 5, 6, 8, 9, 10};
        System.out.println(Floor.floorValue(arr, 7));
    }

}
