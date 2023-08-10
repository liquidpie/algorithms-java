package com.vivek.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Number of Connected Components in an Undirected Graph
 *
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 * Example 1:
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 *
 * Example 2:
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 *
 * Complexity Analysis:
 *
 * Time complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
 * Space Complexity: O(V), since an extra visited array of size V is required.
 *
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph
 */
public class CountOfConnectedComponentsInUndirectedGraph {

    public static void main(String[] args) {
        {
            int n = 5;
            int[][] edges = {{0,1}, {1,2}, {3,4}};
            System.out.println(countConnectedComponents(n, edges));
        }
        {
            int n = 5;
            int[][] edges = {{0,1}, {1,2}, {2,3}, {3,4}};
            System.out.println(countConnectedComponents(n, edges));
        }
    }

    static int countConnectedComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0)
            return 0;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(graph, i, visited);
            }
        }
        return count;
    }

    private static void dfs(List<List<Integer>> graph, int source, boolean[] visited) {
        visited[source] = true;

        for (int neighbor : graph.get(source)) {
            if (!visited[neighbor])
                dfs(graph, neighbor, visited);
        }
    }

}
