package com.vivek.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Remove duplicates from a LinkedList or Sorted Linked List (Remove all occurrences of duplicates from a sorted Linked List)
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 *
 * Example 1:
 *
 * Input: head = [1,1,2]
 * Output: [1,2]
 * Example 2:
 *
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list
 */
public class RemoveDupes {

    static void usingBuffer(Node head) {
        Set<Integer> lookup = new HashSet<>();
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (lookup.contains(current.data)) {
                previous.next = current.next;
            } else {
                lookup.add(current.data);
                previous = current;
            }
            current = current.next;
        }

    }

    /**
     Without a buffer, we can iterate with two pointers: “current” does a normal iteration,
     while “runner” iterates through all prior nodes to check for dups. Runner will only see one dup per node,
     because if there were multiple duplicates they would have been removed already.
     */
    static void usingTwoPointers(Node head) {
        if (head == null) return;
        Node previous = head;
        Node current = previous.next;
        while (current != null) {
            Node runner = head;
            while (runner != current) {
                if (runner.data == current.data) {
                    previous.next = current.next;
                    current = current.next;
                    break;
                }
                runner = runner.next;
            }
            if (runner == current) {    // current not updated  - update now
                previous = current;
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);

//        usingBuffer(head);
        usingTwoPointers(head);
        Node.print(head);
    }

}
