package com.vivek.heap;

import java.util.PriorityQueue;

/**
 * Given an unsorted array of numbers, find Kth smallest number in it.
 *
 * Time complexity:
 * The time complexity of this algorithm is O(K ∗ logK + (N − K) ∗ logK), which is asymptotically equal to
 * O(N ∗ logK)
 * Space complexity:
 * The space complexity will be O(K) because we need to store ‘K’ smallest numbers in the heap.
 */
public class KthSmallestElement {

    static int kthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }

        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 12, 2, 11};
        int k = 3;
        System.out.println(kthSmallest(arr, k));
    }

}
