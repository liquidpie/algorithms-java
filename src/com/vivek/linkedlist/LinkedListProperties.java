package com.vivek.linkedlist;

public class LinkedListProperties {

    /**
     * n’th node from the end of a Linked List
     *
     * Given a Linked List and a number n, write a function that returns the value at the n’th node from the end of the Linked List.
     * One method is to use length of linked list and print the (len – n + 1)th node from the beginning of the Linked List.
     *
     * Here we're discussing Double Pointer method:
     * Maintain two pointers – reference pointer and main pointer. Initialize both reference and main pointers to head.
     * First, move the reference pointer to n nodes from head. Now move both pointers one by one until the reference pointer reaches the end.
     * Now the main pointer will point to nth node from the end. Return the main pointer.
     */
    static Node nthNodeFromLast(Node head, int n) {
        if (head == null)
            return null;

        Node ref = head;
        Node result = head;

        while (ref != null && n > 0) {
            ref = ref.next;
            n--;
        }

        if (ref == null)
            return null;

        while (ref != null) {
            ref = ref.next;
            result = result.next;
        }

        return result;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);

        System.out.println(nthNodeFromLast(head, 4));
    }

}
