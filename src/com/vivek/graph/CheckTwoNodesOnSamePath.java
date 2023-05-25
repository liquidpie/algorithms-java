package com.vivek.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Check if two nodes are on same path in a tree
 *
 * Given a tree (not necessarily a binary tree) and a number of queries such that every query takes two nodes of the tree as parameters.
 * For every query pair, find if two nodes are on the same path from the root to the bottom.
 *
 * For example, consider the below tree, if given queries are (1, 5), (1, 6), and (2, 6), then answers should be true, true, and false respectively.
 *
 *                   1
 *                /  |  \
 *              2    3   4
 *             /     |    \
 *           5       6     7
 *
 * It is obvious that the Depth First Search technique is to be used to solve the above problem, the main problem is how to respond to multiple queries fast. Here our graph is a tree which may have any number of children. Now DFS in a tree, if started from root node, proceeds in a depth search manner i.e. Suppose root has three children and those children have only one child with them so if DFS is started then it first visits the first child of root node then will go deep to the child of that node. The situation with a small tree can be shown as follows:
 *
 * The order of visiting the nodes will be – 1 2 5 3 6 4 7 .
 * Thus, other children nodes are visited later until completely one child is successfully visited till depth. To simplify this if we assume that we have a watch in our hand, and we start walking from the root in DFS manner.
 *
 * Intime – When we visit the node for the first time
 * Outtime- If we again visit the node later but there are no children unvisited we call it outtime,
 *
 * Note: Any node in its sub-tree will always have intime < its children (or children of children) because it is always visited first before children (due to DFS) and will have outtime > all nodes in its sub-tree because before noting the outtime it waits for all of its children to be marked visited.
 *
 * For any two nodes u, v if they are in the same path then,
 *
 * Intime[v] < Intime[u] and Outtime[v] > Outtime[u]
 *                  OR
 * Intime[u] < Intime[v] and Outtime[u ]> Outtime[v]
 *
 *     If a given pair of nodes follows any of the two conditions, then they are on the same root to the leaf path.
 *     Else not on the same path (If two nodes are on different paths it means that no one is in the subtree of each other).
 *
 * Pseudo Code
 *
 * We use a global variable time which will be incremented as dfs for a node begins and will also be incremented after
 *
 * DFS(v)
 *     increment timer
 *     Intime[v] = timer
 *     mark v as visited
 *     for all u that are children of v
 *               DFS(u)
 *     increment timer
 *     Outtime[v] = timer
 * end
 *
 * Time Complexity – O(n) for preprocessing and O(1) per query.
 *
 *
 * https://www.geeksforgeeks.org/check-if-two-nodes-are-on-same-path-in-a-tree/
 */
public class CheckTwoNodesOnSamePath {

    static int numVertices;
    static int MAX = 10001;
    static int timer = 0;
    static boolean[] visited = new boolean[MAX];
    static int[] inTime = new int[MAX];
    static int[] outTime = new int[MAX];

    static void dfs(List<Integer>[] graph, int s) {
        visited[s] = true;

        ++timer;
        inTime[s] = timer;

        for (int u : graph[s]) {
            if (!visited[u]) {
                dfs(graph, u);
            }
        }

        ++timer;
        outTime[s] = timer;
    }

    static boolean query(int u, int v) {
        return (inTime[u] < inTime[v] && outTime[u] > outTime[v]) ||
                (inTime[v] < inTime[u] && outTime[v] > outTime[u]);
    }

    public static void main(String[] args) {
        numVertices = 9;
        List<Integer>[] graph = new ArrayList[numVertices + 1];
        for (int i = 0; i < numVertices + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[1].add(2);
        graph[1].add(3);
        graph[3].add(6);
        graph[2].add(4);
        graph[2].add(5);
        graph[5].add(7);
        graph[5].add(8);
        graph[5].add(9);

        dfs(graph, 1);

        System.out.println(query(1, 5));
        System.out.println(query(2, 9));
        System.out.println(query(2, 6));
    }

}
