package com.vivek.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Shortest Path in Directed Acyclic Graph
 *
 * Given a Weighted Directed Acyclic Graph and a source vertex in the graph, find the shortest paths from given source to all other vertices.
 * For a general weighted graph, we can calculate single source shortest distances in O(VE) time using Bellman–Ford Algorithm.
 * For a graph with no negative weights, we can do better and calculate single source shortest distances in O(E + VLogV)
 * time using Dijkstra’s algorithm. Can we do even better for Directed Acyclic Graph (DAG)? We can calculate single source
 * shortest distances in O(V+E) time for DAGs. The idea is to use Topological Sorting.
 *
 * We initialize distances to all vertices as infinite and distance to source as 0, then we find a topological sorting
 * of the graph. Topological Sorting of a graph represents a linear ordering of the graph
 *
 * Once we have topological order (or linear representation), we one by one process all vertices in topological order.
 * For every vertex being processed, we update distances of its adjacent using distance of current vertex.
 *
 *     Following is complete algorithm for finding shortest distances.
 *
 *         Initialize dist[] = {INF, INF, ….} and dist[s] = 0 where s is the source vertex.
 *         Create a topological order of all vertices.
 *         Do following for every vertex u in topological order.
 *         ………..Do following for every adjacent vertex v of u
 *         ………………if (dist[v] > dist[u] + weight(u, v))
 *         ………………………dist[v] = dist[u] + weight(u, v)
 *
 *
 * Time Complexity: Time complexity of topological sorting is O(V+E). After finding topological order,
 *                  the algorithm process all vertices and for every vertex, it runs a loop for all adjacent vertices.
 *                  Total adjacent vertices in a graph is O(E). So the inner loop runs O(V+E) times.
 *                  Therefore, overall time complexity of this algorithm is O(V+E).
 *
 * Auxiliary Space : O(V+E)
 *
 * https://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
 */
public class ShortestPathDAG {

    static List<List<Node>> adjList;
    static int numVertices;
    static final int INF = Integer.MAX_VALUE;

    static class Node {
        int v;  // vertex
        int wt; // weight
        public Node(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }
    }

    static void buildGraph() {
        adjList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    static void addEdge(int u, int v, int wt) {
        adjList.get(u).add(new Node(v, wt));
    }

    static void topoSort(int u, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        // Iterating for all the adjacent nodes of u
        for (Node node : adjList.get(u)) {
            if (!visited[node.v]) {
                topoSort(node.v, visited, stack);
            }
        }
        stack.push(u);
    }

    static void printShortestPath(int src) {
        Stack<Integer> stack = new Stack<>();
        int[] dist = new int[numVertices];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i])
                topoSort(i, visited, stack);
        }

        while (!stack.isEmpty()) {
            int u = stack.pop();
            // If shortest distance from src to u is
            // not infinity i.e. it is reachable.
            if (dist[u] != INF) {
                for (Node node : adjList.get(u)) {
                    // If distance of src->v is greater than
                    // distance of src->u + u->v then update
                    // the value as shown.
                    if (dist[node.v] > dist[u] + node.wt) {
                        dist[node.v] = dist[u] + node.wt;
                    }
                }
            }
        }

        for (int i = 0; i < numVertices; i++) {
            System.out.print(dist[i] != INF ? dist[i] + " " : "INF ");
        }
    }

    public static void main(String[] args) {
        numVertices = 6;
        buildGraph();
        addEdge(0, 1, 5);
        addEdge(0, 2, 3);
        addEdge(1, 3, 6);
        addEdge(1, 2, 2);
        addEdge(2, 4, 4);
        addEdge(2, 5, 2);
        addEdge(2, 3, 7);
        addEdge(3, 4, -1);
        addEdge(4, 5, -2);

        // Find the shortest path from a vertex (here 1)
        printShortestPath(1);
    }

}
