package com.vivek.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A bipartite graph also called a bi-graph, is a set of graph vertices, i.e, points where multiple lines meet,
 * decomposed into two disjoint sets, meaning they have no element in common, such that no two graph vertices within the same set are adjacent.
 *
 *      Adjacent nodes are any two nodes that are connected by an edge.
 *
 * A bipartite graph is a special case of a k-partite graph with k = 2.
 *
 * Bipartite graphs are equivalent to two-colorable graphs i.e., coloring of the vertices using two colors in such a way that vertices of the same color are never adjacent along an edge.
 * All Acyclic graphs are bipartite. A cyclic graph is bipartite if all its cycles are of even length.
 *
 * A Bipartite Graph is a graph whose vertices can be divided into two independent sets, U and V such that every edge (u, v)
 * either connects a vertex from U to V or a vertex from V to U. In other words, for every edge (u, v), either u belongs to U and v to V,
 * or u belongs to V and v to U. We can also say that there is no edge that connects vertices of same set.
 *
 * Approach:
 *
 * Following is a simple algorithm to find out whether a given graph is Birpartite or not using Breadth First Search (BFS).
 * 1. Assign RED color to the source vertex (putting into set U).
 * 2. Color all the neighbors with BLUE color (putting into set V).
 * 3. Color all neighbor’s neighbor with RED color (putting into set U).
 * 4. This way, assign color to all vertices such that it satisfies all the constraints of m way coloring problem where m = 2.
 * 5. While assigning colors, if we find a neighbor which is colored with same color as current vertex, then the graph cannot be colored with 2 vertices (or graph is not Bipartite)
 *
 * Reference: https://www.geeksforgeeks.org/bipartite-graph
 */
public class BipartiteGraph {

    static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // Create a color array to store colors assigned to all vertices. Vertex/ number is used as
        // index in this array. The value '-1' of colorArr[i] is used to indicate that no color
        // is assigned to vertex 'i'. The value 1 is used to indicate first color is assigned and value
        // 0 indicates second color is assigned.
        int[] colorArray = new int[n];
        Arrays.fill(colorArray, -1);

        for (int i = 0; i < n; i++) {
            if (colorArray[i] == -1) {
                if (!isBipartite(graph, i, n, colorArray))
                    return false;
            }
        }
        return true;
    }

    private static boolean isBipartite(int[][] graph, int src, int n, int[] colorArray) {

        colorArray[src] = 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            // Return false if there is a self-loop
            if (graph[u][u] == 1)
                return false;

            for (int v = 0; v < n; v++) {
                // An edge from u to v exists and destination v is not colored
                if (graph[u][v] == 1 && colorArray[v] == -1) {
                    // Assign alternate color to this adjacent v of u
                    colorArray[v] = 1 - colorArray[u];
                    queue.add(v);
                }
                // An edge from u to v exists and destination v is colored with same color as u
                else if (graph[u][v] == 1 && colorArray[v] == colorArray[u]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[][] graph = {
                { 0, 1, 0, 1 },
                { 1, 0, 1, 0 },
                { 0, 1, 0, 1 },
                { 1, 0, 1, 0 }
        };
        System.out.println(isBipartite(graph));
    }

}
