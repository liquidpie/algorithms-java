package com.vivek.tree.bst;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Returns node with keys falling in given range
     */
    List<Node> findAllNodesInRange(Node root, int min, int max) {
        List<Node> nodes = new ArrayList<>();

        findAllNodesInRange(root, min, max, nodes);

        return nodes;
    }

    private void findAllNodesInRange(Node node, int min, int max, List<Node> nodes) {
        if (node == null)
            return;

        if (min < node.data)
            findAllNodesInRange(node.left, min, max, nodes);

        if (node.data >= min && node.data <= max)
            nodes.add(node);

        if (max > node.data)
            findAllNodesInRange(node.right, min, max, nodes);
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
    }

}
