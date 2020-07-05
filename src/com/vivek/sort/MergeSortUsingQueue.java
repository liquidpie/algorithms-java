package com.vivek.sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MergeSortUsingQueue {

    static void mergeSort(Queue<Integer> queue) {
        if (queue == null)
            return;
        int n = queue.size();
        if (n < 2)
            return;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        while(q1.size() < n / 2) {
            q1.add(queue.remove());
        }

        while(!queue.isEmpty()) {
            q2.add(queue.remove());
        }

        mergeSort(q1);
        mergeSort(q2);

        merge(q1, q2, queue);
    }

    private static void merge(Queue<Integer> q1, Queue<Integer> q2, Queue<Integer> queue) {
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.element() < q2.element()) {
                queue.add(q1.remove());
            } else {
                queue.add(q2.remove());
            }
        }

        while (!q1.isEmpty()) {
            queue.add(q1.remove());
        }

        while (!q2.isEmpty()) {
            queue.add(q2.remove());
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(List.of(4, 3, 8, 7, 12, 10, 9, 1, 2));

        System.out.println(queue);

        mergeSort(queue);

        System.out.println(queue);
    }

}
