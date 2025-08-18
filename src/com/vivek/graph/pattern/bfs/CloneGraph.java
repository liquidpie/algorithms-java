package com.vivek.graph.pattern.bfs;

import java.util.*;

/**
 * 133. Clone Graph
 *
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * Test case format:
 *
 * For simplicity, each node's value is the same as the node's index (1-indexed).
 * For example, the first node with val == 1, the second node with val == 2, and so on.
 * The graph is represented in the test case using an adjacency list.
 *
 * An adjacency list is a collection of unordered lists used to represent a finite graph.
 * Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1.
 * You must return the copy of the given node as a reference to the cloned graph.
 *
 * Example 1:
 *
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 *
 * Example 2:
 *
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 *
 * Example 3:
 *
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 *
 * Intuition ::
 *
 * If you want to copy a graph, you start by creating a copy of original node, followed by creating a copy for each subsequent node.
 * While creating a copy we shouldnt forget to check the neighbours list for each node as well.
 * The approach goes like this -
 *
 *     Copy the first node
 *     Copy all subsequent nodes
 *     If two nodes are neighbours in original graph, we also connect them in the new copy(not the original node!)
 *
 * Approach
 *
 * This can be solved by either BFS or DFS. I personally went with the BFS approach as it was more intuitive to me.
 * But really, it doesn't matter what you take because we eventually need to visit all nodes and edges.
 * What we need:
 *
 *     A HashMap to keep track of which nodes we've copied (also prevents infinite loops!)
 *     A Queue for BFS traversal
 *
 * Complexity
 *
 *     Time complexity:
 *     O(V + E) - This cannot be reduced in any way because we MUST visit each vertex and edge at least once
 *
 *     Space complexity:
 *     O(V) - for storing the HashMap and Queue
 *
 * Reference:
 * https://leetcode.com/problems/clone-graph
 */
public class CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        Map<Node, Node> cloneMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        cloneMap.put(node, new Node(node.val));
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node neighbor : current.neighbors) {
                if (!cloneMap.containsKey(neighbor)) {
                    cloneMap.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                cloneMap.get(current).neighbors.add(cloneMap.get(neighbor));
            }
        }

        return cloneMap.get(node);
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}
