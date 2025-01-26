package com.vivek.graph;

import java.util.*;

/**
 * Suppose we have input data describing relationships between parents and children over multiple generations.
 * The data is formatted as a list of (parent, child) pairs, where each individual is assigned a unique integer identifier.
 * For example, in this diagram, 3 is a child of 1 and 2, and 5 is a child of 4:
 *
 * 		  10
 * 		  /
 * 1       2             4
 *   \    /           /    \
 *      3            5        8
 * 	   \        /   \        \
 * 	     \    /       \        \
 * 		    6          7        9
 *
 * **Find the Earliest Ancestor**
 *
 * Write a function that, for a given individual in our dataset, returns their earliest known ancestor
 * – the one at the farthest distance from the input individual.
 * If there is more than one ancestor tied for “earliest”, return any one of them. If the input individual has no parents,
 * the function should return null (or -1).
 *
 * Sample input and output:
 * parentChildPairs = [[1, 3], [2, 3], [3, 6], [5, 6], [5, 7], [4, 5], [4, 8], [8, 9], [10,2]];
 * 8 => 4
 * 7 => 4
 * 6 => 10
 * Common Clarifications
 * * Any member of the “earliest” generation of the input individual’s ancestors is an acceptable result.
 * * No person will appear in the ancestor list twice (via two different relationship paths).
 * * There are no cycles in the input.
 *
 * Complexity
 *     Time Complexity: O(V+E)O(V+E), where VV is the number of unique nodes and EE is the number of relationships in the input.
 *     Space Complexity: O(V)O(V) for the childParentList and queue.
 *
 * Reference:
 * https://leetcode.com/discuss/interview-question/5128771/WayFair-round-1-or-Banglore-or-May-2024/
 */
public class EarliestAncestor {

    static int findEarliestAncestor(int[][] parentChildPairs, int node) {
        // Step 1: Build the graph
        Map<Integer, List<Integer>> childParentList = new HashMap<>();

        for (int[] pair : parentChildPairs) {
            int child = pair[1];
            int parent = pair[0];
            childParentList.computeIfAbsent(child, k -> new ArrayList<>()).add(parent);
        }

        // Step 2: Handle the case where the node has no parents
        if (!childParentList.containsKey(node)) {
            return -1; // No parents, no ancestors
        }

        // Step 3: BFS to find the earliest ancestor
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        int earliestAncestor = node;
        while (!queue.isEmpty()) {
            int current = queue.poll();

            // If the current node has parents, add them to the queue
            if (childParentList.containsKey(current)) {
                queue.addAll(childParentList.get(current));
                earliestAncestor = current; // Update to the latest node in the queue
            }
        }
        // If the earliest ancestor is the input node, return -1
        return earliestAncestor != node ? earliestAncestor : -1;
    }

    public static void main(String[] args) {
        int[][] parentChildPairs = { {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 9}, {10,2} };
        System.out.println(findEarliestAncestor(parentChildPairs, 8)); // 4
        System.out.println(findEarliestAncestor(parentChildPairs, 7)); // 4
        System.out.println(findEarliestAncestor(parentChildPairs, 6)); // 10
        System.out.println(findEarliestAncestor(parentChildPairs, 1)); // null (no parents)
        System.out.println(findEarliestAncestor(parentChildPairs, 9)); // 4 (earliest ancestor in the earliest generation)
    }

}
