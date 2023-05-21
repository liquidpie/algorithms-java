package com.vivek.linkedlist;

/**
 * Remove Nth Node From End of List
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 */
public class RemoveNthNodeFromEnd {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node result = removeNthFromEnd(head, 5);
        Node.print(result);

    }

    static Node removeNthFromEnd(Node head, int n) {
        if (head == null)
            return head;

        Node first = head;
        Node second = head;

        while (n-- > 0 && second != null) {
            second = second.next;
        }

        if (second == null)
            return head.next;

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }

        first.next = first.next.next;
        return head;
    }

}
