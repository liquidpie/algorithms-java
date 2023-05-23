package com.vivek.tree;

/**
 * Binary Tree Maximum Path Sum
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 * Example 1:
 *    1
 *  /   \
 * 2     3
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 * Example 2:
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *          * Approach: We want to calculate the sum of path originating from
 *          * left or right child of a node in a recursive manner. We then include
 *          * the value of the node to check if the resulting sum exceeds the sum of
 *          * value encountered so far. We then return the sum of the node's value and
 *          * the larger of the path originating from the left and right node.
 *          *
 *          * Time Complexity:O(n)
 *          * Space Complexity: O(1)
 *
 * https://www.youtube.com/watch?v=JBYs5J4skZE
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 */
public class MaximumPathSum {

    static int sum = 0;

    public static void main(String[] args) {
        Node root  = new Node(-10);
        root.left  = new Node(9);
        root.right = new Node(20);

        root.right.left   = new Node(15);
        root.right.right  = new Node(7);

        System.out.println(maxPathSum(root));
    }

    static int maxPathSum(Node root) {
        helper(root);
        return sum;
    }

    static int helper(Node root) {
        if (root == null)
            return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        int straightPath = Math.max(root.data, root.data + Math.max(left, right));
        int curvedPath = root.data + left + right;

        sum = Math.max(sum, Math.max(straightPath, curvedPath));

        return straightPath;
    }

}
