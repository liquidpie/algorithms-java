package com.vivek.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, connect each node with its level order successor. The last node of each level should point
 * to a null node.
 * Example:
 *                   1 -> null
 *              2    ->   3 -> null
 *          4 -> 5 -> 6 -> 7 -> null
 *
 *
 */
public class ConnectLevelOrderSiblings {

    static void connectLevelOrderSiblings(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node previousNode = null;
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (previousNode != null)
                    previousNode.next = node;
                previousNode = node;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
    }

    private static class Node {
        int data;
        Node left, right, next;

        Node(int data) {
            this.data = data;
        }

        void printLevelOrderUsingNextPointer() {
            Node nextLevelRoot = this;
            while (nextLevelRoot != null) {
                Node current = nextLevelRoot;
                nextLevelRoot = null;
                while (current != null) {
                    System.out.print(current.data + " ");
                    if (nextLevelRoot == null) {
                        if (current.left != null)
                            nextLevelRoot = current.left;
                        else if (current.right != null)
                            nextLevelRoot = current.right;
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        connectLevelOrderSiblings(root);

        root.printLevelOrderUsingNextPointer();
    }

}
