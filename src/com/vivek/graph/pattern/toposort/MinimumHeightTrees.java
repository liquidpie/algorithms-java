package com.vivek.graph.pattern.toposort;

import java.util.*;

/**
 * Minimum Height Trees
 *
 * A tree is an undirected graph in which any two vertices are connected by exactly one path.
 * In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that
 * there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root.
 * When you select a node x as the root, the result tree has height h.
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 *               OR
 *
 * We are given an undirected graph that has characteristics of a k-ary tree. I
 * n such a graph, we can choose any node as the root to make a k-ary tree.
 * The root (or the tree) with the minimum height will be called Minimum Height Tree (MHT).
 * There can be multiple MHTs for a graph. In this problem, we need to find all those roots which give us MHTs.
 * Write a method to find all MHTs of the given graph and return a list of their roots.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * Example 1:
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 *
 * Example 2:
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *
 * Solution: Grokking the Coding Interview (Problem Challenge 2)
 *
 * From the above-mentioned examples, we can clearly see that any leaf node (i.e., node with only one edge) can never
 * give us an MHT because its adjacent non-leaf nodes will always give an MHT with a smaller height.
 * All the adjacent non-leaf nodes will consider the leaf node as a subtree. Let’s understand this with another example.
 * Suppose we have a tree with root ‘M’ and height ‘5’. Now, if we take another node, say ‘P’, and make the ‘M’ tree as its subtree,
 * then the height of the overall tree with root ‘P’ will be ‘6’ (=5+1). Now, this whole tree can be considered a graph,
 * where ‘P’ is a leaf as it has only one edge (connection with ‘M’).
 * This clearly shows that the leaf node (‘P’) gives us a tree of height ‘6’ whereas its adjacent non-leaf node (‘M’)
 * gives us a tree with smaller height ‘5’ - since ‘P’ will be a child of ‘M’.
 *
 * This gives us a strategy to find MHTs. Since leaves can’t give us MHT, we can remove them from the graph and
 * remove their edges too. Once we remove the leaves, we will have new leaves. Since these new leaves can’t give us MHT,
 * we will repeat the process and remove them from the graph too. We will prune the leaves until we are left with one or
 * two nodes which will be our answer and the roots for MHTs.
 *
 * We can implement the above process using the topological sort.
 * Any node with only one edge (i.e., a leaf) can be our source and, in a stepwise fashion,
 * we can remove all sources from the graph to find new sources. We will repeat this process until we are left with one or two nodes in the graph,
 * which will be our answer.
 *
 * Time complexity #
 * In step ‘d’, each node can become a source only once and each edge will be accessed and removed once.
 * Therefore, the time complexity of the above algorithm will be O(V + E), where ‘V’ is the total nodes and ‘E’ is the total number of the edges.
 *
 * Space complexity #
 * The space complexity will be O(V + E), since we are storing all of the edges for each node in an adjacency list.
 *
 * https://leetcode.com/problems/minimum-height-trees/
 */
public class MinimumHeightTrees {

    static List<Integer> findMinHeightTrees(int nodes, int[][] edges) {
        List<Integer> minHeightTrees = new ArrayList<>();
        if (nodes <= 0)
            return minHeightTrees;

        // with only one node, since its in-degree will be 0, therefore, we need to handle it separately
        if (nodes == 1) {
            minHeightTrees.add(0);
            return minHeightTrees;
        }

        // a. Initialize the graph
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < nodes; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        // b. Build the graph
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            // since this is an undirected graph, therefore, add a link for both the nodes
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
            // increment the in-degrees of both the nodes
            inDegree.put(n1, inDegree.get(n1) + 1);
            inDegree.put(n2, inDegree.get(n2) + 1);
        }

        // c. Find all leaves i.e., all nodes with only 1 in-degree
        Queue<Integer> leaves = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 1)
                leaves.add(entry.getKey());
        }

        // d. Remove leaves level by level and subtract each leave's children's in-degrees.
        // Repeat this until we are left with 1 or 2 nodes, which will be our answer.
        // Any node that has already been a leaf cannot be the root of a minimum height tree, because
        // its adjacent non-leaf node will always be a better candidate.
        int totalNodes = nodes;
        while (totalNodes > 2) {
            int leavesSize = leaves.size();
            totalNodes -= leavesSize;
            for (int i = 0; i < leavesSize; i++) {
                int s = leaves.poll();
                for (int child : graph.get(s)) {
                    inDegree.put(child, inDegree.get(child) - 1);
                    if (inDegree.get(child) == 1)  // if the child has become a leaf
                        leaves.add(child);
                }
            }
        }

        minHeightTrees.addAll(leaves);
        return minHeightTrees;
    }

    public static void main(String[] args) {
        List<Integer> result = findMinHeightTrees(5,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 2, 4 } });
        System.out.println("Roots of MHTs: " + result);
        result = findMinHeightTrees(4,
                new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 2, 3 } });
        System.out.println("Roots of MHTs: " + result);
        result = findMinHeightTrees(4,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 } });
        System.out.println("Roots of MHTs: " + result);
    }

}
