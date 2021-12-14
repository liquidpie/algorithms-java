package com.vivek.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * We have given heads of two Linked Lists, and we want to find if there's a common node between the two linked lists.
 * The Linked Lists may or may not contain cycle.
 *
 * Approach:
 * The problem is similar to LinkedListsIntersection but has an additional check for cycle
 */
public class LinkedListsOverlap {

    static boolean doesOverlap(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return false;
        }

        int count1 = 0;
        int count2 = 0;

        Set<Node> visited = new HashSet<>();
        Node node = head1;
        while (node != null && !visited.contains(node)) {
            count1++;
            visited.add(node);
            node = node.next;
        }

        visited.clear();
        node = head2;
        while (node != null && !visited.contains(node)) {
            count2++;
            visited.add(node);
            node = node.next;
        }

        int diff = Math.abs(count1 - count2);
        Node l1 = count1 > count2 ? head1 : head2;
        Node l2 = l1 == head1 ? head2 : head1;

        while (diff > 0) {
            l1 = l1.next;
            diff--;
        }

        while (l1 != null && l2 != null) {
            if (l1 == l2) {
                return true;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        return false;
    }

    public static void main(String[] args) {
        Node head1 = new Node(0);
        head1.next = new Node(1);
        head1.next.next = new Node(2);
        head1.next.next.next = new Node(3);

        Node head2 = new Node(5);
        head2.next = new Node(6);
        head2.next.next = new Node(7);
        head2.next.next.next = new Node(8);
        head2.next.next.next.next = new Node(9);
        head2.next.next.next.next.next = head1.next;

        System.out.println(doesOverlap(head1, head2));
    }

}
