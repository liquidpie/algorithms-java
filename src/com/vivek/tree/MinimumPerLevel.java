package com.vivek.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by VJaiswal on 26/06/18.
 */
public class MinimumPerLevel {

    static void printMinPerLevel(Node root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        final Node MARKER = new Node(-1);
        queue.add(MARKER);

        int min = Integer.MAX_VALUE;
        int level = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // if node == MARKER (Means this is
            // boundary between two levels)
            if (node == MARKER) {
                System.out.println(String.format("Level %d, Min %d", level, min));

                // here queue is empty represent
                // no element in the actual
                // queue
                if (queue.isEmpty())
                    break;

                level++;
                queue.add(MARKER);
                // Reset min for next level
                // minimum value
                min = Integer.MAX_VALUE;
            }

            if (min > node.data)
                min = node.data;

            if (node.left != null)
                queue.add(node.left);

            if (node.right != null)
                queue.add(node.right);

        }
    }

}
