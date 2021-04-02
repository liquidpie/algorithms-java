package com.vivek.sort;

import com.vivek.heap.PriorityQueue;

import java.util.Arrays;

public class HeapSortUsingExtraSpace {

    static void priorityQueueSort(int[] arr) {
        PriorityQueue pq = new PriorityQueue();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }

        for (int i = 0; i < n; i++) {
            arr[i] = pq.removeMin();
        }

    }

    public static void main(String[] args) {
        int[] arr = {3, 7, 1, 0, 8, 4};
        System.out.println(Arrays.toString(arr));
        priorityQueueSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
