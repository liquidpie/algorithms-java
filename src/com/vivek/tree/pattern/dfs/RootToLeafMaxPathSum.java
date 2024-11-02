package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Root-to-leaf path with the maximum sum
 *
 * Given a binary tree, find the root-to-leaf path with the maximum sum.
 *
 * Example:
 *          1
 *     7        9
 *   4   5    2   7
 * Sum: 12
 * Output:
 *  1 -> 9 -> 7
 *
 * Solution #
 * We need to find the path with the maximum sum.
 * As we traverse all paths, we can keep track of the path with the maximum sum.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: DFS
 */
public class RootToLeafMaxPathSum {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(7);
        root.right = new Node(9);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(2);

        System.out.println(findPaths(root));
    }

    static List<Integer> findPaths(Node root) {
        List<Integer> maxPathSum = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        int[] maxSum = {Integer.MIN_VALUE};
        findPathsRecursive(root, 0, maxSum, maxPathSum, currentPath);
        return maxPathSum;
    }

    private static void findPathsRecursive(Node node, int currentSum, int[] maxSum, List<Integer> maxPathSum, List<Integer> currentPath) {
        if (node == null)
            return;

        // add the current node to the path
        currentPath.add(node.data);
        currentSum += node.data;

        // if the current node is a leaf and its value is equal to sum, save the current path
        if (node.left == null && node.right == null) {
            if (currentSum > maxSum[0]) {
                maxSum[0] = currentSum;
                maxPathSum.clear();
                maxPathSum.addAll(currentPath);
            }
        } else {
            findPathsRecursive(node.left, currentSum, maxSum, maxPathSum, currentPath);
            findPathsRecursive(node.right, currentSum, maxSum, maxPathSum, currentPath);
        }
        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
    }
}
