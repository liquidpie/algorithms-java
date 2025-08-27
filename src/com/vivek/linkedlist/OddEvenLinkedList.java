package com.vivek.linkedlist;

/**
 * 328. Odd Even Linked List
 *
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 *
 * Example 2:
 *
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 *
 * Reference: https://leetcode.com/problems/odd-even-linked-list
 */
public class OddEvenLinkedList {

    public Node oddEvenList(Node head) {
        if (head == null || head.next == null)
            return head;

        Node oddTail = head;
        Node evenHead = head.next;
        Node evenTail = evenHead;
        Node node = evenHead.next;
        int i = 3;
        while (node != null) {
            if (i % 2 == 0) {
                evenTail.next = node;
                evenTail = evenTail.next;
            } else {
                oddTail.next = node;
                oddTail = oddTail.next;
            }
            node = node.next;
            i++;
        }

        oddTail.next = evenHead;
        evenTail.next = null;

        return head;
    }

}
