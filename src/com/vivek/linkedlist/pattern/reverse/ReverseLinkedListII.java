package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

/**
 * 92. Reverse Linked List II
 *
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 * Solution: Same as ReverseSubList
 *
 * Reference:
 * https://leetcode.com/problems/reverse-linked-list-ii
 */
public class ReverseLinkedListII {

    public static void main(String[] args) {
        ReverseLinkedListII helper = new ReverseLinkedListII();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node result = helper.reverseBetween(head, 2, 4);
        System.out.println("Nodes of the reversed LinkedList are:");
        Node.print(result);
    }

    public Node reverseBetween(Node head, int left, int right) {
        if (left == right)
            return head;

        Node current = head;
        Node prev = null;
        // after skipping 'left-1' nodes, current will point to 'left'th node
        for (int i = 0; current != null && i < left - 1; i++) {
            prev = current;
            current = current.next;
        }

        // we are interested in three parts of the LinkedList, part before index 'left', part between 'left' and
        // 'right', and the part after index 'right'
        Node lastNodeOfFirstPart = prev;    // points to the node at index 'left-1'
        // after reversing the LinkedList 'current' will become the last node of the sub-list
        Node lastNodeOfSubList = current;
        Node next = null;   // will be used to temporarily store the next node

        for (int i = 0; current != null && i < right - left + 1; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // connect with the first part
        if (lastNodeOfFirstPart != null) {
            lastNodeOfFirstPart.next = prev;  // prev is now the first part of the sub list
        } else {
            head = prev;  // this means left == 1; we're changing the first node (head) of the LinkedList
        }

        // connect with the last part
        lastNodeOfSubList.next = current;

        return head;
    }

}
