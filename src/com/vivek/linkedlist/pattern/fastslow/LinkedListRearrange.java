package com.vivek.linkedlist.pattern.fastslow;

import com.vivek.linkedlist.Node;

/**
 * Rearrange a LinkedList (medium)
 *
 * Given the head of a Singly LinkedList, write a method to modify the LinkedList such that the nodes from the second
 * half of the LinkedList are inserted alternately to the nodes from the first half in reverse order.
 *
 * So if the LinkedList has nodes 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null,
 * your method should return 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.
 *
 * Your algorithm should not use any extra space and the input LinkedList should be modified in-place.
 *
 * Example 1:
 * Input: 2 -> 4 -> 6 -> 8 -> 10 -> 12 -> null
 * Output: 2 -> 12 -> 4 -> 10 -> 6 -> 8 -> null
 *
 * Example 2:
 * Input: 2 -> 4 -> 6 -> 8 -> 10 -> null
 * Output: 2 -> 10 -> 4 -> 8 -> 6 -> null
 *
 * Solution #
 * This problem shares similarities with Palindrome LinkedList. To rearrange the given LinkedList we will
 * follow the following steps:
 * 1. We can use the Fast & Slow pointers method similar to Middle of the LinkedList to find the middle node of the LinkedList.
 * 2. Once we have the middle of the LinkedList, we will reverse the second half of the LinkedList.
 * 3. Finally, we’ll iterate through the first half and the reversed second half to produce a LinkedList in the required order.
 *
 * Time Complexity #
 * The above algorithm will have a time complexity of O(N ) where ‘N’ is the number of nodes in the LinkedList.
 *
 * Space Complexity #
 * The algorithm runs in constant space O(1).
 */
public class LinkedListRearrange {

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(6);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(10);
        head.next.next.next.next.next = new Node(12);

        Node.print(head);

        reorder(head);

        Node.print(head);
    }

    static void reorder(Node head) {
        if (head == null || head.next == null)
            return;

        // find the middle node
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node headSecondHalf = reverse(slow);
        Node headFirstHalf = head;

        // rearrange to produce LinkedList in required order
        while (headFirstHalf != null && headSecondHalf != null) {
            Node temp = headFirstHalf.next;
            headFirstHalf.next = headSecondHalf;
            headFirstHalf = temp;

            temp = headSecondHalf.next;
            headSecondHalf.next = headFirstHalf;
            headSecondHalf = temp;
        }

        // set the next of the last node to 'null'
        if (headFirstHalf != null)
            headFirstHalf.next = null;

    }

    static Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

}
