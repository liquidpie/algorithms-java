package com.vivek.graph;

import java.util.*;

/**
 * Created by VJaiswal on 21/06/18.
 */
public class TopologicalSort {

    /**
     * Topological Sorting is a linear ordering of the vertices of a graph
     * in such a way that every edge between two vertices x and y,
     * the vertex x comes before vertex y.
     *
     * It is basically ordering or arranging the vertices in a linear fashion of a directed acyclic graph.
     * It is also used for Job scheduling and Data Serialization.
     *
     * Important Points:
     *
     * Topological ordering is possible only if the graph does not contain any cycles.
     * If it contains any cycle, topological sequence is not possible.
     *
     * Every Directed Acyclic Graph will contain at least one topological sequence.
     *
     * Complexity : O(n + m) where n is number of nodes & m is number of edges
     *
     * http://interactivepython.org/runestone/static/pythonds/Graphs/TopologicalSorting.html
     *
     * https://www.geeksforgeeks.org/topological-sorting-using-departure-time-of-vertex/
     *
     */

    private static int departureTime = -1;

    static void topoSort(Graph g) {
        if (g == null)
            return;

        List<Graph.Vertex> vertices = g.getVertices();
        int n = vertices.size();
        Set<Graph.Vertex> visited = new HashSet<>();
        Graph.Vertex[] topology = new Graph.Vertex[n];

        for (Graph.Vertex v : vertices) {
            if (!visited.contains(v)) {
                dfs(g, v, visited, topology);
            }
        }

        // traverse topology in descending order
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(topology[i] + " ");
        }

    }

    private static void dfs(Graph g, Graph.Vertex s, Set<Graph.Vertex> visited, Graph.Vertex[] topology) {
        if (s == null)
            return;

        visited.add(s);

        for (Graph.Edge e : s.getAdjacentEdges()) {
            Graph.Vertex v = e.opposite(s);
            if (!visited.contains(v)) {
                dfs(g, v, visited, topology);
            }
        }

        topology[++departureTime] = s;
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

        topoSort(graph);

    }
}
