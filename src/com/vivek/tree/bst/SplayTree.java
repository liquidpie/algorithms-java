package com.vivek.tree.bst;

import com.vivek.tree.Node;

/**
 * Created by VJaiswal on 16/10/17.
 *
 * Like AVL and Red-Black Trees, Splay tree is also self-balancing BST.
 *
 * The main idea of splay tree is to bring the recently accessed item to root of the tree,
 * this makes the recently searched item to be accessible in O(1) time if accessed again.
 * The idea is to use locality of reference (In a typical application, 80% of the access are to 20% of the items).
 *
 * All splay tree operations run in O(log n) time on average, where n is the number of entries in the tree.
 *
 * The search operation in Splay tree does the standard BST search, in addition to search,
 * it also splays (move a node to the root). If the search is successful, then the node that is found is splayed
 * and becomes the new root. Else the last node accessed prior to reaching the NULL is splayed and becomes the new root.
 *
 * Splay trees are simpler compared to AVL and Red-Black Trees as no extra field is required in every tree node.
 * Unlike AVL tree, a splay tree can change even with read-only operations like search.
 *
 * http://www.geeksforgeeks.org/splay-tree-set-1-insert/
 * http://www.geeksforgeeks.org/splay-tree-set-2-insert-delete/
 */
public class SplayTree {

    /**
     * The search function for Splay tree. Note that this function
     * returns the new root of Splay Tree. If key is present in tree
     * then, it is moved to root.
     */
    Node search(Node root, int key) {
        return splay(root, key);
    }

    /**
     * It's similar to Binary Search Tree insert with additional steps to make sure
     * that the newly inserted key becomes the new root
     */
    Node insert(Node root, int key) {
        // base case, if tree is empty
        if (root == null)
            return new Node(key);

        // splay the closest leaf node to root
        root = splay(root, key);

        // if key is already present, then return
        if (key == root.data)
            return root;

        // otherwise create a new node
        Node node = new Node(key);

        // if root's key is greater, make root as right child
        // of new node and copy the left child of root to left of new node
        if (key < root.data) {
            node.right = root;
            node.left = root.left;
            root.left = null;
        }
        // if root's key is smaller, make root as left child
        // of new node and copy the right child of root to right of new node
        else {
            node.left = root;
            node.right = root.right;
            root.right = null;
        }

        // node becomes new root
        return node;
    }


    /**
     * If a node with that key exists, it is splayed to the root of the tree. If it does not, the last node
     * along the search path for the key is splayed to the root.
     */
    private Node splay(Node root, int key) {
        // base case
        if (root == null || root.data == key) {
            return root;
        }

        // key lies in left subtree
        if (key < root.data) {
            // key is not in tree
            if (root.left == null) {
                return root;
            }
            // Zig-Zig (Left Left) case
            if (key < root.left.data) {
                // first recursively bring the key as root of left-left
                root.left.left = splay(root.left.left, key);
                // do first rotation for root, second rotation is done after else
                root = rightRotate(root);
            }
            else if (key > root.left.data) { // Zig-Zag (Left Right) case
                // first recursively bring the key as root of left-right
                root.left.right = splay(root.left.right, key);
                // do first rotation for root.left
                if (root.left.right != null) {
                    root.left = leftRotate(root.left);
                }
            }
            // do second rotation for root
            return (root.left == null) ? root : rightRotate(root);
        }
        else { // key lies in right subtree
            // key is not in tree
            if (root.right == null) {
                return root;
            }
            // Zag-Zag (Right Right) case
            if (key > root.right.data) {
                // first recursively bring the key as root of right-right
                root.right.right = splay(root.right.right, key);
                // do first rotation for root
                root = leftRotate(root);
            }
            else if (key < root.right.data) { // Zag-Zig (Right Left) case
                // first recursively bring the key as root of right-left
                root.right.left = splay(root.right.left, key);
                // do first rotation for root.right
                if (root.right.left != null) {
                    root.right = rightRotate(root.right);
                }
            }
            // do second rotation for root
            return (root.right == null) ? root : leftRotate(root);
        }
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

}
