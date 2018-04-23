package com.vivek.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Remove duplicates from a LinkedList
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
