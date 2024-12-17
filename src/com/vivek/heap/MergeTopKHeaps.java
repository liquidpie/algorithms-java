package com.vivek.heap;

import java.util.*;
import java.util.PriorityQueue;

/**
 * System design problem that computes Top K of a large volume of data where data is partitioned to multiple partitions
 * and each partition calculates its individual top K using heap and lastly these top K are merged to get the final
 * Top K
 */
public class MergeTopKHeaps {

    public PriorityQueue<Integer> merge(List<PriorityQueue<Integer>> maxHeaps, int k) {
        PriorityQueue<Integer> resultMinHeap = new PriorityQueue<>();

        for (PriorityQueue<Integer> heap : maxHeaps) {
            while (!heap.isEmpty()) {
                resultMinHeap.offer(heap.poll());
                if (resultMinHeap.size() > k) {
                    resultMinHeap.poll();
                }
            }
        }

        return resultMinHeap;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> heap1 = new PriorityQueue<>(Comparator.reverseOrder());
        heap1.addAll(Arrays.asList(20, 15, 10, 8, 5));

        PriorityQueue<Integer> heap2 = new PriorityQueue<>(Comparator.reverseOrder());
        heap2.addAll(Arrays.asList(25, 18, 12, 9, 6));

        PriorityQueue<Integer> heap3 = new PriorityQueue<>(Comparator.reverseOrder());
        heap3.addAll(Arrays.asList(22, 17, 14, 11, 7));

        List<PriorityQueue<Integer>> heaps = Arrays.asList(heap1, heap2, heap3);

        MergeTopKHeaps mergeTopKHeaps = new MergeTopKHeaps();
        PriorityQueue<Integer> topK = mergeTopKHeaps.merge(heaps, 5);

        System.out.println(topK);

    }

}
