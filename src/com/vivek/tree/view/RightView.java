package com.vivek.tree.view;

import com.vivek.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a Binary Tree, print Right view of it.
 * Right view of a Binary Tree is set of nodes visible when tree is visited from Right side.
 *
 * Solution: Do level order traversal and print the last node in every level
 */
public class RightView {

    void rightView(Node root) {
        if (root == null)
            return;

        Queue<Node> qNode = new LinkedList<>();
        Queue<Integer> qLevel = new LinkedList<>();

        qNode.add(root);
        qLevel.add(0);

        while (!qNode.isEmpty()) {
            Node node = qNode.poll();
            int level = qLevel.poll();

            if (qLevel.isEmpty() || qLevel.peek() > level) {
                System.out.print(node.data + " ");
            }

            if (node.left != null) {
                qNode.add(node.left);
                qLevel.add(level + 1);
            }

            if (node.right != null) {
                qNode.add(node.right);
                qLevel.add(level + 1);
            }
        }
    }

    public static void main(String[] args) {
        RightView view = new RightView();

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        view.rightView(root);
    }

}
