package com.vivek.tree.bst;

import com.vivek.tree.Node;

/**
 * Kth Smallest Element in a BST
 *
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 */
public class KthSmallestElement {

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(2);
        root.left.right = new Node(5);
        root.left.left.left = new Node(1);
        root.right = new Node(15);
        root.right.left = new Node(13);
        root.right.right = new Node(22);
        root.right.left.right = new Node(14);

        System.out.println(kthSmallest(root, 3));
    }

    static int kthSmallest(Node root, int k) {
        if (root == null)
            return 0;

        int leftCount = count(root.left);

        if (k <= leftCount) {
            return kthSmallest(root.left, k);
        } else if (k == leftCount + 1) {
            return root.data;
        } else {
            return kthSmallest(root.right, k - leftCount - 1);
        }
    }

    private static int count(Node root) {
        if (root == null)
            return 0;

        return count(root.left) + count(root.right) + 1;
    }

}
