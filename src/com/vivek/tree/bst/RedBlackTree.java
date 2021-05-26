package com.vivek.tree.bst;

/**
 * Created by VJaiswal on 17/10/17.
 *
 * Red-Black Tree is a self-balancing Binary Search Tree (BST) where every node follows below rules:
 * 1. Every node has a color either red or black
 * 2. Root of tree is always black
 * 3. There are no two adjacent red nodes (A red node cannot have a red parent or red child)
 * 4. Every path from root to a NULL node has same number of black nodes
 *
 * The height of a Red Black tree is always O(Log n) where n is the number of nodes in the tree.
 * Every Red Black Tree with n nodes has height <= Log2(n+1)
 *
 * The AVL trees are more balanced compared to Red Black Trees,
 * but they may cause more rotations during insertion and deletion.
 * So if your application involves many frequent insertions and deletions,
 * then Red Black trees should be preferred.
 * And if the insertions and deletions are less frequent and search is more frequent operation,
 * then AVL tree should be preferred over Red Black Tree.
 *
 * Black height is number of black nodes on a path from a node to a leaf. Leaf nodes are also counted black nodes.
 * From above properties 3 and 4, we can derive, a node of height h has black-height >= h/2.
 *
 * http://www.geeksforgeeks.org/red-black-tree-set-2-insert/
 * http://www.codebytes.in/2014/10/red-black-tree-java-implementation.html
 */
public class RedBlackTree {

    private static final int RED = 0;
    private static final int BLACK = 1;

    /**
     * Insertion in a Red Black Tree
     * @param root
     * @param data
     * @return new root of the tree
     */
    Node insert(Node root, int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
        } else {
            Node temp = root;
            node.color = RED;
            while (true) {
                if (node.data < temp.data) {
                    if (temp.left == null) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if (temp.right == null) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
        }

        return fixViolation(root, node);
    }

    Node fixViolation(Node root, Node node) {
        while (node.parent.color == RED) {
            Node uncle = null;

            // left case
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                // recolor
                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }

                // left-right case
                if (node == node.parent.right) {
                    // double rotation
                    node = node.parent;
                    root = rotateLeft(root, node);
                }

                node.parent.color = BLACK;
                node.parent.parent.color = RED;

                // if above if block isn't executed, then it is left-left case
                // otherwise, left-right case
                root = rotateRight(root, node.parent.parent);
            } else {
                uncle = node.parent.parent.left;

                // recolor
                if (uncle != null && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }

                // right-left case
                if (node == node.parent.left) {
                    // double rotation
                    node = node.parent;
                    root = rotateRight(root, node);
                }

                node.parent.color = BLACK;
                node.parent.parent.color = RED;

                // if above if block isn't executed, then it is right-right case
                // otherwise, right-left case
                root = rotateLeft(root, node.parent.parent);
            }
        }
        root.color = BLACK;
        return root;
    }

    Node rotateLeft(Node root, Node node) {
        if (node.parent != null) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != null) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {//Need to rotate root
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = null;
            root = right;
        }
        return root;
    }

    Node rotateRight(Node root, Node node) {
        if (node.parent != null) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != null) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {//Need to rotate root
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = null;
            root = left;
        }
        return root;
    }

    private static class Node {
        int data = -1;
        int color = BLACK;

        Node left, right, parent;

        public Node(int data) {
            this.data = data;
        }
    }

}
