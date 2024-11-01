package com.vivek.tree.pattern.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Connect All Level Order Siblings (medium) #
 *
 * Given a binary tree, connect each node with its level order successor. The last node of each level should point
 * to the first node of the next level.
 *
 * Example:
 *        1
 *     /     \
 *    2       3
 *  /   \   /   \
 *  4   5   6   7
 *
 * After processing:
 *
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
 *
 * Solution #
 * This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same BFS approach.
 * The only difference will be that while traversing we will remember (irrespective of the level)
 * the previous node to connect it with the current node.
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
public class ConnectAllLevelOrderSiblings {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        connect(root);

        // level order traversal using 'next' pointer
        Node current = root;
        System.out.println("Traversal using 'next' pointer: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
    }

    static void connect(Node root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node prev = null;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);

            if (prev != null) {
                prev.next = current;
            }
            prev = current;
        }

    }

    static class Node {
        int data;
        Node left, right, next;

        public Node(int data) {
            this.data = data;
        }
    }

}
