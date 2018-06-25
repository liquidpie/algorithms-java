package com.vivek.tree;

/**
 * Created by VJaiswal on 16/10/17.
 *
 * An AVL tree is another balanced binary search tree.
 *
 * An AVL tree is a binary search tree which has the following properties:
 * 1. The sub-trees of every node differ in height by at most one.
 * 2. Every sub-tree is an AVL tree.
 *
 * In an AVL tree, balance factor of every node is either -1, 0 or +1.
 * The balance factor of a node is calculated either
 * height of left subtree - height of right subtree (OR) height of right subtree - height of left subtree.
 *
 * In AVL tree, after performing every operation like insertion and deletion we need to check the balance factor
 * of every node in the tree. If every node satisfies the balance factor condition then
 * we conclude the operation otherwise we must make it balanced. We use rotation operations
 * to make the tree balanced whenever the tree is becoming imbalanced due to any operation.
 *
 * Most of the BST operations (e.g., search, max, min, insert, delete.. etc) take O(h) time
 * where h is the height of the BST. The cost of these operations may become O(n) for a skewed Binary tree.
 * If we make sure that height of the tree remains O(Logn) after every insertion and deletion,
 * then we can guarantee an upper bound of O(Logn) for all these operations.
 * The height of an AVL tree is always O(Logn) where n is the number of nodes in the tree
 *
 * http://btechsmartclass.com/DS/U5_T2.html
 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * http://www.geeksforgeeks.org/avl-tree-set-2-deletion/
 */
public class AVLTree {

    // utility to get height of the node
    int height(Node node) {
        if (node == null)
            return 0;

        return node.height;
    }

    // utility to right rotate subtree rooted with y
    Node rightRotate(Node z) {
        Node y = z.left;
        Node T3 = y.right;

        // Perform rotation
        y.right = z;
        z.left = T3;

        // Update heights
        z.height = Math.max(height(z.left), height(z.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // utility to left rotate subtree rooted with x
    Node leftRotate(Node z) {
        Node y = z.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = z;
        z.right = T2;

        //  Update heights
        z.height = Math.max(height(z.left), height(z.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // get balance factor of Node n
    int getBalanceFactor(Node n) {
        if (n == null)
            return 0;
        return height(n.left) - height(n.right);
    }

    Node insert(Node node, int data) {
        // perform normal BST insertion
        if (node == null)
            return new Node(data);

        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);
        else    // duplicate keys not allowed
            return node;

        // update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        return balanceTree(node);
    }

    Node delete(Node node, int data) {
        if (node == null) {
            return node;
        }

        if (data < node.data) {
            node.left = delete(node.left, data);
        } else if (data > node.data) {
            node.right = delete(node.right, data);
        } else { // if key is same as root's key, then This is the node to be deleted

            // node with only one child or no child
            if (node.left == null || node.right == null) {
                Node temp = null;
                if (node.left == null)
                    temp = node.right;
                if (node.right == null)
                    temp = node.left;

                // no child case
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    // one child case
                    node = temp; // copy the contents of non-empty child
                }
            }
            else {
                // node with two children: Get the inorder successor (smallest
                // in the right subtree)
                node.data = minValue(node.right);

                // Delete the inorder successor
                node.right = delete(node.right, node.data);
            }
        }

        // if the tree had only one node, return
        if (node == null)
            return node;


        // update height of this current node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        return balanceTree(node);
    }

    Node balanceTree(Node node) {
        // get balance factor of this node
        int balanceFactor = getBalanceFactor(node);

        // if this node becomes unbalanced, there are four cases

        // case 1: Left Left case
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        // case 2: Right Right case
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        // case 3: Left Right case
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // case 4: Right Left case
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    int minValue(Node root) {
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    static class Node {
        int data, height;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }

}
