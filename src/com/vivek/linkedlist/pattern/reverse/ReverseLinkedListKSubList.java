package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

/**
 * Reverse every K-element Sub-list
 *
 * Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head.
 * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 *
 * Solution #
 * The problem follows the In-place Reversal of a LinkedList pattern and is quite similar to Reverse a Sub-list.
 * The only difference is that we have to reverse all the sub-lists.
 * We can use the same approach, starting with the first sub-list (i.e. p=1, q=k )
 * and keep reversing all the sublists of size ‘k’.
 *
 * Time complexity #
 * The time complexity of our algorithm will be O(N ) where ‘N’ is the total number of nodes in the LinkedList.
 *
 * Space complexity #
 * We only used constant space, therefore, the space complexity of our algorithm is O(1).
 *
 * Reference:
 * 1. https://practice.geeksforgeeks.org/problems/reverse-a-linked-list-in-groups-of-given-size/1
 * 2. Grokking the Coding Interview
 *    Pattern: Reversal of Linked List
 */
public class ReverseLinkedListKSubList {

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
        Node result = reverse(head, 3);
        System.out.println("Nodes of the reversed LinkedList are:");
        Node.print(result);
    }

    static Node reverse(Node head, int k) {
        if (k <= 1 || head == null)
            return head;

        Node current = head;
        Node prev = null;
        while (true) {
            Node lastNodeOfPreviousPart = prev;
            Node lastNodeOfSubList = current;

            Node next = null;
            for (int i = 0; current != null && i < k; i++) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            // connect with the previous part
            if (lastNodeOfPreviousPart != null) {
                lastNodeOfPreviousPart.next = prev;  // 'previous' is now the first node of the sub-list
            } else {  // this means we are changing the first node (head) of the LinkedList
                head = prev;
            }

            lastNodeOfSubList.next = current;

            if (current == null) // break, if we've reached the end of the LinkedList
                break;
            // prepare for the next sublist
            prev = lastNodeOfSubList;
        }
        return head;
    }

}
