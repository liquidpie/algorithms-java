package com.vivek.graph;

import java.util.*;

public class DFS {

    /**
     * driver DFS method
     */
    static Map<Graph.Vertex, Graph.Edge> dfs(Graph g, Graph.Vertex s) {
        if (g == null)
            return null;
        Set<Graph.Vertex> visited = new HashSet<>();
        Map<Graph.Vertex, Graph.Edge> forest = new LinkedHashMap<>();

        dfs(g, s, visited, forest);
        return forest;
    }

    /**
     * Base DFS method for both undirected and simple directed graph
     */
    static void dfs(Graph g, Graph.Vertex u, Set<Graph.Vertex> visited, Map<Graph.Vertex, Graph.Edge> forest) {
        if (u == null)
            return;

        visited.add(u);

        for (Graph.Edge e : u.getAdjacentEdges()) {
            Graph.Vertex v = e.opposite(u);

            if (!visited.contains(v)) {
                forest.put(v, e);
                dfs(g, v, visited, forest);
            }
        }
    }

    /**
     * DFS method for directed graph
     */
    static void dfs(DirectedGraph g, DirectedGraph.Vertex u, Set<DirectedGraph.Vertex> visited, Map<DirectedGraph.Vertex, DirectedGraph.Edge> forest) {
        if (u == null)
            return;

        visited.add(u);

        for (DirectedGraph.Edge e : u.getOutgoingEdges()) {
            DirectedGraph.Vertex v = e.opposite(u);

            if (!visited.contains(v)) {
                forest.put(v, e);
                dfs(g, v, visited, forest);
            }
        }
    }

    /**
     * Reverse DFS method for directed graph using incoming edges instead of outgoing edges
     */
    static void reverseDfs(DirectedGraph g, DirectedGraph.Vertex u, Set<DirectedGraph.Vertex> visited, Map<DirectedGraph.Vertex, DirectedGraph.Edge> forest) {
        if (u == null)
            return;

        visited.add(u);

        for (DirectedGraph.Edge e : u.getIncomingEdges()) {
            DirectedGraph.Vertex v = e.opposite(u);

            if (!visited.contains(v)) {
                forest.put(v, e);
                reverseDfs(g, v, visited, forest);
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

        System.out.println(dfs(graph, graph.getVertex(1)));
    }

}
