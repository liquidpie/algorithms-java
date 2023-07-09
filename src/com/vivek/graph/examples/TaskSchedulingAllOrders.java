package com.vivek.graph.examples;

import java.util.*;

/**
 * All Tasks Scheduling Orders
 *
 * There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be
 * completed before it can be scheduled. Given the number of tasks and a list of prerequisite pairs, write a
 * method to print all possible ordering of tasks meeting all prerequisites.
 *
 * Example:
 * Input: Tasks=4, Prerequisites=[3, 2], [3, 0], [2, 0], [2, 1]
 * Output:
 * 1) [3, 2, 0, 1]
 * 2) [3, 2, 1, 0]
 *
 * Solution:
 * We need to find all the topological orderings of the tasks.
 * At any stage, if we have more than one source available and since we can choose any source, therefore, in this
 * case, we will have multiple orderings of the tasks. We can use a recursive approach with <Backtracking> to
 * consider all sources at any step.
 *
 * Time and Space Complexity:
 * If we don’t have any prerequisites, all combinations of the tasks can represent a topological ordering. As we
 * know, that there can be N! combinations for ‘N’ numbers, therefore the time and space complexity of our
 * algorithm will be O(V! * E) where ‘V’ is the total number of tasks and ‘E’ is the total prerequisites. We need
 * the ‘E’ part because in each recursive call, at max, we remove (and add back) all the edges.
 */
public class TaskSchedulingAllOrders {

    static void printOrders(int v, int[][] edges) {
        List<Integer> sortedOrder = new ArrayList<>();

        // Build and initialize graph
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
            if (entry.getValue() == 0)
                sources.add(entry.getKey());
        }

        printAllTopoOrders(graph, inDegree, sources, sortedOrder);
    }

    private static void printAllTopoOrders(Map<Integer, List<Integer>> graph, Map<Integer, Integer> inDegree,
                                           Queue<Integer> sources, List<Integer> sortedOrder) {
        if (!sources.isEmpty()) {
            for (Integer s : sources) {
                sortedOrder.add(s);
                Queue<Integer> sourcesForNextCall = new LinkedList<>(sources);
                // only remove the current source, all other sources should remain in the queue for the next call
                sourcesForNextCall.remove(s);
                List<Integer> children = graph.get(s);
                for (int child : children) {
                    inDegree.put(child, inDegree.get(child) - 1);
                    if (inDegree.get(child) == 0) {
                        sourcesForNextCall.add(child); // save the new source for the next call
                    }
                }
                // recursive call to print other orderings from the remaining (and new) sources
                printAllTopoOrders(graph, inDegree, sourcesForNextCall, sortedOrder);

                // backtrack, remove the vertex from the sorted order and put all of its children back to consider
                // the next source instead of the current vertex
                sortedOrder.remove(s);
                for (int child : children) {
                    inDegree.put(child, inDegree.get(child) + 1);
                }
            }
        }

        if (sortedOrder.size() == inDegree.size()) {
            System.out.println(sortedOrder);
        }
    }

    public static void main(String[] args) {
        int v = 4;
        int[][] edges = { {3,2}, {3,0}, {2,0}, {2,1} };
        printOrders(v, edges);
    }

}
