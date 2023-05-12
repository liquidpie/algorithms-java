package com.vivek.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Find first node of loop in a linked list
 *
 * Write a function findFirstLoopNode() that checks whether a given Linked List contains a loop.
 * If the loop is present then it returns point to the first node of the loop. Else it returns NULL.
 *
 * Example:
 *
 * 1 -> 2 -> 3
 *      ^    |
 *      5 <- 4
 *
 * Output: 2
 *
 * Solution:
 * We have discussed Floyd’s loop detection algorithm. Below are steps to find the first node of the loop.
 * 1. If a loop is found, initialize a slow pointer to head, let fast pointer be at its position.
 * 2. Move both slow and fast pointers one node at a time.
 * 3. The point at which they meet is the start of the loop.
 *
 * https://www.geeksforgeeks.org/find-first-node-of-loop-in-a-linked-list/
 */
public class FirstLoopNode {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node;
        node = new Node(2, head);
        node = new Node(3, node);
        node = new Node(4, node);
        node = new Node(5, node);
        Node node1 = new Node(6, node);
        node1.next = node;

        System.out.println(findFirstLoopNode(head));
    }

    /**
     * How does this approach work?
     * Let slow and fast meet at some point after Floyd’s Cycle finding algorithm. The below diagram shows the situation when the cycle is found.
     *
     * We can conclude below from the above diagram
     *
     * Distance traveled by fast pointer = 2 * (Distance traveled
     *                                          by slow pointer)
     *
     * (m + n*x + k) = 2*(m + n*y + k)
     *
     * Note that before meeting the point shown above, fast
     * was moving at twice speed.
     *
     * x -->  Number of complete cyclic rounds made by
     *        fast pointer before they meet first time
     *
     * y -->  Number of complete cyclic rounds made by
     *        slow pointer before they meet first time
     *
     * From the above equation, we can conclude below
     *
     *     m + k = (x-2y)*n
     *
     * Which means m+k is a multiple of n.
     *
     * So if we start moving both pointers again at the same speed such that one pointer (say slow) begins from
     * the head node of the linked list and other pointers (say fast) begins from the meeting point.
     * When the slow pointer reaches the beginning of the loop (has made m steps), the fast pointer would have made
     * also moved m steps as they are now moving the same pace. Since m+k is a multiple of n and fast starts from k,
     * they would meet at the beginning. Can they meet before also?
     * No, because the slow pointer enters the cycle first time after m steps.
     *
     * Time Complexity: O(N), where N is length of array.
     * Auxiliary Space: O(1)
     */
    static Node findFirstLoopNode(Node head) {
        if (head ==  null || head.next ==  null) {
            return null;
        }
        Node slow = head, fast = head;
        slow = slow.next;
        fast = fast.next.next;

        // search for loop using slow & fast pointers
        while (fast != null && fast.next != null) {
            if (slow == fast)
                break;
            slow = slow.next;
            fast = fast.next.next;
        }

        // if loop doesn't exist, return
        if (slow != fast)
            return null;

        // if loop exists, start slow fom head & fast from meeting point
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * Time Complexity: O(NlogN)
     * Auxiliary Space: O(N)
     */
    static Node findFirstLoopNodeUsingHashing(Node head) {
        if (head == null)
            return null;

        Set<Node> set = new HashSet<>();
        Node ptr = head;

        while (ptr != null) {
            if (set.contains(ptr))
                return ptr;

            set.add(ptr);
            ptr = ptr.next;
        }

        return null;
    }

}
