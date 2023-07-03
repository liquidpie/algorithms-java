package com.vivek.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Next Smaller Element
 *
 * Given an array, print the Next Smaller Element (NSE) for every element. The NSE for an element x is the first smaller element on the right side of x in the array. Elements for which no smaller element exist (on the right side), consider NSE as -1.
 *
 * Examples:
 *
 * a) For any array, the rightmost element always has NSE as -1.
 * b) For an array that is sorted in increasing order, all elements have NSE as -1.
 * c) For the input array [4, 8, 5, 2, 25}, the NSE for each element is as follows.
 *
 * Element         NSE
 *    4      -->    2
 *    8      -->    5
 *    5      -->    2
 *    2      -->   -1
 *    25     -->   -1
 *
 * https://www.geeksforgeeks.org/next-smaller-element/
 */
public class NextSmallerElement {

    public static void main(String[] args) {
        int[] arr = { 11, 13, 21, 3 };
        int n = arr.length;
        System.out.println(Arrays.toString(printNSE(arr, n)));
    }

    public static int[] printNSE(int[] arr, int n) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(-1);
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int curr = arr[i];
            while (!s.isEmpty() && s.peek() > curr)
                s.pop();

            ans[i] = s.peek();
            s.push(curr);
        }
        return ans;
    }

}
