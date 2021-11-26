package com.vivek.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Find if there is a path between two vertices in a directed graph
 *
 * Approach: Either Breadth First Search (BFS) or Depth First Search (DFS) can be used to find path between two vertices.
 * Take the first vertex as source in BFS (or DFS), follow the standard BFS (or DFS).
 * If the second vertex is found in our traversal, then return true else return false.
 *
 * Reference: https://www.geeksforgeeks.org/find-if-there-is-a-path-between-two-vertices-in-a-given-graph
 */
public class CheckPathExists {

    static boolean isReachable(Graph graph, int u, int v) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(u);
        visited.add(u);

        while (!queue.isEmpty()) {
            int s = queue.poll();
            for (int t : graph.adjacency[s]) {
                if (t == v)
                    return true;
                if (!visited.contains(t)) {
                    visited.add(t);
                    queue.add(t);
                }
            }
        }

        return false;
    }

    static class Graph {
        private int v;
        private LinkedList<Integer>[] adjacency;

        public Graph(int v) {
            this.v = v;
            this.adjacency = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adjacency[i] = new LinkedList<>();
            }
        }

        void addEdge(int u, int v) {
            adjacency[u].add(v);
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

        int u = 1;
        int v = 3;
        System.out.println(isReachable(g, u, v));
    }

}
