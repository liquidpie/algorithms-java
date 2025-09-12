package com.vivek.tree.pattern.bfs;

import com.vivek.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1161. Maximum Level Sum of a Binary Tree
 *
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 * Example 1:
 *
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 * Example 2:
 *
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 *
 * Reference: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree
 */
public class MaxLevelSum {

    public int maxLevelSum(Node root) {
        int maxLevel = 0;
        int level = 0;
        int max = Integer.MIN_VALUE;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            level++;
            int qSize = queue.size();
            int sum = 0;
            for (int i = 0; i < qSize; i++) {
                Node node = queue.poll();
                sum += node.data;

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }

            if (sum > max) {
                max = sum;
                maxLevel = level;
            }
        }
        return maxLevel;
    }

}
