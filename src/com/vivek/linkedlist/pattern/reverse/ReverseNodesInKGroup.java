package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

/**
 * 25. Reverse Nodes in k-Group
 *
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Example 2:
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Solution:
 * The problem is about reversing nodes in chunks of k while maintaining the order of leftover nodes.
 * First, think of reversing a small part of the list, then recursively handle the rest.
 *
 * Approach
 *
 * 1️⃣ Count if there are at least k nodes.
 * 2️⃣ Reverse the first k nodes.
 * 3️⃣ Recursively call for the rest and connect.
 *
 * Complexity
 *
 *     Time complexity: O(n)
 *     Space complexity: O(n/k) (recursion stack)
 *
 * Reference:
 * https://leetcode.com/problems/reverse-nodes-in-k-group
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);
        
        ReverseNodesInKGroup helper = new ReverseNodesInKGroup();
        Node result = helper.reverseKGroup(head, 3);

        System.out.println("Nodes of the reversed LinkedList are:");
        Node.print(result);
    }
    

    public Node reverseKGroup(Node head, int k) {
        int groupCount = 0;
        Node node = head;

        while (node != null && groupCount < k) {
            node = node.next;
            groupCount++;
        }

        if (groupCount < k)
            return head;

        Node newHead = reverse(head, node);
        head.next = reverseKGroup(node, k);

        return newHead;
    }

    private Node reverse(Node start, Node end) {
        Node prev = null;
        Node current = start;

        while (current != end) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

}
