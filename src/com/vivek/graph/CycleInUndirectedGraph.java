package com.vivek.graph;

import java.util.HashSet;
import java.util.Set;

/**
 Like directed graphs, we can use DFS to detect cycle in an undirected graph in O(V+E) time.
 We do a DFS traversal of the given graph. For every visited vertex ‘v’,
 if there is an adjacent ‘u’ such that u is already visited and u is not parent of v,
 then there is a cycle in graph. If we don’t find such an adjacent edge for any vertex,
 we say that there is no cycle.
 */
public class CycleInUndirectedGraph {

    static boolean isCyclic(Graph g) {
        if (g == null)
            return false;

        Set<Graph.Vertex> visited = new HashSet<>();
        for (Graph.Vertex v : g.getVertices()) {
            if (!visited.contains(v)) {
                if (_isCyclicUtil(v, visited, null))
                    return true;
            }
        }
        return false;
    }

    private static boolean _isCyclicUtil(Graph.Vertex s, Set<Graph.Vertex> visited, Graph.Vertex parent) {
        if (s == null)
            return false;

        visited.add(s);

        for (Graph.Edge e : s.getAdjacentEdges()) {
            Graph.Vertex v = e.opposite(s);
            // If an adjacent is not visited, then recur for that
            // adjacent
            if (!visited.contains(v)) {
                if (_isCyclicUtil(v, visited, s))
                    return true;
            } else if (v != parent) {
                // If an adjacent is visited and not parent of current
                // vertex, then there is a cycle.
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println(isCyclic(graph));

    }

}
