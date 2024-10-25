package com.vivek.linkedlist.pattern.fastslow;

import com.vivek.linkedlist.Node;

/**
 * Given the head of a LinkedList with a cycle, find the length of the cycle.
 *
 * Solution: We can use the above solution to find the cycle in the LinkedList. Once the fast and slow pointers meet,
 * we can save the slow pointer and iterate the whole cycle with another pointer until we see the slow pointer again to find the length of the cycle.
 *
 * Time and Space Complexity: The above algorithm runs in O(N ) time complexity and O(1) space complexity.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Fast Slow Pointer
 */
public class LinkedListCycleLength {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node;
        node = new Node(2, head);
        node = new Node(3, node);
        node = new Node(4, node);
        node = new Node(5, node);
        Node node1 = new Node(6, node);
        node1.next = node;
    }

    static int findCycleLength(Node node) {
        Node slow = node;
        Node fast = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                return calculateLength(slow);
        }
        return -1;
    }

    static int calculateLength(Node slow) {
        Node current = slow;
        int len = 0;
        do {
            current = current.next;
            len++;
        } while (current != slow);
        return len;
    }

}
