package com.vivek.tree;

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
