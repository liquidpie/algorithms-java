package com.vivek.graph;

import java.util.*;

/**
 * Suppose we have input data describing relationships between parents and children over multiple generations.
 * The data is formatted as a list of (parent, child) pairs, where each individual is assigned a unique integer identifier.
 *
 * For example, in this diagram, 3 is a child of 1 and 2, and 5 is a child of 4:
 *
 *       10
 *      /
 * 1   2     4
 *  \ /     / \
 *   3     5   8
 *    \   / \   \
 *     \ /   \   \
 *      6     7   9
 * Find the Earliest Ancestor
 *
 * Write a function that, for a given individual in our dataset, returns their earliest known ancestor
 * – the one at the farthest distance from the input individual.
 * If there is more than one ancestor tied for “earliest”, return any one of them.
 * If the input individual has no parents, the function should return null (or -1).
 *
 * Sample input and output:
 *
 * parentChildPairs = [[1, 3], [2, 3], [3, 6], [5, 6], [5, 7], [4, 5], [4, 8], [8, 9], [10,2]];
 *
 * 8 => 4
 *
 * 7 => 4
 *
 * 6 => 10
 *
 * Solution: Use BFS
 * - Build graph in child->parent order.
 * - Run BFS traversal
 *
 */
public class FarthestNodeFromSeed {

    public static void main(String[] args) {
        int[][] parentChildPairs = {
                {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7},
                {4, 5}, {4, 8}, {8, 9}, {10, 2}
        };

        int individual = 10;
        int earliestAncestor = findEarliestAncestor(parentChildPairs, individual);
        System.out.println(earliestAncestor);
    }

    public static int findEarliestAncestor(int[][] parentChildPairs, int seed) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // build graph
        for (int[] pair : parentChildPairs) {
            int parent = pair[0];
            int child = pair[1];
            graph.putIfAbsent(child, new ArrayList<>());
            graph.get(child).add(parent);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(seed);
        int earliestAncestor = -1;

        if (!graph.containsKey(seed))
            return earliestAncestor;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (graph.containsKey(u)) {
                for (int v : graph.get(u)) {
                    queue.add(v);
                }
            }
            earliestAncestor = u;
        }
        return earliestAncestor;
    }

}
