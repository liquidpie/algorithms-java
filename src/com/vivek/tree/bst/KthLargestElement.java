package com.vivek.tree.bst;

import com.vivek.tree.Node;

/**
 * The Kth largest element is Kth last element in inorder traversal
 * and Kth element in reverse inorder traversal.
 * We traverse given Binary Search Tree in reverse inorder and keep track of counts of nodes visited.
 * Once the count becomes K, we print the node.
 */
public class KthLargestElement {

    static int count = 0;

    static void kthLargest(Node node, int k) {
        if (node == null || count >= k)
            return;

        kthLargest(node.right, k);

        count++;

        if (count == k) {
            System.out.println(node.data);
            return;
        }

        kthLargest(node.left, k);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // finds the second largest element in the BST
        KthLargestElement.kthLargest(root, 2);
    }

}
