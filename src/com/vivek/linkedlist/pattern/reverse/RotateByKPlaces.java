package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

/**
 * 61. Rotate List
 *
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Example 2:
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 * Reference: https://leetcode.com/problems/rotate-list
 */
public class RotateByKPlaces {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        RotateByKPlaces helper = new RotateByKPlaces();

        Node newHead = helper.rotateRight(head, 2);

        Node.print(newHead);
    }

    public Node rotateRight(Node head, int k) {
        if (head == null)
            return head;

        int n = 0;
        Node node = head;
        while (node != null) {
            n++;
            node = node.next;
        }

        k = (n + k) % n;
        if (k > 0) {
            node = head;
            for (int i = 0; i < n - k - 1; i++) {
                node = node.next;
            }

            Node newHead = node.next;
            node.next = null;
            node = newHead;
            while (k > 1) {
                node = node.next;
                k--;
            }

            node.next = head;
            return newHead;
        }
        return head;
    }

}
