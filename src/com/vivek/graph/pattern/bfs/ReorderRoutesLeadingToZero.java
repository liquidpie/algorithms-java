package com.vivek.graph.pattern.bfs;

import java.util.*;

/**
 * 1466. Reorder Routes to Make All Paths Lead to the City Zero
 *
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities
 * (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 *
 *
 * Example 1:
 *
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 *
 * Example 2:
 *
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 *
 * Example 3:
 *
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 *
 * Solution:
 * Make a weighted graph with weight of all nodes in opposite direction as 1.
 * Do a BFS/DFS from root and calculate the cost of traversing the whole graph
 *
 * - make all the the path bidirectional between two nodes without affecting connections.
 * - start traversing from "0" using bfs.
 * - if path does not exists in connections it mean you have change direction of that path, So add one to the result variable.
 * - After completing BFS you will have the answer.
 *
 * Reference:
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero
 */
public class ReorderRoutesLeadingToZero {

    public static void main(String[] args) {
        int[][] connections = {{0,1},{1,3},{2,3},{4,0},{4,5}};
        ReorderRoutesLeadingToZero helper = new ReorderRoutesLeadingToZero();
        System.out.println(helper.minReorder(6, connections));
    }

    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : connections) {
            graph.get(edge[0]).add(new Edge(edge[1], 1));
            graph.get(edge[1]).add(new Edge(edge[0], 0));
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Edge edge : graph.get(node)) {
                if (!visited[edge.node]) {
                    visited[edge.node] = true;
                    queue.add(edge.node);
                    count += edge.wt;
                }
            }
        }

        return count;
    }

    // below is the dfs version of the above bfs version
    private int dfs(int node, Map<Integer, List<Edge>> graph, boolean[] visited) {
        int count = 0;
        for (Edge edge : graph.get(node)) {
            if (!visited[edge.node]) {
                visited[edge.node] = true;
                count += edge.wt;
                count += dfs(edge.node, graph, visited);
            }
        }
        return count;
    }

    static class Edge {
        int node;
        int wt;

        Edge(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }

}
