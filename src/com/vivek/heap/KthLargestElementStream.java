package com.vivek.heap;

import java.util.PriorityQueue;

/**
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Implement KthLargest class:
 *
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 *
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 * Time complexity:
 * The time complexity of the add() function will be O(logK) since we are inserting the new number in the
 * heap.
 * Space complexity:
 * The space complexity will be O(K) for storing numbers in the heap.
 *
 * https://leetcode.com/problems/kth-largest-element-in-a-stream
 */
public class KthLargestElementStream {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
    int k;

    public KthLargestElementStream(int[] arr, int k) {
        this.k = k;
        for (int num : arr) {
            add(num);
        }
    }

    int add(int num) {
        minHeap.add(num);

        if (minHeap.size() > k) {
            minHeap.poll();
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 12, 2, 11};
        int k = 3;

        KthLargestElementStream obj = new KthLargestElementStream(arr, k);

        System.out.println(obj.add(9));
        System.out.println(obj.add(4));
    }

}
