package com.vivek.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Check if a given graph is tree or not
 *
 * An undirected graph is tree if it has following properties.
 * 1) There is no cycle.
 * 2) The graph is connected.
 * For an undirected graph we can either use BFS or DFS to detect above two properties.
 *
 * How to detect cycle in an undirected graph?
 * We can either use BFS or DFS. For every visited vertex ‘v’,
 * if there is an adjacent ‘u’ such that u is already visited and u is not parent of v, then there is a cycle in graph.
 * If we don’t find such an adjacent for any vertex, we say that there is no cycle.
 *
 * How to check for connectivity?
 * Since the graph is undirected, we can start BFS or DFS from any vertex and check if all vertices are reachable or not.
 * If all vertices are reachable, then graph is connected, otherwise not.
 *
 * Reference: https://www.geeksforgeeks.org/check-given-graph-tree
 */
public class CheckGraphIsTree {

    static boolean isTree(Graph graph) {
        List<Graph.Vertex> vertices = graph.getVertices();
        Set<Integer> visited = new HashSet<>();

        // check if graph has no cycle
        if (isCyclic(graph, visited)) {
            return false;
        }

        // check if graph is connected
        for (Graph.Vertex vertex : vertices) {
            if (!visited.contains(vertex.getId())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCyclic(Graph graph, Set<Integer> visited) {
        for (Graph.Vertex vertex : graph.getVertices()) {
            if (!visited.contains(vertex.getId())) {
                if (isCyclicUtil(vertex, null, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCyclicUtil(Graph.Vertex s, Graph.Vertex parent, Set<Integer> visited) {
        visited.add(s.getId());

        for (Graph.Edge e : s.getAdjacentEdges()) {
            Graph.Vertex v = e.opposite(s);
            if (!visited.contains(v.getId())) {
                if (isCyclicUtil(v, s, visited)) {
                    return true;
                }
            } else if (parent!= null && v != parent) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        System.out.println(isTree(graph));
    }

}
