package com.vivek.tree;

/**
 * Count Nodes
 *
 * Given the root of a binary tree, return the number of the nodes in the tree.
 */
public class CountNodes {

    public int countNodes(Node root) {
        if (root == null)
            return 0;

        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }

}
