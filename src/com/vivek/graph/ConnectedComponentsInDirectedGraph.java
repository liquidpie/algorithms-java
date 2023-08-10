package com.vivek.graph;

import java.util.*;

/**
 * Strongly connected components on Directed Graph
 *
 * For undirected graphs there is the notion of connected components, which you find by performing a DFS on the undirected graph.
 *
 * For directed graphs there is the notion of strongly connected components, for which multiple algorithms are available,
 * all slightly more complicated than a simple DFS.
 *
 *     A strongly connected component is the component of a directed graph that has a path from every vertex to every
 *     other vertex in that component. It can only be used in a directed graph.
 *
 *     <img src="https://cdn.programiz.com/sites/tutorial2program/files/scc-strongly-connected-components.png" width="400">
 *
 * Difference between Connected and Strongly Connected Components?
 *
 * Connectivity in a undirected graph refers to whether two vertices are reachable from each other or not.
 * Two vertices are said to be connected if there is path between them. Meanwhile Strongly Connected is applicable
 * only to directed graphs. A subgraph of a directed graph is considered to be an Strongly Connected Components (SCC)
 * if and only if for every pair of vertices A and B, there exists a path from A to B and a path from B to A.
 * https://www.geeksforgeeks.org/strongly-connected-components/
 *
 * Solution::
 * Kosaraju's Algorithm:
 *
 * Kosaraju's Algorithm is based on the depth-first search algorithm implemented twice.
 *
 * Three steps are involved.
 *
 *   1.Perform a depth first search on the whole graph.
 *
 *     Let us start from vertex-0, visit all of its child vertices, and mark the visited vertices as done.
 *     If a vertex leads to an already visited vertex, then push this vertex to the stack.
 *
 *     For example: Starting from vertex-0, go to vertex-1, vertex-2, and then to vertex-3. Vertex-3 leads to already
 *     visited vertex-0, so push the source vertex (ie. vertex-3) into the stack.
 *     <img src="https://cdn.programiz.com/sites/tutorial2program/files/scc-step-1.png" width="400">
 *
 *     Go to the previous vertex (vertex-2) and visit its child vertices i.e. vertex-4, vertex-5, vertex-6 and vertex-7
 *     sequentially. Since there is nowhere to go from vertex-7, push it into the stack.
 *     <img src="https://cdn.programiz.com/sites/tutorial2program/files/scc-step-2.png" width="400">
 *
 *     Go to the previous vertex (vertex-6) and visit its child vertices. But, all of its child vertices are visited,
 *     so push it into the stack.
 *     <img src="https://cdn.programiz.com/sites/tutorial2program/files/scc-step-3.png" width="400">
 *
 *     Similarly, a final stack is created.
 *     <img src="https://cdn.programiz.com/sites/tutorial2program/files/scc-step-4.png" width="400">
 *
 *   2. Reverse the original graph.
 *      <img src="https://cdn.programiz.com/sites/tutorial2program/files/scc-reversed-graph.png" width="400">
 *
 *   3.Perform depth-first search on the reversed graph.
 *
 *     Start from the top vertex of the stack. Traverse through all of its child vertices.
 *     Once the already visited vertex is reached, one strongly connected component is formed.
 *
 *     For example: Pop vertex-0 from the stack. Starting from vertex-0, traverse through its child vertices
 *     (vertex-0, vertex-1, vertex-2, vertex-3 in sequence) and mark them as visited. The child of vertex-3 is already visited,
 *     so these visited vertices form one strongly connected component.
 *     <img src="https://cdn.programiz.com/sites/tutorial2program/files/scc-reversed-step-1.png" width="400">
 *
 *     Go to the stack and pop the top vertex if already visited. Otherwise, choose the top vertex from the stack and
 *     traverse through its child vertices as presented above.
 *     <img src="https://cdn.programiz.com/sites/tutorial2program/files/reversed%20step-2_0.png" width="400">
 *
 *     <img src="https://cdn.programiz.com/sites/tutorial2program/files/reversed%20step-3_0.png" width="400">
 *
 *   4. Thus, the strongly connected components are:
 *     <img src="https://cdn.programiz.com/sites/tutorial2program/files/scc-final-graph.png" width="400">
 *
 *   Kosaraju's Algorithm Complexity: Kosaraju's algorithm runs in linear time i.e. O(V+E).
 *
 *   Strongly Connected Components Applications:
 *     1. Vehicle routing applications
 *     2. Maps
 *     3. Model-checking in formal verification
 *
 * https://www.programiz.com/dsa/strongly-connected-components
 */
public class ConnectedComponentsInDirectedGraph {

    public static void main(String[] args) {
        Graph g = new Graph(8);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 0);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);
        g.addEdge(6, 7);

        System.out.println("Strongly Connected Components:");
        g.printSCC();
    }

    static class Graph {
        private final int V;

        private final List<List<Integer>> adj;

        Graph(int s) {
            V = s;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int s, int d) {
            adj.get(s).add(d);
        }

        void dfs(int source, boolean[] visited) {
            visited[source] = true;
            System.out.print(source + " ");

            for (int neighbor : adj.get(source)) {
                if (!visited[neighbor])
                    dfs(neighbor, visited);
            }
        }

        Graph transpose() {
            Graph g = new Graph(V);
            for (int s = 0; s < V; s++) {
                for (int d : adj.get(s)) {
                    g.addEdge(d, s);
                }
            }
            return g;
        }

        void fillOrder(int s, boolean[] visited, Stack<Integer> stack) {
            visited[s] = true;

            for (int neighbor : adj.get(s)) {
                if (!visited[neighbor]) {
                    fillOrder(neighbor, visited, stack);
                }
            }
            stack.push(s);
        }

        void printSCC() {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[V];

            for (int i = 0; i < V; i++)
                if (!visited[i])
                    fillOrder(i, visited, stack);

            Graph gr = transpose();

            for (int i = 0; i < V; i++)
                visited[i] = false;

            while (!stack.empty()) {
                int s = stack.pop();

                if (!visited[s]) {
                    gr.dfs(s, visited);
                    System.out.println();
                }
            }
        }
    }


}
