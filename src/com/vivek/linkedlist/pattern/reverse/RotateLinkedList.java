package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

/**
 * Rotate a LinkedList (medium) #
 *
 * Given the head of a Singly LinkedList and a number ‘k’, rotate the LinkedList to the right by ‘k’ nodes.
 *
 * Example 1: k = 3
 * Original List -
 *  head -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 * Rotated List -
 *  head -> 4 -> 5 -> 6 -> 1 -> 2 -> 3 -> null
 *
 * Example 2: k = 8
 * Original List -
 *  head -> 1 -> 2 -> 3 -> 4 -> 5 -> null
 * Rotated List -
 *  head -> 3 -> 4 -> 5 -> 1 -> 2 -> null
 *
 * Solution #
 * Another way of defining the rotation is to take the sub-list of ‘k’ ending nodes of the LinkedList and connect
 * them to the beginning. Other than that we have to do three more things:
 * 1. Connect the last node of the LinkedList to the head, because the list will have a different tail after the rotation.
 * 2. The new head of the LinkedList will be the node at the beginning of the sublist.
 * 3. The node right before the start of sub-list will be the new tail of the rotated LinkedList.
 *
 * Time complexity #
 * The time complexity of our algorithm will be O(N ) where ‘N’ is the total number of nodes in the LinkedList.
 *
 * Space complexity #
 * We only used constant space, therefore, the space complexity of our algorithm is O(1).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Reversal of Linked List
 */
public class RotateLinkedList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        Node result = rotate(head, 8);
        System.out.println("Nodes of the rotated LinkedList are:");
        Node.print(result);
    }

    static Node rotate(Node head, int rotation) {
        if (head == null || head.next == null || rotation <= 0)
            return head;

        // find the length and the last node of the list
        int length = 1;
        Node lastNode = head;
        while (lastNode.next != null) {
            length++;
            lastNode = lastNode.next;
        }

        lastNode.next = head;  // connect the last node with the head to make it a circular list
        rotation %= length;  // no need to do rotations more than the length of the list
        int skipLength = length - rotation;

        Node lastNodeOfRotatedList = head;
        for (int i = 0; i < skipLength - 1; i++) {
            lastNodeOfRotatedList = lastNodeOfRotatedList.next;
        }
        // 'lastNodeOfRotatedList.next' is pointing to the sub-list of 'k' ending nodes
        head = lastNodeOfRotatedList.next;
        lastNodeOfRotatedList.next = null;
        return head;
    }

}
