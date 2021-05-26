package com.vivek.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * K-th ancestor of a node in Binary Tree
 *
 * Given a binary tree in which nodes are numbered from 1 to n. Given a node and a positive integer K. We have to print the K-th ancestor of the given node in the binary tree. If there does not exist any such ancestor then print -1.
 * For example in the below given binary tree, 2nd ancestor of node 4 and 5 is 1. 3rd ancestor of node 4 will be -1.
 *
 * Approaches:
 *
 * 1. Use Parent array to find Kth ancestor using BFS method (https://www.geeksforgeeks.org/kth-ancestor-node-binary-tree)
 * 2. Use Recursive DFS to find Kth ancestor
 *
 */
public class KthAncestorOfNode {

    /**
     * Approach:
     * The idea to do this is to first traverse the binary tree and store the ancestor of each node in an array of size n.
     * For example, suppose the array is anecestor[n]. Then at index i, ancestor[i] will store the ancestor of ith node.
     * So, the 2nd ancestor of ith node will be ancestor[ancestor[i]] and so on.
     * We will use this idea to calculate the kth ancestor of the given node. We can use level order traversal to populate this array of ancestors.
     *
     * This is a BFS based solution
     */
    static class UsingParentArrayBFS {
        static int kthAncestor(Node root, int n, int node, int k) {
            if (root == null || k < 0)
                return -1;
            if (k == 0)
                return node;

            int[] parent = new int[n + 1];
            buildAncestorArray(root, parent);

            int temp = node;
            while (k > 0 && temp != -1) {
                temp = parent[temp];
                k--;
            }

            return temp;
        }

        private static void buildAncestorArray(Node root, int[] parent) {
            parent[root.data] = -1;

            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node node = queue.poll();

                if (node.left != null) {
                    parent[node.left.data] = node.data;;
                    queue.add(node.left);
                }

                if (node.right != null) {
                    parent[node.right.data] = node.data;
                    queue.add(node.right);
                }
            }
        }
    }

    /**
     * Approach:
     *
     * The idea of using DFS is to first find the given node in the tree, and then backtrack k times to reach to kth ancestor,
     * once we have reached to the kth parent, we will simply print the node and return NULL.
     */
    static class UsingRecursiveDFS {

        private static Node temp;
        private static int k;

        static void kthAncestor(Node root, int node, int _k) {
            k = _k;
            // check if parent is not null, it means
            // there is no Kth ancestor of the node
            if (kthAncestor(root, node) != null)
                System.out.println(-1);
        }

        private static Node kthAncestor(Node root, int node) {
            if (root == null)
                return null;

            if (root.data == node ||
                (temp = kthAncestor(root.left, node)) != null ||
                (temp = kthAncestor(root.right, node)) != null) {
                if (k > 0)
                    k--;
                else if (k == 0) {
                    System.out.println("Kth Ancestor is : " + root.data);
                    // return null to stop further backtracking
                    return null;
                }
                return root;
            }
            return null;
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

        System.out.println(UsingParentArrayBFS.kthAncestor(root, 7, 5, 2));

        UsingRecursiveDFS.kthAncestor(root, 5, 2);
    }

}
