package com.vivek.tree;

/**
 * Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root.
 *
 * We break the problem in 3 parts:
 *
 * 1. Print the left boundary in top-down manner.
 * 2. Print all leaf nodes from left to right, which can again be sub-divided into two sub-parts:
 *    2.1 Print all leaf nodes of left sub-tree from left to right.
 *    2.2 Print all leaf nodes of right subtree from left to right.
 * 3. Print the right boundary in bottom-up manner.
 *
 * We need to take care of one thing that nodes are not printed again.
 * e.g. The left most node is also the leaf node of the tree.
 *
 */
public class BoundaryTraversal {

    void printBoundary(Node root) {
        if (root == null)
            return;

        System.out.print(root.data + " ");

        printLeftBoundary(root.left);

        printLeaves(root.left);
        printLeaves(root.right);

        printRightBoundary(root.right);
    }

    void printLeaves(Node node) {
        if (node == null)
            return;

        printLeaves(node.left);

        if (node.left == null && node.right == null)
            System.out.print(node.data + " ");

        printLeaves(node.right);
    }

    void printLeftBoundary(Node node) {
        if (node == null)
            return;

        if (node.left != null) {
            System.out.print(node.data + " ");
            printLeftBoundary(node.left);
        } else if (node.right != null) {
            System.out.print(node.data + " ");
            printLeftBoundary(node.right);
        }
    }

    void printRightBoundary(Node node) {
        if (node == null)
            return;

        if (node.right != null) {
            printRightBoundary(node.right);
            System.out.print(node.data + " ");
        } else if (node.left != null) {
            printRightBoundary(node.left);
            System.out.print(node.data + " ");
        }
    }

    public static void main(String[] args) {
        Node root = new Node(20);
        root.left = new Node(8);

        root.left.left = new Node(4);
        root.left.right = new Node(12);

        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);

        root.right = new Node(22);
        root.right.right = new Node(25);

        BoundaryTraversal bt = new BoundaryTraversal();
        bt.printBoundary(root);

    }

}
