package com.vivek.tree.nary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Kth ancestor of a node in an N-ary tree using Binary Lifting Technique
 *
 * Given a vertex V of an N-ary tree and an integer K, the task is to print the Kth ancestor of the given vertex in the tree.
 * If there does not exist any such ancestor then print -1.
 *
 */
public class KthAncestorOfNode {

    /**
     * At each vertex of the tree, instead of only knowing who the parent is, we also remember who the 2nd, 4th, 8th, ... ancestors are.
     * We call these the binary ancestors of the vertex. This means that each vertex remembers O(log(n)) of its ancestors, so we pay O(nlog(n)) space,
     * but it will be worth it.
     *
     * We can compute the binary ancestors in our preprocessing step with a depth-first search.
     * The key observation that makes binary ancestors easy to compute is the following:
     *
     *     The kth binary ancestor of a vertex is the (k-1)th binary ancestor of its (k-1)th binary ancestor
     *
     * Binary Lifting is a dynamic programming approach where we pre-compute an array memo[1, n][1, log(n)] where memo[i][j] contains 2^j-th ancestor of node i.
     * For computing the values of memo[][], the following recurrence relation may be used
     *
     *     memo[i][j] = parent[i] if j = 0 and
     *     memo[i][j] = memo[memo[i][j – 1]][j – 1] if j > 0.
     *
     * Reference: https://giaosame.me/Binary-Lifting
     */
    static class UsingBinaryLifting {

        private final int n;
        private final int log;
        private final int[][] dp;

        UsingBinaryLifting(Node root, int n) {
            this.n = n;
            this.log = (int) Math.ceil(Math.log10(n) / Math.log10(2));
            this.dp = new int[n][log];
            preprocess(root);
        }

        int kthAncestor(int node, int k) {
            for (int j = 0; j < log; j++) {
                if (((k >> j) & 1) == 1) {
                    node = dp[node][j];
                    if (node == -1)
                        break;
                }
            }
            return node;
        }

        private void preprocess(Node root) {
            int[] parent = buildAncestorArray(root);

            for (int i = 0; i < n; i++) {
                dp[i][0] = parent[i];

                for (int j = 1; j < log; j++) {
                    if (dp[i][j - 1] == -1)
                        dp[i][j] = -1;
                    else
                        dp[i][j] = dp[dp[i][j - 1]][j - 1];
                }
            }
        }

        private int[] buildAncestorArray(Node root) {
            int[] parent = new int[n + 1];

            parent[root.getData()] = -1;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                Node node = queue.poll();

                for (Node child : node.getChildren()) {
                    parent[child.getData()] = node.getData();
                    queue.add(child);
                }
            }

            return parent;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node1.addChildren(node2);
        node1.addChildren(node3);

        node2.addChildren(node4);
        node2.addChildren(node5);
        node2.addChildren(node6);

        node3.addChildren(node7);

        node4.addChildren(node8);
        node4.addChildren(node9);

        UsingBinaryLifting solution = new UsingBinaryLifting(node1, 9);
        System.out.println(solution.kthAncestor(8, 2));
    }

}
