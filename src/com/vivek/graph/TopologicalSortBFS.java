package com.vivek.graph;

import java.util.*;

/**
 * Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering of its vertices such
 * that for every directed edge (U, V) from vertex U to vertex V , U comes before V in the ordering.
 *
 * Few fundamental concepts related to topological sort:
 * 1. Source: Any node that has no incoming edge and has only outgoing edges is called a source.
 * 2. Sink: Any node that has only incoming edges and no outgoing edge is called a sink.
 * 3. So, we can say that a topological ordering starts with one of the sources and ends at one of the sinks.
 * 4. A topological ordering is possible only when the graph has no directed cycles, i.e. if the graph is a
 * Directed Acyclic Graph (DAG).
 *
 * To find the topological sort of a graph we can traverse the graph in a Breadth First Search (BFS) way. We
 * will start with all the sources, and in a stepwise fashion, save all sources to a sorted list. We will then remove
 * all sources and their edges from the graph. After the removal of the edges, we will have new sources, so we
 * will repeat the above process until all vertices are visited.
 *
 * Time Complexity: O(V+E)
 * Space Complexity: O(V+E)
 */
public class TopologicalSortBFS {

    static List<Integer> topoSort(int v, int[][] edges) {
        List<Integer> topoOrder = new ArrayList<>();

        // Initialize and Build graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < v; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int parent = edge[0], child = edge[1];
            inDegree.put(child, inDegree.get(child) + 1);
            graph.get(parent).add(child);
        }

        // Find all sources
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }

        // BFS
        while (!sources.isEmpty()) {
            int s = sources.poll();
            topoOrder.add(s);
            for (int u : graph.get(s)) {
                inDegree.put(u, inDegree.get(u) - 1);
                if (inDegree.get(u) == 0) {
                    sources.add(u);
                }
            }
        }

        // topological sort is not possible if the graph has cycle
        if (topoOrder.size() != v) {
            return new ArrayList<>();
        }

        return topoOrder;
    }

    public static void main(String[] args) {
        int[][] edges = { {3,2}, {3,0}, {2,0}, {2,1} };
        List<Integer> result = topoSort(4, edges);
        System.out.println(result);
    }

}
