package com.vivek.graph;

import java.util.*;

public class DFSComplete {

    /**
     * DFS Complete traverses all the nodes even we have disconnection.
     * If an initial call to DFS fails to reach all vertices of a graph,
     * we can restart a new call to DFS at one of those unvisited vertices.
     *
     * Complexity : O(n + m) where n is number of nodes & m is number of edges
     */
    static void dfsComplete(Graph g) {
        if (g == null)
            return;

        List<Graph.Vertex> vertices = g.getVertices();
        Set<Graph.Vertex> visited = new HashSet<>();
        Map<Graph.Vertex, Graph.Edge> forest = new LinkedHashMap<>();

        for (Graph.Vertex v : vertices) {
            if (!visited.contains(v)) {
                visited.add(v);
                forest.put(v, null);
                dfs(g, v, visited, forest);
            }
        }

        forest.keySet().forEach(System.out::println);
    }

    private static void dfs(Graph g, Graph.Vertex s, Set<Graph.Vertex> visited, Map<Graph.Vertex, Graph.Edge> forest) {
        if (s == null)
            return;

        visited.add(s);

        for (Graph.Edge e : s.getAdjacentEdges()) {
            Graph.Vertex v = e.opposite(s);

            if (!visited.contains(v)) {
                forest.put(v, e);
                dfs(g, v, visited, forest);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        graph.addEdge(3, 5);
        graph.addEdge(2, 6);
        graph.addEdge(7, 6);

        dfsComplete(graph);

    }
}
