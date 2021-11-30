package com.vivek.linkedlist;

/**
 * Given only a pointer to a node to be deleted in a singly linked list, how do you delete it?
 *
 * A simple solution is to traverse the linked list until you find the node you want to delete.
 * But this solution requires a pointer to the head node which contradicts the problem statement.
 *
 * The fast solution is to copy the data from the next node to the node to be deleted and delete the next node.
 *
 * Note: It is important to note that this approach will only work if it is guaranteed that the given pointer does not point to the last node.
 * Because if it is the last node, then you don’t have a next node to copy the data from.
 *
 * Reference: https://www.geeksforgeeks.org/in-a-linked-list-given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you-delete-it/
 */
public class DeleteArbitraryNode {

    static void deleteNode(Node node) {
        Node temp = node.next;
        node.data = temp.data;
        node.next = temp.next;
        temp = null;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(12);
        head.next.next = new Node(1);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(1);

        System.out.println("Before Deleting ");
        Node.print(head);

        deleteNode(head);
        System.out.println("");
        System.out.println("After deleting ");
        Node.print(head);
    }

}
