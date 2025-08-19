package com.vivek.graph.pattern.toposort;

import java.util.*;

/**
 * 207. Course Schedule
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that you must take course bi first if you want to take course ai.
 *
 *     For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * Solution: Same as Task Scheduling
 *
 * Reference: https://leetcode.com/problems/course-schedule/description/
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> topo = new ArrayList<>();

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int i = 0 ; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            inDegree.put(edge[0], inDegree.get(edge[0]) + 1);
        }

        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0)
                q.add(entry.getKey());
        }

        while (!q.isEmpty()) {
            int s = q.poll();
            topo.add(s);
            for (int v : graph.get(s)) {
                inDegree.put(v, inDegree.get(v) - 1);
                if (inDegree.get(v) == 0)
                    q.add(v);
            }
        }

        return topo.size() == numCourses;
    }

}
