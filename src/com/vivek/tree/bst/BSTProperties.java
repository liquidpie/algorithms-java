package com.vivek.tree.bst;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class BSTProperties {

    /**
     * The trick is to write a utility helper function isBSTUtil(Node node, Node min, Node max)
     * that traverses down the tree keeping track of the narrowing min and max allowed values as it goes,
     * looking at each node only once. The initial values for min and max should be INT_MIN and INT_MAX
     * — then narrow from there.
     *
     * https://walkccc.me/LeetCode/problems/0098/
     */
    static boolean isBST(Node node) {
        return isBSTUtil(node, null, null);
    }

//    private static boolean isBSTUtil(Node node, int min, int max) {
//        // an empty tree is BST
//        if (node == null)
//            return true;
//
//        if (node.data < min || node.data > max)
//            return false;
//
//        return isBSTUtil(node.left, min, node.data - 1) &&
//                isBSTUtil(node.right, node.data + 1, max);
//    }

    private static boolean isBSTUtil(Node node, Node min, Node max) {
        // an empty tree is BST
        if (node == null)
            return true;

        if (min != null && node.data <= min.data) {
            return false;
        }
        if (max != null && node.data >= max.data) {
            return false;
        }

        return isBSTUtil(node.left, min, node) &&
                isBSTUtil(node.right, node, max);
    }

    /**
     * Returns the second largest element in BST
     *
     * The second largest element is second last element in inorder traversal and second element in reverse inorder traversal.
     *
     * We traverse given Binary Search Tree in reverse inorder and keep track of counts of nodes visited. Once the count becomes 2, we print the node.
     */
    static int count = 0;

    static void secondLargest(Node node) {
        if (node == null || count >= 2)
            return;

        // Follow reverse inorder traversal so that the
        // largest element is visited first
        secondLargest(node.right);

        count++;

        if (count == 2) {
            System.out.println(node.data);
            return;
        }

        secondLargest(node.left);
    }

    /**
     * Returns nodes with keys falling in given range
     */
    List<Node> findAllNodesInRange(Node root, int min, int max) {
        List<Node> nodes = new ArrayList<>();

        findAllNodesInRange(root, min, max, nodes);

        return nodes;
    }

    private void findAllNodesInRange(Node node, int min, int max, List<Node> nodes) {
        if (node == null)
            return;

        if (node.data < min)
            findAllNodesInRange(node.left, min, max, nodes);

        if (node.data >= min && node.data <= max)
            nodes.add(node);

        if (node.data < max)
            findAllNodesInRange(node.right, min, max, nodes);
    }

    public static int sizeOfTree(Node root) {
        if (root == null) {
            return 0;
        }

        // Calculate left size recursively
        int left = sizeOfTree(root.left);

        // Calculate right size recursively
        int right = sizeOfTree(root.right);

        // Return total size recursively
        return (left + right + 1);
    }

    public static void main(String[] args) {
        BSTProperties test = new BSTProperties();

        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(6);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(7);

        List<Node> nodes = test.findAllNodesInRange(root, 6, 9);

        for (Node node: nodes) {
            System.out.println(node.data);
        }

        Node root2 = new Node(Integer.MIN_VALUE);
        root2.left = new Node(Integer.MIN_VALUE);
        System.out.println(isBST(root2));
    }

}
