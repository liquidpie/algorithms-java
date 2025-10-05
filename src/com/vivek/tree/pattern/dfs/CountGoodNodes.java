package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

/**
 * 1448. Count Good Nodes in Binary Tree
 *
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 *
 * Example 1:
 *
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 *
 * Example 2:
 *
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 *
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 *
 * Reference: https://leetcode.com/problems/count-good-nodes-in-binary-tree
 */
public class CountGoodNodes {

    public int goodNodes(Node root) {
        return traversal(root, Integer.MIN_VALUE);
    }

    private int traversal(Node node, int max) {
        if (node == null)
            return 0;

        int count = node.data >= max ? 1 : 0;

        count += traversal(node.left, Math.max(max, node.data));
        count += traversal(node.right, Math.max(max, node.data));

        return count;
    }

}
