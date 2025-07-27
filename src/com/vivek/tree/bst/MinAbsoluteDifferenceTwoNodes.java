package com.vivek.tree.bst;

import com.vivek.tree.Node;

/**
 * 530. Minimum Absolute Difference in BST
 *
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference
 * between the values of any two different nodes in the tree.
 *
 *
 * Example 1:
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 *
 * Example 2:
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 * Solution:
 * In a Binary Search Tree, the in-order traversal gives nodes in sorted order.
 * So, the minimum absolute difference will always be between two adjacent nodes in this sequence.
 * - Perform in-order traversal.
 * - At each node, calculate the difference between current and previous node.
 * - Keep track of the smallest difference found.
 *
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/solutions/6961212/minimum-absolute-difference-in-bst/?source=submission-noac
 *
 *
 * Reference:
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst
 */
public class MinAbsoluteDifferenceTwoNodes {

    public static void main(String[] args) {
        MinAbsoluteDifferenceTwoNodes helper = new MinAbsoluteDifferenceTwoNodes();
        Node root = new Node(1);
        root.right = new Node(5);
        root.right.left = new Node(3);

        System.out.println(helper.getMinimumDifference(root));
    }

    private Node prev = null;
    private int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(Node root) {
        if (root == null)
            return 0;
        inorderTraversal(root);
        return minDiff;
    }

    private void inorderTraversal(Node node) {
        if (node == null)
            return;

        inorderTraversal(node.left);

        if (prev != null) {
            minDiff = Math.min(minDiff, Math.abs(node.data - prev.data));
        }
        prev = node;
        inorderTraversal(node.right);
    }

}
