package com.vivek.linkedlist.pattern.fastslow;

import com.vivek.linkedlist.Node;

/**
 * Middle of the Linked List
 *
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 *
 * Example 2:
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 * Reference:
 * https://leetcode.com/problems/middle-of-the-linked-list/
 *
 * Grokking the Coding Interview
 * Pattern: Fast Slow
 */
public class MiddleNode {

    public static void main(String[] args) {

    }

    static Node middleNode(Node head) {
        if (head == null)
            return null;
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

}
