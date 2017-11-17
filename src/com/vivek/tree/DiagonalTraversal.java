package com.vivek.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a Binary Tree, print all diagonal elements in a binary tree belonging to same line.
 *
 * The idea is to use map. We use different slope distances and use them as key in map.
 * Value in map is vector (or dynamic array) of nodes. We traverse the tree to store values in map.
 * Once map is built, we print contents of it.
 *
 */
public class DiagonalTraversal {

    void printDiagonals(Node root) {
        // create a map of lists to store Diagonal elements
        Map<Integer, List<Integer>> diagMap = new HashMap<>();

        printDiagonalUtil(root, 0, diagMap);

        // print contents of map
        for (Map.Entry<Integer, List<Integer>> entry : diagMap.entrySet())
            System.out.println(entry.getValue());
    }

    void printDiagonalUtil(Node node, int d, Map<Integer, List<Integer>> diagMap) {
        // base case
        if (node == null)
            return;

        if (diagMap.get(d) == null)
            diagMap.put(d, new ArrayList<>());

        // Store all nodes of same line together as a list
        diagMap.get(d).add(node.data);

        // Increase the vertical distance if left child
        printDiagonalUtil(node.left, d + 1, diagMap);

        // Vertical distance remains same for right child
        printDiagonalUtil(node.right, d, diagMap);
    }

    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);

        DiagonalTraversal dt = new DiagonalTraversal();
        dt.printDiagonals(root);

    }

}
