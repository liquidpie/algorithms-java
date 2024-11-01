package com.vivek.tree.pattern.bfs;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Level Averages in a Binary Tree (easy)
 *
 * Given a binary tree, populate an array to represent the averages of all of its levels.
 *
 * Example 1:
 * Level Averages: [1, 2.5, 5.5]
 *
 *      1
 *   2    3
 * 4  5  6  7
 *
 *
 */
public class LevelAverages {

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(9);
        root.left.right = new Node(2);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        List<Double> result = findLevelAverages(root);
        System.out.print("Level averages are: " + result);
    }

    static List<Double> findLevelAverages(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<Double> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            double sum = 0;
            for (int i = 0; i < queueSize; i++) {
                Node node = queue.poll();
                sum += node.data;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(sum / queueSize);
        }
        return result;
    }

}
