package com.vivek.deque;

import com.vivek.tree.bst.AVLTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array and an integer K, find the maximum for each and every contiguous subarray of size k.
 *
 * https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
 */
public class SlidingWindowMaximum {

    /**
     Create a Deque, Qi of capacity k, that stores only useful elements of current window of k elements.
     An element is useful if it is in current window and is greater than all other elements on right side of it in current window.
     Process all array elements one by one and maintain Qi to contain useful elements of current window
     and these useful elements are maintained in sorted order.
     The element at front of the Qi is the largest and element at rear/back of Qi is the smallest of current window.
     */
    static void dequeBased(int[] arr, int k) {

        // Create a Double Ended Queue, Qi that will store indexes of array elements. The queue will store indexes of
        // useful elements in every window and it will maintain decreasing order of values
        // from front to rear in Qi, i.e., arr[Qi.front[]] to arr[Qi.rear()] are sorted in decreasing order
        Deque<Integer> deque = new LinkedList<>();

        int i;
        for (i = 0; i < k; i++) {

            // For every element, the previous smaller elements are useless so remove them from Qi
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.removeLast();
            }
            // Add new element at rear of queue
            deque.addLast(i);
        }

        // Process rest of the elements, i.e., from arr[k] to arr[n-1]
        for (; i < arr.length; ++i) {
            // The element at the front of the queue is the largest element of previous window, so print it
            System.out.println(arr[deque.peek()]);

            // Remove the elements which are out of this window
            while (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.removeFirst();
            }

            // Remove all elements smaller than the currently being added element (remove useless elements)
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.removeLast();
            }

            // Add current element at the rear of Qi
            deque.addLast(i);
        }

        // Print the maximum element of last window
        System.out.println(arr[deque.peek()]);

    }

    /**
     - Pick first k elements and create a max heap of size k.
     - Perform heapify and print the root element.
     - Store the next and last element from the array
     - Run a loop from k – 1 to n
        - Replace the value of element which is got out of the window with new element which came inside the window.
        - Perform heapify.
        - Print the root of the Heap.
     */
    static void maxHeapBased(int[] arr, int k) {
        // ToDo
    }

    /**
     The self-balancing BST like Red-Black Tree, AVL tree, etc. adjust their height automatically for every insertion and deletion, taking O(log N) time on an average.

     The idea is to make a BST of size K and at each sliding, we will insert the next element and delete the least-recent added element in O(log N).

     Solution steps:

     Create a self-balancing BST for the first K elements.
     Get the maximum element from the BST and store it in the solution array.
     Move the window (having K elements) forward. This step needs “dropping” the first element (arr[i]) in the window and “adding” the element after the last element (arr[i+k]) to the window.
     Iterate through the whole array from 0 to N-K and repeat the above steps.
     Return the solution array.
     */
    static void selfBalancingBstBased(int[] arr, int k) {

        // self-balancing tree
        var tree = new AVLTree();
        AVLTree.Node root = null;
        // size of subarray
        int c = 0;
        // last window's first element index
        int l = 0;

        // insert first k elements
        for (int i = 0; i < arr.length; i++) {
            root  = tree.insert(root, arr[i]);
            c++;

            // size of subarray greater than k
            if (c > k) {
                root = tree.delete(root, arr[l++]);
                c--;
            }

            // size of subarray equal to k, print max
            if (c == k) {
                System.out.println(tree.maxValue(root));
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = { 12, 1, 78, 90, 57, 89, 56 };
        int k = 3;

        dequeBased(arr, k);
        selfBalancingBstBased(arr, k);
    }

}
