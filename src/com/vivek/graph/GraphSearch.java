package com.vivek.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Find out whether there is a route between two nodes
 */
public class GraphSearch {

    /**
     * This problem can be solved by just simple graph traversal,
     * such as depth first search or breadth first search.
     * We start with one of the two nodes and, during traversal,
     * check if the other node is found. We should mark any node found in the
     * course of the algorithm as ‘already visited’ to avoid cycles and repetition of the nodes.
     */
    boolean search(Graph graph, Graph.Vertex start, Graph.Vertex end) {
        Set<Graph.Vertex> visitedSet = new HashSet<>();

        LinkedList<Graph.Vertex> q = new LinkedList<>();
        q.add(start);
        Graph.Vertex u;
        while (!q.isEmpty()) {
            u = q.removeFirst();

            if (u != null) {
                for (Graph.Vertex v : u.getAdjacentVertices()) {
                    if (v == end)
                        return true;
                    if (visitedSet.add(v)) {
                        q.add(v);
                    }
                }
                visitedSet.add(u);
            }
        }

        return false;
    }

}
