package com.vivek.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Deepest Leaves Sum
 *
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 *
 * Example 2:
 *
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 19
 *
 *          * Approach: We used the approach of breadth first search to
 *          * find the deepest-level leaves and find the sum of those
 *          * elements.
 *          *
 *          * Time Complexity: O(n)
 *          * Space Complexity: O(n)
 *
 * https://leetcode.com/problems/deepest-leaves-sum/
 */
public class DeepestLeavesSum {

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.left = new Node(2);

        System.out.println(deepestLeavesSum(root));
    }

    static int deepestLeavesSum(Node root) {
        if (root == null)
            return 0;

        int sum = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int currSize = queue.size();
            sum = 0;

            while (currSize-- > 0 && !queue.isEmpty()) {
                Node node = queue.poll();
                sum += node.data;

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        return sum;
    }

}
