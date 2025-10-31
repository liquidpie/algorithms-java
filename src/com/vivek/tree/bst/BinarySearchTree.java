package com.vivek.tree.bst;

import com.vivek.tree.Node;

/**
 * Created by VJaiswal on 15/10/17.
 */
public class BinarySearchTree {

    Node search(Node root, int value) {
        if (root == null)
            return root;

        if (value == root.data)
            return root;

        if (value < root.data)
            return search(root.left, value);
        else
            return search(root.right, value);

    }

    Node insert(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (value < root.data) {
            root.left = insert(root.left, value);
        }

        if (value > root.data) {
            root.right = insert(root.right, value);
        }
        return root;
    }

    /**
     * Case 1: Node has No Children (Leaf Node)
     * If the target node is a leaf node, it can be directly removed from the tree since it has no child to maintain.
     *
     * Case 2: Node has One Child(Left or Right Child)
     * If the target node has only one child, we remove the node and connect its parent directly to its only child.
     * This way, the tree remains valid after deletion of target node.
     *
     * Case 3: Node has Two Children
     * If the target node has two children, deletion is slightly more complex.
     * To maintain the BST property, we need to find a replacement node for the target. The replacement can be either:
     *
     *     The inorder successor — the smallest value in the right subtree, which is the next greater value than the target node.
     *     The inorder predecessor — the largest value in the left subtree, which is the next smaller value than the target node.
     *
     * Once the replacement node is chosen, we replace the target node’s value with that node’s value,
     * and then delete the replacement node, which will now fall under Case 1 (no children) or Case 2 (one child).
     * Note: Inorder predecessor can also be used.
     */
    Node delete(Node root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.data) {
            root.left = delete(root.left, value);
        } else if (value > root.data) {
            root.right = delete(root.right, value);
        } else { // if key is same as root's key, then This is the node to be deleted

            // node with only one child or no child
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = delete(root.right, root.data);
        }
        return root;
    }

    int minValue(Node root) {
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

}
