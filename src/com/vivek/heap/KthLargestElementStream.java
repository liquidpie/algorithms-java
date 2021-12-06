package com.vivek.heap;

import java.util.PriorityQueue;

/**
 * Time complexity:
 * The time complexity of the add() function will be O(logK) since we are inserting the new number in the
 * heap.
 * Space complexity:
 * The space complexity will be O(K) for storing numbers in the heap.
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
