package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

/**
 * Given a LinkedList with ‘n’ nodes, reverse it based on its size in the following way:
 *
 * 1. If ‘n’ is even, reverse the list in a group of n/2 nodes.
 * 2. If n is odd, keep the middle node as it is, reverse the first ‘n/2’ nodes and reverse the last ‘n/2’ nodes.
 *
 * Solution: When ‘n’ is even we can perform the following steps:
 * 1. Reverse first ‘n/2’ nodes: head = reverse(head, 1, n/2)
 * 2. Reverse last ‘n/2’ nodes: head = reverse(head, n/2 + 1, n)
 *
 * When ‘n’ is odd, our algorithm will look like:
 * 1. head = reverse(head, 1, n/2)
 * 2. head = reverse(head, n/2 + 2, n)
 *
 * Please note the function call in the second step. We’re skipping two elements as we will be skipping the middle element.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Reversal of Linked List
 */
public class ReverseLinkedListHalves {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        System.out.println("Nodes of the reversed LinkedList are:");
        Node.print(reverse(head));
    }

    static Node reverse(Node head) {
        Node node = head;
        int n = 0;
        while (node != null) {
            n++;
            node = node.next;
        }

        if (n % 2 == 0) {
            head = ReverseSubList.reverse(head, 1, n / 2);
            head = ReverseSubList.reverse(head, n / 2 + 1, n);
        } else {
            head = ReverseSubList.reverse(head, 1, n / 2);
            head = ReverseSubList.reverse(head, n / 2 + 2, n);
        }
        return head;
    }

}
