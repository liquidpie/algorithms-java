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
        Map<Graph.Vertex, Graph.Edge> forest = new HashMap<>();
        bfs(g, s, new HashSet<>(), forest);
    }

    private static void bfs(Graph g, Graph.Vertex s, Set<Graph.Vertex> visited, Map<Graph.Vertex, Graph.Edge> forest) {
        if (g == null || s == null)
            return;
        Queue<Graph.Vertex> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);

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
}
