package com.vivek.tree.pattern.bfs;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Find the largest value on each level of a binary tree.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: BFS
 */
public class LevelLargestValue {

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(9);
        root.left.right = new Node(2);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        List<Integer> result = findMax(root);
        System.out.print("Level averages are: " + result);
    }

    static List<Integer> findMax(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < queueSize; i++) {
                Node node = queue.poll();
                max = Math.max(max, node.data);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(max);
        }
        return result;
    }

}
