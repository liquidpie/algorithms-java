package com.vivek.tree;

/**
 * Find the distance between two keys in a binary tree, no parent pointers are given.
 * Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.
 *
 * Solution:
 * We first find LCA of two nodes. Then we find distance from LCA to two nodes.
 */
public class DistanceBetweenNodes {

    Node lca(Node node, int n1, int n2) {
        if (node == null)
            return node;

        if (node.data == n1 || node.data == n2)
            return node;

        Node leftLCA = lca(node.left, n1, n2);
        Node rightLCA = lca(node.right, n1, n2);

        if (leftLCA != null && rightLCA != null)
            return node;

        return leftLCA != null ? leftLCA : rightLCA;
    }

    // Returns level of key k if it is present in
    // tree, otherwise returns -1
    int level(Node node, int k, int level) {
        if (node == null)
            return -1;

        if (node.data == k)
            return level;

        int leftLevel = level(node.left, k, level + 1);
        if (leftLevel == -1)
            return level(node.right, k, level + 1);

        return leftLevel;
    }

    int dist(Node root, int k1, int k2) {
        Node lca = lca(root, k1, k2);

        // distance of LCA and node k1
        int d1 = level(lca, k1, 0);
        int d2 = level(lca, k2, 0);

        return d1 + d2;
    }

    public static void main(String[] args) {
        DistanceBetweenNodes solution = new DistanceBetweenNodes();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println(solution.dist(root, 1, 7));

    }

}
