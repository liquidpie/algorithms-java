package com.vivek.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Find the nearest smaller numbers on left side in an array
 *
 * Given an array of integers, find the nearest smaller number for every element such that the smaller element is on the left side.
 *
 * Examples:
 *
 * Input:  arr[] = {1, 6, 4, 10, 2, 5}
 * Output:         {_, 1, 1,  4, 1, 2}
 * First element ('1') has no element on left side. For 6,
 * there is only one smaller element on left side '1'.
 * For 10, there are three smaller elements on left side (1,
 * 6 and 4), nearest among the three elements is 4.
 *
 * Input: arr[] = {1, 3, 0, 2, 5}
 * Output:        {_, 1, _, 0, 2}
 * Expected time complexity is O(n).
 *
 * Solution:
 * The idea is to use a stack. Stack is used to maintain a subsequence of the values that have been processed
 * so far and are smaller than any later value that has already been processed.
 *
 * Let input sequence be 'arr[]' and size of array be 'n'
 *
 * 1) Create a new empty stack S
 *
 * 2) For every element 'arr[i]' in the input sequence 'arr[]',
 *    where 'i' goes from 0 to n-1.
 *     a) while S is nonempty and the top element of
 *        S is greater than or equal to 'arr[i]':
 *            pop S
 *
 *     b) if S is empty:
 *            'arr[i]' has no preceding smaller value
 *     c) else:
 *            the nearest smaller value to 'arr[i]' is
 *            the top element of S
 *
 *     d) push 'arr[i]' onto S
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * https://www.geeksforgeeks.org/find-the-nearest-smaller-numbers-on-left-side-in-an-array/
 */
public class NearestSmallerNumber {

    public static void main(String[] args) {
        int[] arr = {1, 3, 0, 2, 5};
        int n = arr.length;
        int[] result = nearestSmaller(arr, n);
        System.out.println(Arrays.toString(result));
    }

    static int[] nearestSmaller(int[] arr, int n) {
        int[] result = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // Keep removing top element from S while the top
            // element is greater than or equal to arr[i]
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            // If all elements in S were greater than arr[i]
            result[i] = !stack.isEmpty() ? stack.peek() : Integer.MAX_VALUE;
            stack.push(arr[i]);
        }

        return result;
    }

}
