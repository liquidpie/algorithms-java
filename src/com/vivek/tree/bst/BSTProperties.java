package com.vivek.tree.bst;

import com.vivek.tree.Node;

public class BSTProperties {

    /**
     * The trick is to write a utility helper function isBSTUtil(Node node, int min, int max)
     * that traverses down the tree keeping track of the narrowing min and max allowed values as it goes,
     * looking at each node only once. The initial values for min and max should be INT_MIN and INT_MAX
     * — then narrow from there.
     */
    static boolean isBST(Node node) {
        return isBSTUtil(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBSTUtil(Node node, int min, int max) {
        // an empty tree is BST
        if (node == null)
            return true;

        if (node.data < min || node.data > max)
            return false;

        return isBSTUtil(node.left, min, node.data - 1) &&
                isBSTUtil(node.right, node.data + 1, max);
    }

    /**
     * returns the second largest element in BST
     */
    static int secondLargest(Node node) {
        if (node == null)
            return -1;

        while (node.right != null) {
            node = node.right;
        }

        return node.data;
    }

}
