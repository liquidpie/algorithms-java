package com.vivek.tree.pattern.bfs;

import com.vivek.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Level Order Successor (easy)
 *
 * Given a binary tree and a node, find the level order successor of the given node in the tree. The level order
 * successor is the node that appears right after the given node in the level order traversal.
 *
 * Example 1:
 *
 *      1
 *   2     3
 * 4   5
 *
 * Given Node: 3
 * Level Order Successor: 4
 *
 * Solution #
 * This problem follows the Binary Tree Level Order Traversal pattern.
 * We can follow the same BFS approach. The only difference will be that we will not keep track of all the levels.
 * Instead we will keep inserting child nodes to the queue. As soon as we find the given node,
 * we will return the next node from the queue as the level order successor.
 *
 * Time complexity #
 * The time complexity of the above algorithm is O(N), where ‘N’ is the total number of nodes in the tree. This
 * is due to the fact that we traverse each node once.
 *
 * Space complexity #
 * The space complexity of the above algorithm will be O(N ) which is required for the queue.
 * Since we can have a maximum of N /2 nodes at any level (this could happen only at the lowest level),
 * therefore we will need O(N ) space to store them in the queue.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: BFS
 */
public class LevelOrderSuccessor {

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(9);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        Node result = findSuccessor(root, 12);
        if (result != null)
            System.out.println(result.data + " ");
        result = findSuccessor(root, 9);
        if (result != null)
            System.out.println(result.data + " ");
    }

    static Node findSuccessor(Node root, int key) {
        if (root == null)
            return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            // insert the children of current node in the queue
            if (currentNode.left != null)
                queue.offer(currentNode.left);
            if (currentNode.right != null)
                queue.offer(currentNode.right);
            // break if we have found the key
            if (currentNode.data == key)
                break;
        }
        return queue.peek();
    }
}
