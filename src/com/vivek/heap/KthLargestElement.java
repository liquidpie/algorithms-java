package com.vivek.heap;

import java.util.PriorityQueue;

public class KthLargestElement {

    static int kthLargest(int[] arr, int k) {
        java.util.PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

        for (int i = 0; i < k; i++) {
            minHeap.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(arr[i]);
            }
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 12, 2, 11};
        int k = 3;
        System.out.println(kthLargest(arr, k));
    }

}
