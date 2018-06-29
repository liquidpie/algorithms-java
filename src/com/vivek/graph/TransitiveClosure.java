package com.vivek.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a directed graph, find out if a vertex v is reachable from another vertex u for all vertex pairs (u, v)
 * in the given graph. Here reachable mean that there is a path from vertex u to v.
 * The reach-ability matrix is called transitive closure of a graph.
 *

 (0)------->(3)
 |          /|\
 |           |
 |           |
 \|/         |
 (1)------->(2)

 Transitive closure of the given graph
 1 1 1 1
 0 1 1 1
 0 0 1 1
 0 0 0 1


 1. Create a matrix tc[V][V] that would finally have transitive closure of given graph.
 Initialize all entries of tc[][] as 0.
 2. Call DFS for every node of graph to mark reachable vertices in tc[][].
 In recursive calls to DFS, we don’t call DFS for an adjacent vertex if it is already marked as reachable in tc[][].


 https://www.geeksforgeeks.org/transitive-closure-of-a-graph-using-dfs/

 */
public class TransitiveClosure {

    static int[][] transitiveClosure(Graph g) {
        int n = g.n;
        int[][] transitiveClosure = new int[n][n];

        for (int i = 0; i < n; i++) {
            _dfsUtil(i, i, transitiveClosure, g);
        }

        return transitiveClosure;
    }

    private static void _dfsUtil(int s, int u, int[][] transitiveClosure, Graph g) {
        // Mark reachability from s to u as true.
        transitiveClosure[s][u] = 1;

        // Find all the vertices reachable through u
        for (int v : g.getAdjacentVertices(u)) {
            if (transitiveClosure[s][v] == 0)
                _dfsUtil(s, v, transitiveClosure, g);
        }
    }


    private static class Graph {
        final int n;
        int[][] adjacencyMatrix;

        Graph(int n) {
            this.n = n;
            adjacencyMatrix = new int[n][n];
        }

        void addEdge(int u, int v) {
            adjacencyMatrix[u][v] = 1;
        }

        List<Integer> getAdjacentVertices(int s) {
            List<Integer> adjacentVertices = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (adjacencyMatrix[s][i] == 1)
                    adjacentVertices.add(i);
            }
            return adjacentVertices;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        int[][] out = transitiveClosure(g);
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(out[i]));
        }
    }

}
