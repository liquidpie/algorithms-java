package com.vivek.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree with distinct nodes(no two nodes have the same have data values).
 * The problem is to print the path from root to a given node x. If node x is not present then print “No Path”.
 *
 * Approach:
 * Create a recursive function that traverses the different path in the binary tree to find the required node x.
 * If node x is present then it returns true and accumulates the path nodes in some array arr[].
 * Else it returns false.
 *
 *  - If root = NULL, return false.
 *  - push the root’s data into arr[].
 *  - if root’s data = x, return true.
 *  - if node x is present in root’s left or right subtree, return true.
 *  - Else remove root’s data value from arr[] and return false.
 */
public class RootToNodePath {

    static boolean hasPath(Node root, int node, List<Integer> path) {
        // if root is NULL there is no path
        if (root == null)
            return false;

        path.add(root.data);

        // if it is the required node return true
        if (root.data == node)
            return true;

        // else check whether the required node lies in the left subtree or right subtree of the current node
        if (hasPath(root.left, node, path) ||
            hasPath(root.right, node, path))
            return true;

        // required node does not lie either in the left or right subtree of the current node
        // Thus, remove current node's value from list and then return false
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        List<Integer> path = new ArrayList<>();
        if (hasPath(root, 5, path)) {
            System.out.println(path);
        } else {
            System.out.println("No path exists");
        }
    }

}
