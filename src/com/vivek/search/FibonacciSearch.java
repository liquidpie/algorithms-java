package com.vivek.search;

/**
 * Similar to binary search, Fibonacci search is also a divide and conquer algorithm and needs a sorted list.
 * It also divides the list into two parts, checks the target with the item in the centre of the two parts,
 * and eliminates one side based on the comparison. So how exactly is it different from binary search?
 *
 * In the Fibonacci search, we use the Fibonacci numbers to divide the list into two parts,
 * so it will divide the list into two parts of different lengths. Also, instead of performing division to do that,
 * it performs addition which is less taxing on the CPU. Now let’s dive into the details.
 *
 * First, we need to have the length of the given list. Then we find the smallest Fibonacci number greater than or equal to the size of the list.
 * That means if the size of the list is 100, then the smallest Fibonacci number greater than 100 is 144.
 * Let us say that this is the nth Fibonacci number. In the above example, 144 is the 12th Fibonacci number.
 *
 * After this, we move back twice in the Fibonacci series from that number.
 * Essentially, we find the (n-2)th Fibonacci number. So in the above example, we had found the 12th Fibonacci number which is 144,
 * so we need the 10th one which is 55.
 *
 * We use this as the index to divide the list into two parts. That is, we look at this index in the list,
 * and assuming the list is sorted in increasing order, if the item at this index is smaller than the target,
 * then we eliminate the left side, otherwise, we eliminate the right side. We do this until we find the item we are looking for,
 * which will happen when the calculated index’s item will match the target.
 *
 * https://www.geeksforgeeks.org/fibonacci-search
 */
public class FibonacciSearch {

    public static int search(int[] arr, int key) {
        if (arr == null || arr.length == 0)
            return -1;

        int n = arr.length;

        int f0 = 0;
        int f1 = 1;
        int f2 = f1 + f0;

        while (f2 < n) {
            f0 = f1;
            f1 = f2;
            f2 = f1 + f0;
        }

        int start = -1;

        // while there are elements to be inspected. Note that we compare arr[f0] with key. When f2 becomes 1, f0 becomes 0
        while (f2 > 1) {
            int index = Math.min(start + f0, n - 1);
            // If key is greater than the value at index f0, cut the subarray array from start to index
            if (arr[index] < key) {
                f2 = f1;
                f1 = f0;
                f0 = f2 - f1;
                start = index;
            } else if (key < arr[index]) { // If key is less than the value at index f0, cut the subarray after index + 1
                f2 = f0;
                f1 = f1 - f0;
                f0 = f2 - f1;
            } else {
                return index;
            }
        }

        // comparing the last element with key
        if (f1 == 1 && arr[n - 1] == key)
            return n - 1;

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        System.out.println(search(arr, 13));
    }

}
