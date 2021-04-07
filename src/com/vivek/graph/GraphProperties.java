package com.vivek.graph;

import java.util.*;

public class GraphProperties {

    /**
     * Returns an ordered list of edges comprising the directed path from u to v
     */
    static void constructPath(Graph g, Graph.Vertex u, Graph.Vertex v, Map<Graph.Vertex, Graph.Edge> forest) {
        List<Graph.Edge> path  = new LinkedList<>();
        if (forest.get(v) != null) {
            Graph.Vertex walk = v;
            while (walk != u) {
                Graph.Edge e = forest.get(walk);
                walk = e.opposite(walk);
                path.add(0, e);
            }
        }
        System.out.println(path);
    }

    /**
     * We can use the basic DFS method to determine whether a graph is connected. In
     * the case of an undirected graph, we simply start a depth-first search at an arbitrary
     * vertex and then test whether visited.size( ) equals n at the conclusion.
     */
    static boolean testConnectivityForUndirectedGraph(Graph g) {
        Set<Graph.Vertex> visited = new HashSet<>();
        Map<Graph.Vertex, Graph.Edge> forest = new LinkedHashMap<>();
        DFS.dfs(g, g.getVertex(1), visited, forest);

        return visited.size() == g.getVertices().size();
    }

    /**
     * For directed graph, G, we may wish to test whether it is strongly connected, that
     * is, whether for every pair of vertices u and v, both u reaches v and v reaches u.
     * It can be done by two depth-first searches
     *
     * We begin by performing a depth-first search of our directed graph G starting at
     * an arbitrary vertex s. If there is any vertex of G that is not visited by this traversal,
     * and is not reachable from s, then the graph is not strongly connected. If this first
     * depth-first search visits each vertex of G, we need to then check whether s is reachable
     * from all other vertices.
     *
     * A better approach than making a new graph is to reimplement
     * a version of the DFS method that loops through all incoming edges to the current
     * vertex, rather than all outgoing edges.
     */
    static boolean stronglyConnectedForDirectedGraph(DirectedGraph g) {
        Set<DirectedGraph.Vertex> visited = new HashSet<>();
        Map<DirectedGraph.Vertex, DirectedGraph.Edge> forest = new LinkedHashMap<>();

        DFS.dfs(g, g.getVertex(1), visited, forest);

        if (visited.size() != g.getVertices().size()) {
            return false;
        }

        visited.clear();
        forest.clear();

        DFS.reverseDfs(g, g.getVertex(2), visited, forest);
        return visited.size() == g.getVertices().size();
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

        Map<Graph.Vertex, Graph.Edge> forest = DFS.dfs(graph, graph.getVertex(1));

        constructPath(graph, graph.getVertex(1), graph.getVertex(5), forest);

        System.out.println(testConnectivityForUndirectedGraph(graph));

        DirectedGraph directedGraph = new DirectedGraph();
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(2, 1);

        System.out.println(stronglyConnectedForDirectedGraph(directedGraph));

    }

}
