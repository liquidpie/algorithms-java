package com.vivek.deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * First negative integer in every window of size k
 *
 * Given an array and a positive integer k, find the first negative integer for each window(contiguous subarray) of size k. If a window does not contain a negative integer, then print 0 for that window.
 *
 * Examples:
 *
 * Input : arr[] = {-8, 2, 3, -6, 10}, k = 2
 * Output : -8 0 -6 -6
 *
 * First negative integer for each window of size k
 * {-8, 2} = -8
 * {2, 3} = 0 (does not contain a negative integer)
 * {3, -6} = -6
 * {-6, 10} = -6
 *
 * Input : arr[] = {12, -1, -7, 8, -15, 30, 16, 28} , k = 3
 * Output : -1 -1 -7 -15 -15 0
 *
 *
 * Approach 1:
 *
 * We create a Dequeue, Di of capacity k, that stores only useful elements of the current window of k elements.
 * An element is useful if it is in the current window and it is a negative integer.
 * We process all array elements one by one and maintain Di to contain useful elements of current window and
 * these useful elements are all negative integers. For a particular window, if Di is not empty then
 * the element at front of the Di is the first negative integer for that window, else that window does not contain a negative integer.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(k)
 *
 * https://www.geeksforgeeks.org/first-negative-integer-every-window-size-k/
 *
 */
public class SlidingWindowFirstNegative {

    public static void main(String[] args) {
        int[] arr = {12, -1, -7, 8, -15, 30, 16, 28};
        int n = arr.length;
        int k = 3;
        printFirstNegativeInSlidingWindow(arr, n, k);
        printFirstNegativeInSlidingWindowUsingIndex(arr, n, k);
    }

    static void printFirstNegativeInSlidingWindow(int[] arr, int n, int k) {
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            if (arr[i] < 0) {
                dq.add(i);
            }
        }

        for (int i = k; i < n; i++) {
            if (!dq.isEmpty()) {
                System.out.println(arr[dq.peek()]);
            } else {
                System.out.println("0");
            }
            // Remove the elements which are out of this window
            while (!dq.isEmpty() && dq.peek() < (i - k + 1)) {
                dq.remove();
            }
            // Add current element at the rear of dq if it is a negative integer
            if (arr[i] < 0) {
                dq.add(i);
            }
        }

        // Print the first negative integer of last window
        System.out.println(!dq.isEmpty() ? arr[dq.peek()] : "0");
    }

    /**
     * It is also possible to accomplish this with constant space. The idea is to have a variable firstNegativeIndex
     * to keep track of the first negative element in the k sized window. At every iteration,
     * we skip the elements which no longer fall under the current k size window (firstNegativeIndex <= i – k)
     * as well as the non-negative elements(zero or positive).
     *
     * Time Complexity: O(n)
     * Auxiliary Space: O(1)
     */
    static void printFirstNegativeInSlidingWindowUsingIndex(int[] arr, int n, int k) {
        int firstNegativeIndex = 0;
        int firstNegativeElem;

        for (int i = k - 1; i < n; i++) {
            while ((firstNegativeIndex < i) && (firstNegativeIndex <= i - k || arr[firstNegativeIndex] >= 0)) {
                firstNegativeIndex++;
            }

            firstNegativeElem = Math.min(arr[firstNegativeIndex], 0);
            System.out.println(firstNegativeElem);
        }
    }

}
