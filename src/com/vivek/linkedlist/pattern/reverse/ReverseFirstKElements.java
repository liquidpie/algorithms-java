package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

/**
 * Reverse the first ‘k’ elements of a given LinkedList.
 *
 * Solution:
 * This problem can be easily converted to ReverseSubList problem;
 * to reverse the first ‘k’ nodes of the list, we need to pass p=1 and q=k .
 */
public class ReverseFirstKElements {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        Node result = ReverseSubList.reverse(head, 1, 3);
        System.out.println("Nodes of the reversed LinkedList are:");
        Node.print(result);
    }

}
