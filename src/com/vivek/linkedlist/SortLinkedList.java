package com.vivek.linkedlist;

public class SortLinkedList {

    /**
     * Sort a linked list of 0s, 1s and 2s
     *
     * Examples:
     *
     *     Input: 1 -> 1 -> 2 -> 0 -> 2 -> 0 -> 1 -> NULL
     *     Output: 0 -> 0 -> 1 -> 1 -> 1 -> 2 -> 2 -> NULL
     *     Input: 1 -> 1 -> 2 -> 1 -> 0 -> NULL
     *     Output: 0 -> 1 -> 1 -> 1 -> 2 -> NULL
     */
    static void sortZerosOnesTwos(Node head) {
        int[] count = new int[3];
        Node node = head;

        while (node != null) {
            count[node.data]++;
            node = node.next;
        }

        int i = 0;
        node = head;
        while (node != null) {
            if (count[i] == 0)
                i++;
            node.data = i;
            count[i]--;
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(0);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(0);
        head.next.next.next.next.next.next = new Node(1);

        sortZerosOnesTwos(head);
        Node.print(head);
    }

}
