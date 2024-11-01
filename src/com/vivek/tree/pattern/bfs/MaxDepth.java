package com.vivek.tree.pattern.bfs;

import com.vivek.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its maximum depth (or height).
 *
 * Solution
 * We will follow a similar approach to MinDepth. Instead of returning as soon as we find a leaf node,
 * we will keep traversing for all the levels, incrementing maximumDepth each time we complete a level.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: BFS
 */
public class MaxDepth {

    static int maxDepthOfTree(Node root) {
        if (root == null)
            return -1;
        int maxDepth = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxDepth++;
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        return maxDepth;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println(maxDepthOfTree(root));
    }

}
