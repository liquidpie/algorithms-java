package com.vivek.tree.bst;

import com.vivek.tree.Node;

/**
 * Find the closest element in Binary Search Tree
 *
 * Given a binary search tree and a target node K. The task is to find the node with minimum absolute difference with given target value K.
 *
 *                 10
 *           5              15
 *        2     5     13         22
 *    1                   14
 *
 * Reference: https://www.geeksforgeeks.org/find-closest-element-binary-search-tree/
 */
public class ClosestElement {

    static int minDiff = Integer.MAX_VALUE;
    static int minDiffKey = -1;

    static int closestElement(Node root, int key) {
        findMinDiff(root, key);
        return minDiffKey;
    }

    private static void findMinDiff(Node node, int key) {
        if (node == null)
            return;

        if (key == node.data) {
            minDiffKey = node.data;
            return;
        }

        if (minDiff > Math.abs(key - node.data)) {
            minDiff = Math.abs(key - node.data);
            minDiffKey = node.data;;
        }

        if (key > node.data) {
            findMinDiff(node.right, key);
        } else {
            findMinDiff(node.left, key);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(2);
        root.left.right = new Node(5);
        root.left.left.left = new Node(1);
        root.right = new Node(15);
        root.right.left = new Node(13);
        root.right.right = new Node(22);
        root.right.left.right = new Node(14);

        System.out.println(closestElement(root, 12));

    }

}
