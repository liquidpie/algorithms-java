package com.vivek.graph;

import java.util.List;

/**
 * Weakly Connected Components in a Directed Graph
 *
 * Weakly Connected Graph:
 *
 * A directed graph ‘G = (V, E)’ is weakly connected if the underlying undirected graph Ĝ is connected.
 *
 *     The underlying undirected graph is the graph Ĝ = (V, Ê) where Ê represents the set of undirected edges that is
 *     obtained by removing the arrowheads from the directed edges and making them bidirectional in G.
 *
 * <img src="https://media.geeksforgeeks.org/wp-content/uploads/20210926153049/WCCCropped-660x287.jpg">
 *
 * The directed graph G above is weakly connected since its underlying undirected graph Ĝ is connected.
 *
 * Weakly Connected Component:
 *
 * Given a directed graph, a weakly connected component (WCC) is a subgraph of the original graph where all
 * vertices are connected to each other by some path, ignoring the direction of edges.
 *
 * <img src="https://media.geeksforgeeks.org/wp-content/uploads/20210927193505/WCC2Cropped-660x338.jpg">
 *
 * In the above directed graph, there are two weakly connected components:
 *   - [0, 1, 2, 3]
 *   - [4, 5]
 *
 * Algorithm to find Weakly Connected Component:
 *
 *  It uses the algorithm to find connected components of an undirected graph.
 *
 *     Construct the underlying undirected graph of the given directed graph.
 *     Find all the connected components of the undirected graph.
 *     The connected components of the undirected graph will be the weakly connected components of the directed graph.
 *
 * https://www.geeksforgeeks.org/find-weakly-connected-components-in-a-directed-graph/
 */
public class ConnectedComponentsWeaklyInDirectedGraph {

    public static void main(String[] args) {
        SimpleGraph directedGraph = new SimpleGraph(6);

        directedGraph.addEdge(0, 1);
        directedGraph.addEdge(0, 2);
        directedGraph.addEdge(3, 1);
        directedGraph.addEdge(3, 2);
        directedGraph.addEdge(4, 5);

        List<List<Integer> > weaklyConnectedComponents = weaklyConnectedComponents(directedGraph);

        int index = 1;
        for (List<Integer> component : weaklyConnectedComponents) {
            System.out.print("Component " + index++ + ": ");
            for (Integer i : component)
                System.out.print(i + " ");
            System.out.println();
        }
    }

    private static List<List<Integer>> weaklyConnectedComponents(SimpleGraph directedGraph) {
        // Step 1: Construct the underlying undirected graph
        SimpleGraph undirectedGraph = new SimpleGraph(directedGraph.vertices);
        for (int u = 0; u < directedGraph.vertices; u++) {
            for (int v : directedGraph.adjacencyList.get(u)) {
                undirectedGraph.addEdge(u, v);
                undirectedGraph.addEdge(v, u);
            }
        }

        // Step 2: Find the connected components of the undirected graph
        return ConnectedComponentsInUndirectedGraph.dfs(undirectedGraph);
    }

}
