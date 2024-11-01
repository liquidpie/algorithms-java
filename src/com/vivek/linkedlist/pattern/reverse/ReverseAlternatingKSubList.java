package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

/**
 * Reverse alternating K-element Sub-list (medium)
 *
 * Given the head of a LinkedList and a number ‘k’, reverse every alternating ‘k’ sized sub-list starting from
 * the head.
 * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 *
 * Solution #
 * The problem follows the In-place Reversal of a LinkedList pattern and is quite similar to Reverse every K- element Sub-list.
 * The only difference is that we have to skip ‘k’ alternating elements.
 * We can follow a similar approach, and in each iteration after reversing ‘k’ elements,
 * we will skip the next ‘k’ elements.
 *
 * Time complexity #
 * The time complexity of our algorithm will be O(N ) where ‘N’ is the total number of nodes in the LinkedList.
 *
 * Space complexity #
 * We only used constant space, therefore, the space complexity of our algorithm is O(1).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Reversal of Linked List
 */
public class ReverseAlternatingKSubList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);
        head.next.next.next.next.next.next.next.next = new Node(9);
        Node result = reverse(head, 2);
        System.out.println("Nodes of the reversed LinkedList are:");
        Node.print(result);
    }

    static Node reverse(Node head, int k) {
        if (k <= 1 || head == null)
            return head;

        Node current = head;
        Node prev = null;
        while (true) {
            Node lastNodeOfPrevPart = prev;
            Node lastNodeOfSubList = current;
            Node next = null;
            for (int i = 0; current != null && i < k; i++) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            if (lastNodeOfPrevPart != null) {
                lastNodeOfPrevPart.next = prev;
            } else {
                head = prev;
            }

            lastNodeOfSubList.next = current;

            // skip 'k' nodes
            for (int i = 0; current != null && i < k; i++) {
                prev = current;
                current =  current.next;
            }
            if (current == null)
                break;
        }
        return head;
    }

}
