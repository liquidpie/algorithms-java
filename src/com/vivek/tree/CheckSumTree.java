package com.vivek.tree;

/**
 * Check if a given Binary Tree is SumTree
 *
 * Write a function that returns true if the given Binary Tree is SumTree else false.
 * A SumTree is a Binary Tree where the value of a node is equal to the sum of the nodes present in its left subtree and right subtree.
 * An empty tree is SumTree and the sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.
 *
 *           26
 *         /   \
 *       10     3
 *     /    \     \
 *   4      6      3
 *
 *  Method:
 *
 * Use the following rules to get the sum directly:
 * 1) If the node is a leaf node then the sum of the subtree rooted with this node is equal to the value of this node.
 * 2) If the node is not a leaf node then the sum of the subtree rooted with this node is twice the value of this node (Assuming that the tree rooted with this node is SumTree).
 *
 * Reference: https://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree
 */
public class CheckSumTree {

    /**
     * Time Complexity: O(n)
     */
    static boolean isSumTree(Node node) {
        if (node == null || isLeaf(node)) {
            return true;
        }
        int leftSum, rightSum;

        if (isSumTree(node.left) && isSumTree(node.right)) {
            // Get the sum of nodes in left subtree
            if (node.left == null)
                leftSum = 0;
            else if (isLeaf(node.left))
                leftSum = node.left.data;
            else
                leftSum = 2 * node.left.data;

            // Get the sum of nodes in right subtree
            if (node.right == null)
                rightSum = 0;
            else if (isLeaf(node.right))
                rightSum = node.right.data;
            else
                rightSum = 2 * node.right.data;

            return node.data == (leftSum + rightSum);
        }
        return false;
    }

    private static boolean isLeaf(Node node) {
        return node != null &&
                (node.left == null && node.right == null);
    }


}
