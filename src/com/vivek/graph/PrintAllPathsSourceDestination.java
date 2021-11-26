package com.vivek.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Print all paths from a given source to a destination
 *
 * Given a directed graph, a source vertex ‘s’ and a destination vertex ‘d’, print all paths from given ‘s’ to ‘d’.
 *
 * DFS Approach:
 *     - The idea is to do Depth First Traversal of given directed graph.
 *     - Start the DFS traversal from source.
 *     - Keep storing the visited vertices in an array or HashMap say ‘path[]’.
 *     - If the destination vertex is reached, print contents of path[].
 *     - The important thing is to mark current vertices in the path[] as visited also so that
 *       the traversal doesn’t go in a cycle.
 *
 * BFS Approach:
 *
 *     - create a queue which will store path(s) of type vector
 *     - initialise the queue with first path starting from src
 *
 *     - Now run a loop till queue is not empty
 *          get the frontmost path from queue
 *          check if the lastnode of this path is destination
 *              if true then print the path
 *          run a loop for all the vertices connected to the
 *          current vertex i.e. lastnode extracted from path
 *             if the vertex is not visited in current path
 *                a) create a new path from earlier path and
 *                    append this vertex
 *                b) insert this new path to queue
 *
 * Reference: https://www.geeksforgeeks.org/find-paths-given-source-destination
 */
public class PrintAllPathsSourceDestination {

    static void printAllPathsUsingBFS(Graph graph, int s, int d) {
        // Create a queue which stores the paths
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(s);
        queue.add(path);

        while (!queue.isEmpty()) {
            path = queue.poll();
            int lastElt = path.get(path.size() - 1);
            if (lastElt == d) {
                System.out.println(path);
            }
            for (int u : graph.adjacency[lastElt]) {
                if (!path.contains(u)) {
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(u);
                    queue.add(newPath);
                }
            }
        }
    }

    /**
     *  Time Complexity: O(V^V)
     *      The time complexity is polynomial. From each vertex there are v vertices that can be visited from current vertex.
     *  Auxiliary space: O(V^V)
     *      To store the paths V^V space is needed.
     */
    static void printAllPathsUsingDFS(Graph graph, int s, int d) {
        boolean[] visited = new boolean[graph.v];
        List<Integer> pathList = new ArrayList<>();
        pathList.add(s);
        printAllPathsUsingDfsUtil(graph, s, d, visited, pathList);
    }

    private static void printAllPathsUsingDfsUtil(Graph graph, Integer s, Integer d, boolean[] visited, List<Integer> localPathList) {
        if (s.equals(d)) {
            System.out.println(localPathList);
            return;
        }

        visited[s] = true;

        for (Integer u : graph.adjacency[s]) {
            if (!visited[u]) {
                localPathList.add(u);
                printAllPathsUsingDfsUtil(graph, u, d, visited, localPathList);
                // remove current node in path
                localPathList.remove(u);
            }
        }
        // Mark the current node as not visited
        visited[s] = false;
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
        g.addEdge(0, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 3);

        int s = 2;
        int d = 3;

        printAllPathsUsingBFS(g, s, d);
    }

}
