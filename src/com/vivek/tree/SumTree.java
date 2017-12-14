package com.vivek.tree;

/**
 * Convert a given tree to its Sum Tree
 *
 * Given a Binary Tree where each node has positive and negative values.
 * Convert this to a tree where each node contains the sum of the left and right
 * sub trees in the original tree. The values of leaf nodes are changed to 0.
 */
public class SumTree {

    int convertToSumTree(Node node) {
        if (node == null)
            return 0;

        int oldValue = node.data;

        int left = convertToSumTree(node.left);
        int right = convertToSumTree(node.right);

        node.data = left + right;

        return node.data + oldValue;
    }


    public static void main(String[] args) {
        SumTree solution = new SumTree();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        solution.convertToSumTree(root);

        Traversal traversal = new Traversal();
        traversal.inOrder(root);
    }

}
