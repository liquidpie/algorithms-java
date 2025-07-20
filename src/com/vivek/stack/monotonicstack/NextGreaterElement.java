package com.vivek.stack.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array, return the Next Greater Element (NGE) for every element.
 * The Next greater Element for an element x is the first greater element on the right side of x in array.
 * Elements for which no greater element exist, consider next greater element as -1.
 */
public class NextGreaterElement {

    public static void main(String[] args) {
        int[] arr = {13, 8, 1, 5, 2, 5, 9, 7, 6, 12};
        int[] nextGreater = nextGreaterElement(arr);

        System.out.println(Arrays.toString(arr));
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(nextGreater[i] == -1 ? -1 : arr[nextGreater[i]]);
            if (i != arr.length - 1)
                System.out.print(", ");
        }
        System.out.print("]");
    }

    static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] nextGreater = new int[n];
        Arrays.fill(nextGreater, -1);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            // while loop runs until the stack is not empty AND
            // the element represented by stack top is STRICTLY SMALLER than the current element
            // This means, the stack will always be monotonic non increasing (type 4)
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                // pop out the top of the stack, it represents the index of the item
                int stackTop = stack.pop();
                // as given in the condition of the while loop above,
                // nextGreater element of stackTop is the element at index i
                nextGreater[stackTop] = i;
            }
            // push the current element
            stack.push(i);
        }

        return nextGreater;
    }

}
