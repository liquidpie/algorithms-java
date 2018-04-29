package com.vivek.array;

import java.util.Stack;

/**
 * Created by VJaiswal on 08/04/18.
 *
 * Miscellaneous problems on Array
 */
public class ArrayMisc {

    /**
     * The majority element is the element that occurs more than half of the size of the array.
     * @param arr
     * @return majority element. otherwise, -1
     *
     * @link https://stackoverflow.com/questions/4325200/find-the-majority-element-in-array
     */
    int findMajority(int[] arr) {
        int n = arr.length;
        int majority = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (count == 0)
                majority = arr[i];
            if (arr[i] == majority)
                count++;
            else
                count--;
        }
        
        count = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] == majority)
                count++;
        }

        if (count > n / 2)
            return majority;

        return -1;
    }

    /**
     * Given an array, print the Next Greater Element (NGE) for every element.
     * The Next greater Element for an element x is the first greater element on the right side of x in array.
     * Elements for which no greater element exist, consider next greater element as -1.
     */
    void nextGreaterElement(int[] arr) {
        int n = arr.length;

        int i = 0;
        Stack<Integer> s = new Stack();
        int element, next;

        /* push the first element to stack */
        s.push(arr[0]);

        // iterate for rest of the elements
        for (i = 1; i < n; i++) {
            next = arr[i];

            if (s.isEmpty() == false) {

                // if stack is not empty, then
                // pop an element from stack
                element = s.pop();

                /* If the popped element is smaller than
                   next, then a) print the pair b) keep
                   popping while elements are smaller and
                   stack is not empty */
                while (element < next) {
                    System.out.println(element + " --> " + next);
                    if (s.isEmpty() == true)
                        break;
                    element = s.pop();
                }

                /* If element is greater than next, then
                   push the element back */
                if (element > next)
                    s.push(element);
            }

            /* push next to stack so that we can find next
               greater for it */
            s.push(next);
        }

        /* After iterating over the loop, the remaining
           elements in stack do not have the next greater
           element, so print -1 for them */
        while (s.isEmpty() == false) {
            element = s.pop();
            next = -1;
            System.out.println(element + " -- " + next);
        }
    }

}
