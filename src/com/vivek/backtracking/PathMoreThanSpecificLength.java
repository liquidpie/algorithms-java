package com.vivek.backtracking;

import com.vivek.graph.Graph;

import java.util.Map;

/**
 * Todo
 *
 * Find if there is a path of more than k length from a source
 *
 * Given a graph, a source vertex in the graph and a number k, find if there is a simple path (without any cycle) starting from given source and ending at any other vertex.
 *
 * Example:
 * Input  : Source s = 0, k = 58
 * Output : True
 * There exists a simple path 0 -> 7 -> 1
 * -> 2 -> 8 -> 6 -> 5 -> 3 -> 4
 * Which has a total distance of 60 km which
 * is more than 58.
 *
 * Input  : Source s = 0, k = 62
 * Output : False
 *
 * In the above graph, the longest simple
 * path has distance 61 (0 -> 7 -> 1-> 2
 *  -> 3 -> 4 -> 5-> 6 -> 8, so output
 * should be false for any input greater
 * than 61.
 *
 *
 * One important thing to note is, simply doing BFS or DFS and picking the longest edge at every step would not work. The reason is, a shorter edge can produce longer path due to higher weight edges connected through it.
 * The idea is to use Backtracking. We start from given source, explore all paths from current vertex. We keep track of current distance from source. If distance becomes more than k, we return true. If a path doesn’t produces more than k distance, we backtrack.
 * How do we make sure that the path is simple and we don’t loop in a cycle? The idea is to keep track of current path vertices in an array. Whenever we add a vertex to path, we check if it already exists or not in current path. If it exists, we ignore the edge.
 *
 * Reference: https://www.geeksforgeeks.org/find-if-there-is-a-path-of-more-than-k-length-from-a-source/
 *
 */
public class PathMoreThanSpecificLength {

    static boolean pathMoreThanK(Graph graph, int src, int k) {

        boolean[] path = new boolean[graph.getVertices().size()];

        path[src] = true;

        return pathMoreThanKUtil(graph, src, k, path);

    }

    private static boolean pathMoreThanKUtil(Graph graph, int src, int k, boolean[] path) {

        if (k <= 0)
            return true;

        for (Map.Entry<Graph.Vertex, Graph.Edge> entry : graph.getVertex(src).getAdjacencyMap().entrySet()) {
            // Get adjacent vertex and weight of edge
            int v = entry.getKey().getId();
            int w = entry.getValue().getWeight();

            // If vertex v is already there in path, then there is a cycle (we ignore this edge)
            if (path[v])
                continue;

            // If weight is more than k, return true
            if (w >= k)
                return true;

            // Else add this vertex to path
            path[v] = true;

            // If this adjacent can provide a path longer than k, return true
            if (pathMoreThanKUtil(graph, v, k - w, path))
                return true;

            // backtrack
            path[v] = false;
        }

        // If no adjacent could produce longer path, return false
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        if (pathMoreThanK(graph, 0,62))
            System.out.println("YES");
        else
            System.out.println("NO");

        if (pathMoreThanK(graph, 0, 60))
            System.out.println("YES");
        else
            System.out.println("NO");

    }

}
