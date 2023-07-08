package com.vivek.graph.examples;

import com.vivek.graph.TopologicalSortBFS;

import java.util.List;

/**
 * Tasks Scheduling
 *
 * There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be
 * completed before it can be scheduled. Given the number of tasks and a list of prerequisite pairs, find out if it
 * is possible to schedule all the tasks.
 *
 * Example:
 * Input: Tasks=3, Prerequisites=[0, 1], [1, 2]
 * Output: true
 * Explanation: To execute task '1', task '0' needs to finish first. Similarly, task '1' needs to
 * finish before '2' can be scheduled. A possible scheduling of tasks is: [0, 1, 2]
 *
 * Solution
 * This problem is asking us to find out if it is possible to find a topological ordering of the given tasks. The tasks
 * are equivalent to the vertices and the prerequisites are the edges.
 *
 * Variation: https://leetcode.com/problems/course-schedule/
 */
public class TaskScheduling {

    static boolean isSchedulingPossible(int vertices, int[][] edges) {
        List<Integer> topoSort = TopologicalSortBFS.topoSort(vertices, edges);
        return topoSort.size() == vertices;
    }

    /**
     * Find the ordering of tasks we should pick to finish all tasks.
     *
     * Solution: return topological sorting
     */
    static List<Integer> schedulingOrder(int vertices, int[][] edges) {
        return TopologicalSortBFS.topoSort(vertices, edges);
    }

    public static void main(String[] args) {
        int vertices = 3;
        int[][] edges = { { 0, 1 }, { 1, 2 } };
        boolean result = isSchedulingPossible(vertices, edges);
        System.out.println("Tasks execution possible: " + result);

        System.out.println(schedulingOrder(vertices, edges));
    }

}
