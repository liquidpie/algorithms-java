package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

/**
 * Path with Maximum Sum (hard) #
 *
 * Find the path with the maximum sum in a given binary tree. Write a function that returns the maximum sum.
 * A path can be defined as a sequence of nodes between any two nodes and doesn’t necessarily pass through the root.
 *
 * Example:
 *         1
 *       /  \
 *      2   3
 *    /    / \
 *   4    5   6
 *
 * Output: 16
 * Explanation: The path with maximum sum is: [4, 2, 1, 3, 6]
 *
 * Solution #
 * This problem follows the Binary Tree Path Sum pattern and shares the algorithmic logic with Tree Diameter.
 * We can follow the same DFS approach. The only difference will be to ignore the paths with negative sums.
 * Since we need to find the overall maximum sum, we should ignore any path which has an overall negative sum.
 *
 * Time complexity #
 * The time complexity of the above algorithm is O(N), where ‘N’ is the total number of nodes in the tree.
 * This is due to the fact that we traverse each node once.
 *
 * Space complexity #
 * The space complexity of the above algorithm will be O(N ) in the worst case. This space will be used to store the recursion stack.
 * The worst case will happen when the given tree is a linked list (i.e., every node has only one child).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: DFS
 */
public class NodeToNodePathMaxSum {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.left.left = new Node(7);
        root.right.left.right = new Node(8);
        root.right.right.left = new Node(9);
        System.out.println("Maximum Path Sum: " + findMaximumPathSum(root));
    }

    static int globalMaximumSum = Integer.MIN_VALUE;

    static int findMaximumPathSum(Node root) {
        findMaximumPathSumRecursive(root);
        return globalMaximumSum;
    }

    static int findMaximumPathSumRecursive(Node currentNode) {
        if (currentNode == null)
            return 0;

        int maxPathSumLeft = findMaximumPathSumRecursive(currentNode.left);
        int maxPathSumRight = findMaximumPathSumRecursive(currentNode.right);

        // ignore paths with negative sum
        maxPathSumLeft = Math.max(maxPathSumLeft, 0);
        maxPathSumRight = Math.max(maxPathSumRight, 0);

        // maximum path sum at the current node will be equal to the sum from the left subtree +
        // the sum from right subtree + val of current node
        int currentMaxSum = maxPathSumLeft + maxPathSumRight + currentNode.data;

        globalMaximumSum = Math.max(globalMaximumSum, currentMaxSum);

        // maximum sum of any path from the current node will be equal to the maximum of
        // the sums from left or right subtrees plus the value of the current node
        return Math.max(maxPathSumLeft, maxPathSumRight) + currentNode.data;
    }

}
