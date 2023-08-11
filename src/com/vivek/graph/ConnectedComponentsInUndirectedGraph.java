package com.vivek.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Count Number of connected components in an undirected graph
 *
 * Given an undirected graph g, the task is to print the number of connected components in the graph.
 *
 * Examples:
 *
 * Input:
 *  1-5
 *  0 4    3
 *  |/
 *  2
 *
 * Output: 3
 * There are three connected components:
 * 1 – 5, 0 – 2 – 4 and 3
 *
 * Approach :
 *
 * DFS visit all the connected vertices of the given vertex.
 *
 * When iterating over all vertices, whenever we see unvisited node, it is because it was not visited by DFS done on vertices so far.
 *
 * That means it is not connected to any previous nodes visited so far i.e it was not part of previous components.
 *
 * Hence this node belongs to new component.
 *
 * This means, before visiting this node, we just finished visiting all nodes previous component and that component is now complete.
 *
 * So we need to increment component counter as we completed a component.
 *
 * The idea is to use a variable count to store the number of connected components and do the following steps:
 *
 * Initialize all vertices as unvisited.
 * For all the vertices check if a vertex has not been visited, then perform DFS on that vertex and increment the variable count by 1.
 *
 * https://www.geeksforgeeks.org/program-to-count-number-of-connected-components-in-an-undirected-graph/
 */
public class ConnectedComponentsInUndirectedGraph {

    public static void main(String[] args) {
        SimpleGraph graph = new SimpleGraph(6);
        graph.addEdge(1, 5);
        graph.addEdge(0, 2);
        graph.addEdge(2, 4);

        List<List<Integer> > connectedComponents = dfs(graph);

        int index = 1;
        for (List<Integer> component : connectedComponents) {
            System.out.print("Component " + index++ + ": ");
            for (Integer i : component)
                System.out.print(i + " ");
            System.out.println();
        }
    }

    static List<List<Integer>> dfs(SimpleGraph graph) {
        List<List<Integer>> components = new ArrayList<>();
        int v = graph.vertices;
        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            List<Integer> component = new ArrayList<>();
            if (!visited[i]) {
                dfs(i, visited, component, graph);
                components.add(component);
            }
        }
        System.out.println(components.size());
        return components;
    }

    static void dfs(int s, boolean[] visited, List<Integer> component, SimpleGraph graph) {
        visited[s] = true;
        component.add(s);

        for (int u : graph.adjacencyList.get(s)) {
            if (!visited[u]) {
                dfs(u, visited, component, graph);
            }
        }
    }

}
