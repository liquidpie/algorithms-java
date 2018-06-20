package com.vivek.graph;

import java.util.*;

/**
 * Created by VJaiswal on 21/06/18.
 */
public class DFSComplete {

    /**
     * DFS Complete traverses all the nodes even we have
     * disconnection
     *
     * Complexity : O(n + m) where n is number of nodes & m is number of edges
     * @param g
     */
    static void dfsComplete(Graph g) {
        if (g == null)
            return;

        List<Graph.Vertex> vertices = g.getVertices();
        Set<Graph.Vertex> visited = new HashSet<>();
        Map<Graph.Vertex, Graph.Edge> forest = new HashMap<>();

        for (Graph.Vertex v : vertices) {
            if (!visited.contains(v))
                dfs(g, v, visited, forest);
        }
    }

    private static void dfs(Graph g, Graph.Vertex s, Set<Graph.Vertex> visited, Map<Graph.Vertex, Graph.Edge> forest) {
        if (s == null)
            return;

        visited.add(s);

        for (Graph.Edge e : s.getAdjacentEdges()) {
            Graph.Vertex v = e.opposite(s);

            if (!visited.contains(v)) {
                visited.add(v);
                forest.put(v, e);
                dfs(g, v, visited, forest);
            }
        }
    }
}
