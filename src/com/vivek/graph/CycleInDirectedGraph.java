package com.vivek.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by VJaiswal on 26/06/18.
 */
public class CycleInDirectedGraph {

    enum Color { WHITE, GRAY, BLACK}

    /**
     The idea is to do DFS of given graph and while doing traversal, assign one of the below three colors to every vertex.

     WHITE : Vertex is not processed yet.  Initially
     all vertices are WHITE.

     GRAY : Vertex is being processed (DFS for this
     vertex has started, but not finished which means
     that all descendants (ind DFS tree) of this vertex
     are not processed yet (or this vertex is in function
     call stack)

     BLACK : Vertex and all its descendants are
     processed.

     While doing DFS, if we encounter an edge from current
     vertex to a GRAY vertex, then this edge is back edge
     and hence there is a cycle.
     */
    static boolean isCyclicUsingColors(Graph g) {
        if (g == null)
            return false;

        // Initialize all vertices with WHITE color
        Map<Graph.Vertex, Color> vertexColorMap = new HashMap<>();
        for (Graph.Vertex u : g.getVertices()) {
            vertexColorMap.put(u, Color.WHITE);
        }

        // Do DFS traversal
        for (Graph.Vertex u : g.getVertices()) {
            if (vertexColorMap.get(u) == Color.WHITE) {
                if (_dfs(u, vertexColorMap))
                    return true;
            }
        }
        return false;
    }

    private static boolean _dfs(Graph.Vertex s, Map<Graph.Vertex, Color> vertexColorMap) {
        if (s == null)
            return false;

        vertexColorMap.put(s, Color.GRAY);

        for (Graph.Edge e : s.getAdjacentEdges()) {
            Graph.Vertex v = e.opposite(s);
            if (vertexColorMap.get(v) == Color.GRAY)
                return true;
            // If v is not processed and there is a back
            // edge in subtree rooted with v
            if (vertexColorMap.get(v) == Color.WHITE
                    && _dfs(v, vertexColorMap))
                return true;
        }

        vertexColorMap.put(s, Color.BLACK);
        return false;
    }

    /**
     Depth First Traversal can be used to detect a cycle in a Graph. DFS for a connected graph produces a tree.
     There is a cycle in a graph only if there is a back edge present in the graph.
     A back edge is an edge that is from a node to itself (self-loop) or
     one of its ancestor in the tree produced by DFS.

     To detect a back edge, we can keep track of vertices currently in recursion stack of function for DFS traversal.
     If we reach a vertex that is already in the recursion stack, then there is a cycle in the tree.
     The edge that connects current vertex to the vertex in the recursion stack is a back edge.
     */
    static boolean isCyclicUsingRecursionStack(Graph g) {
        if (g == null)
            return false;

        Set<Graph.Vertex> visited = new HashSet<>();
        Set<Graph.Vertex> recStack = new HashSet<>();

        for (Graph.Vertex v : g.getVertices()) {
            if (!visited.contains(v)) {
                if (_cyclicUtil(v, visited, recStack))
                    return true;
            }
        }
        return false;
    }

    private static boolean _cyclicUtil(Graph.Vertex s, Set<Graph.Vertex> visited, Set<Graph.Vertex> recStack) {
        if (s == null)
            return false;

        if (recStack.contains(s))
            return true;
        if (visited.contains(s))
            return false;

        visited.add(s);
        recStack.add(s);

        for (Graph.Edge e : s.getAdjacentEdges()) {
            Graph.Vertex v = e.opposite(s);
            if (_cyclicUtil(v, visited, recStack))
                return true;
        }

        recStack.remove(s);

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

        System.out.println(isCyclicUsingRecursionStack(graph));

    }
}
