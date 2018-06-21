package com.vivek.graph;

import java.util.*;

/**
 * Created by VJaiswal on 21/06/18.
 */
public class BFS {

    /**
     * Complexity : O(n + m) where n is number of nodes & m is number of edges
     * @param g
     * @param s
     */
    static void bfs(Graph g, Graph.Vertex s) {
        Map<Graph.Vertex, Graph.Edge> forest = new LinkedHashMap<>();
        bfs(g, s, new HashSet<>(), forest);

        forest.keySet().forEach(System.out::println);
    }

    private static void bfs(Graph g, Graph.Vertex s, Set<Graph.Vertex> visited, Map<Graph.Vertex, Graph.Edge> forest) {
        if (g == null || s == null)
            return;
        Queue<Graph.Vertex> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);
        forest.put(s, null);

        while (!queue.isEmpty()) {
            Graph.Vertex u = queue.poll();

            for (Graph.Edge e : u.getAdjacentEdges()) {
                Graph.Vertex v = e.opposite(u);
                if (!visited.contains(v)) {
                    visited.add(v);
                    forest.put(v, e);
                    queue.add(v);
                }
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

        Graph.Vertex start = graph.getVertex(1);
        bfs(graph, start);

    }
}
