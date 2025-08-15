package com.vivek.graph.examples;

import java.util.*;

/**
 * Given an undirected graph, find all the pairs for Spine Leaf (as in Spine Leaf architecture)
 * <p>
 * <img src="./images/spine_leaf.png" alt="Example Diagram">
 * </p>
 *
 * Algorithm Steps:
 *
 * Iterate through each node and find nodes with identical neighbor sets (potential spines)
 * Group nodes with same neighbors - these become spine candidates
 * Validate the spine-leaf relationship:
 *
 * Each leaf must connect to ALL spines in the group
 * Each spine should only connect to leaves in this cluster (and possibly other spines)
 *
 *
 * Create SpineLeaf clusters for valid groups
 *
 * Key Logic:
 *
 * Spine identification: Nodes that have identical adjacency lists are potential spines
 * Leaf validation: For each neighbor of the spines, verify it connects back to ALL spines
 * Cluster validation: Ensure spines don't have connections outside their designated leaf set
 * Minimum requirement: At least 2 spines are needed to form a valid cluster
 *
 * Cluster 1: SpineLeaf{spines=[2, 3], leaves=[6, 7, 8, 9]}
 * Cluster 2: SpineLeaf{spines=[4, 5], leaves=[10, 11, 12, 13, 14, 15]}
 *
 *
 *
 * Asked in Forward Networks
 */
public class FindSpineLeaf {

    List<SpineLeaf> findSpineLeafClusters(Graph g) {
        List<SpineLeaf> clusters = new ArrayList<>();
        Set<Integer> processedNodes = new HashSet<>();

        for (int i = 0; i < g.numVertices(); i++) {
            if (processedNodes.contains(i))
                continue;

            Set<Integer> neighbors = g.adjacentTo(i);
            if (neighbors.isEmpty()) {
                processedNodes.add(i);
                continue;
            }

            // Find all nodes that have the exact same neighbors as node i
            Set<Integer> potentialSpines = new HashSet<>();
            potentialSpines.add(i);

            for (int j = 0; j < g.numVertices(); j++) {
                if (j == i || processedNodes.contains(j))
                    continue;

                Set<Integer> jNeighbors = g.adjacentTo(j);
                if (neighbors.equals(jNeighbors))
                    potentialSpines.add(j);
            }

            // Verify that all neighbors connect back to all potential spines
            Set<Integer> validLeaves = new HashSet<>();
            for (int neighbor : neighbors) {
                Set<Integer> neighborConnections = g.adjacentTo(neighbor);
                if (neighborConnections.equals(potentialSpines))
                    validLeaves.add(neighbor);
            }

            // Only create a cluster if we have valid leaves that connect to all spines
            if (potentialSpines.size() > 1 && validLeaves.size() > potentialSpines.size()) { // Need at least 2 spines
                clusters.add(new SpineLeaf(potentialSpines, validLeaves));
                processedNodes.addAll(potentialSpines);
            }
        }

        return clusters;
    }


    static class SpineLeaf {
        private final Set<Integer> spines;
        private final Set<Integer> leaves;

        public SpineLeaf(Set<Integer> spines, Set<Integer> leaves) {
            this.spines = new HashSet<>(spines);
            this.leaves = new HashSet<>(leaves);
        }

        @Override
        public String toString() {
            return "{spines=" + spines +
                    ", leaves=" + leaves +
                    '}';
        }
    }

    static class Graph {

        private final Map<Integer, Set<Integer>> adj = new HashMap<>();

        void addEdge(int u, int v) {
            adj.computeIfAbsent(u, k -> new HashSet<>()).add(v);
            adj.computeIfAbsent(v, k -> new HashSet<>()).add(u);
        }

        int numVertices() {
            return adj.size();
        }

        Set<Integer> adjacentTo(int u) {
            return adj.getOrDefault(u, Collections.emptySet());
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        // 0 -> [2, 3, 4, 5]
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(0, 5);
        // Cluster A: 2,3 -> [6,7,8,9]
        g.addEdge(2, 6);
        g.addEdge(2, 7);
        g.addEdge(2, 8);
        g.addEdge(2, 9);
        g.addEdge(3, 6);
        g.addEdge(3, 7);
        g.addEdge(3, 8);
        g.addEdge(3, 9);
        // Cluster B: 4,5 -> [10,11,12,13,14,15]
        g.addEdge(4, 10);
        g.addEdge(4, 11);
        g.addEdge(4, 12);
        g.addEdge(4, 13);
        g.addEdge(4, 14);
        g.addEdge(4, 15);
        g.addEdge(5, 10);
        g.addEdge(5, 11);
        g.addEdge(5, 12);
        g.addEdge(5, 13);
        g.addEdge(5, 14);
        g.addEdge(5, 15);

        FindSpineLeaf helper = new FindSpineLeaf();
        System.out.println(helper.findSpineLeafClusters(g));
    }

}
