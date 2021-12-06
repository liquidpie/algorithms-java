package com.vivek.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
 *
 * If we iterate through the array one element at a time and keep ‘K’ largest numbers in a heap such that each
 * time we find a larger number than the smallest number in the heap, we do two things:
 * 1. Take out the smallest number from the heap, and
 * 2. Insert the larger number into the heap.
 * This will ensure that we always have ‘K’ largest numbers in the heap.
 *
 * Time complexity:
 * As discussed above, the time complexity of this algorithm is O(K ∗ logK + (N − K) ∗ logK), which is asymptotically equal to O(N ∗ logK)
 *
 * Space complexity:
 * The space complexity will be O(K) since we need to store the top ‘K’ numbers in the heap.
 */
public class TopKNumbers {

    static List<Integer> topKNumbers(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < k; i++) {
            minHeap.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(arr[i]);
            }
        }

        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 12, 2, 11};
        int k = 3;
        System.out.println(topKNumbers(arr, k));
    }

}
