package com.vivek.tree.pattern.bfs;

import com.vivek.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Minimum Depth of a Binary Tree
 *
 * Find the minimum depth of a binary tree. The minimum depth is the number of nodes along the shortest
 * path from the root node to the nearest leaf node.
 *
 * Example 1:
 * Minimum Depth: 2
 *           1
 *        2     3
 *      4   5
 *
 * Solution
 * This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach.
 * The only difference will be, instead of keeping track of all the nodes in a level, we will only track the depth of
 * the tree. As soon as we find our first leaf node, that level will represent the minimum depth of the tree.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: BFS
 */
public class MinDepth {

    static int minDepthOfTree(Node root) {
        if (root == null)
            return -1;
        int minDepth = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            minDepth++;
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (node.left == null && node.right == null) {
                    return minDepth;
                }
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        return minDepth;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println(minDepthOfTree(root));
    }

}
