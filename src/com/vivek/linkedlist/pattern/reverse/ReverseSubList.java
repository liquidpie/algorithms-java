package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

/**
 * Reverse a Sub-list (medium)
 *
 * Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from position ‘p’ to ‘q’.
 *
 * Solution #
 * The problem follows the In-place Reversal of a LinkedList pattern. We can use a similar approach as
 * discussed in Reverse a LinkedList. Here are the steps we need to follow:
 * 1. Skip the first p-1 nodes, to reach the node at position p .
 * 2. Remember the node at position p-1 to be used later to connect with the reversed sub-list.
 * 3. Next, reverse the nodes from p to q using the same approach discussed in Reverse a LinkedList.
 * 4. Connect the p-1 and q+1 nodes to the reversed sub-list.
 *
 * Time complexity #
 * The time complexity of our algorithm will be O(N ) where ‘N’ is the total number of nodes in the LinkedList.
 *
 * Space complexity #
 * We only used constant space, therefore, the space complexity of our algorithm is O(1).
 *
 * Reference:
 * Grokking the Coding Interview
 * Reversal of LinkedList
 */
public class ReverseSubList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        Node result = reverse(head, 2, 4);
        System.out.println("Nodes of the reversed LinkedList are:");
        Node.print(result);
    }

    static Node reverse(Node head, int p, int q) {
        if (p == q)
            return head;

        Node current = head;
        Node prev = null;
        // after skipping 'p-1' nodes, current will point to 'p'th node
        for (int i = 0; current != null && i < p - 1; i++) {
            prev = current;
            current = current.next;
        }

        // we are interested in three parts of the LinkedList, part before index 'p', part between 'p' and
        // 'q', and the part after index 'q'
        Node lastNodeOfFirstPart = prev;    // points to the node at index 'p-1'
        // after reversing the LinkedList 'current' will become the last node of the sub-list
        Node lastNodeOfSubList = current;
        Node next = null;   // will be used to temporarily store the next node
        for (int i = 0; current != null && i < q - p + 1; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // connect with the first part
        if (lastNodeOfFirstPart != null) {
            lastNodeOfFirstPart.next = prev; // prev is now the first part of the sub list
        } else {
            head = prev; // this means p == 1; we're changing the first node (head) of the LinkedList
        }

        // connect with the last part
        lastNodeOfSubList.next = current;

        return head;
    }

}
