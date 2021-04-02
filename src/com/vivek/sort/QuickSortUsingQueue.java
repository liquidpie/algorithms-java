package com.vivek.sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QuickSortUsingQueue {

    static void quickSort(Queue<Integer> queue) {
        if (queue == null)
            return;
        int n = queue.size();
        if (n < 2)
            return;

        // divide
        int pivot = queue.peek();

        Queue<Integer> lq = new LinkedList<>();
        Queue<Integer> eq = new LinkedList<>();
        Queue<Integer> rq = new LinkedList<>();

        while (!queue.isEmpty()) {

            int element = queue.poll();

            if (element < pivot) {
                lq.add(element);
            } else if (element == pivot) {
                eq.add(element);
            } else {
                rq.add(element);
            }
        }

        // conquer
        quickSort(lq);
        quickSort(rq);

        // combine
        while (!lq.isEmpty()) {
            queue.add(lq.poll());
        }
        while (!eq.isEmpty()) {
            queue.add(eq.poll());
        }
        while (!rq.isEmpty()) {
            queue.add(rq.poll());
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>(List.of(4, 3, 8, 7, 12, 10, 9, 1, 2));

        System.out.println(queue);

        quickSort(queue);

        System.out.println(queue);
    }

}
