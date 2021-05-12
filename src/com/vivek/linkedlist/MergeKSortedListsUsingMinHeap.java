package com.vivek.linkedlist;

import java.util.PriorityQueue;

/**
 * Merge K sorted linked lists
 *
 * Given K sorted linked lists of size N each, merge them and print the sorted output.
 *
 * Create a min-heap and insert the first element of all the ‘k’ linked lists.
 * As long as the min-heap is not empty, perform the following steps:
 *
 *     Remove the top element of the min-heap (which is the current minimum among all the elements in the min-heap) and add it to the result list.
 *     If there exists an element (in the same linked list) next to the element popped out in previous step, insert it into the min-heap.
 *
 * Complexity Analysis:
 *
 *     Time Complexity: O(N * k * log k), where, ‘N’ is the total number of elements among all the linked lists and ‘k’ is the total number of lists.
 *     Insertion and deletion in a min-heap requires log k time. So the overall time complexity is O(N * log k).
 *     Auxiliary Space: O(k).
 *     The priority queue will have atmost ‘k’ number of elements at any point of time, hence the additional space required for our algorithm is O(k).
 *
 *  Reference: https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/
 *
 */
public class MergeKSortedListsUsingMinHeap {

    static Node mergeKLists(Node[] arr, int k) {
        Node head = null;
        Node last = null;

        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.data - b.data);

        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }

        while (!heap.isEmpty()) {
            Node node = heap.poll();
            if (node.next != null)
                heap.add(node.next);

            if (head == null) {
                head = node;
                last = node;
            } else {
                last.next = node;
                last = node;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        int k = 3;
        Node[] arr = new Node[k];

        arr[0] = new Node(1);
        arr[0].next = new Node(3);
        arr[0].next.next = new Node(5);
        arr[0].next.next.next = new Node(7);

        arr[1] = new Node(2);
        arr[1].next = new Node(4);
        arr[1].next.next = new Node(6);
        arr[1].next.next.next = new Node(8);

        arr[2] = new Node(0);
        arr[2].next = new Node(9);
        arr[2].next.next = new Node(10);
        arr[2].next.next.next = new Node(11);

        // Merge all lists
        Node head = mergeKLists(arr, k);
        Node.print(head);
    }

}
