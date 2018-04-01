package com.vivek.tree;

/**
 * Given a Binary Tree (BT), convert it to a Doubly Linked List(DLL) In-Place.
 * The left and right pointers in nodes are to be used as previous and next pointers respectively
 * in converted DLL. The order of nodes in DLL must be same as Inorder of the given Binary Tree.
 * The first node of Inorder traversal (left most node in BT) must be head node of the DLL.
 *
 * The idea is to do inorder traversal of the binary tree. While doing inorder traversal,
 * keep track of the previously visited node in a variable say prev. For every visited node,
 * make it next of prev and previous of this node as prev.
 *
 * https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
 */
public class ConvertToDoublyLinkedList {

    static Node head; // head to new doubly linked list

    static Node prev;

    static void convert(Node root) {

        if (root == null)
            return;

        convert(root.left);

        if (prev == null) {
            head = root;        // this is the leftmost node
        } else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;            // mark the current node as prev

        convert(root.right);
    }

    static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.right;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(12);
        root.right = new Node(15);
        root.left.left = new Node(25);
        root.left.right = new Node(30);
        root.right.left = new Node(36);

        convert(root);

        printList(head);
    }

}
