package com.vivek.graph.examples;

import java.util.*;

/**
 * Network Delay Time
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 *
 * Example 1:
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 *
 * Example 2:
 *
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 *
 * Example 3:
 *
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 *
 *          * Approach: We start by storing the network delays in an adjacency map.
 *          * We will store the delay times in an array for each of the nodes. After
 *          * that, we will keep on iterating through the adjacency map and update the
 *          * delayTimes array. The maximum value in the array will give the total time
 *          * to propogate the signal throughout the network.
 *          *
 *          * Time Complexity: O(M + N) where M is the size of times array
 *          * Space Complexity: O(M + N) where M is the size of times array
 *
 * https://leetcode.com/problems/network-delay-time/
 */
public class NetworkDelayTime {

    public static void main(String[] args) {
        int[][] times = {
                {2,1,1}, {2,3,1}, {3,4,1}
        };

        System.out.println(networkDelayTime(times, 4, 2));
    }

    static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> delays = new HashMap<>();
        for (int[] time :  times) {
            if (!delays.containsKey(time[0])) {
                delays.put(time[0], new HashMap<>());
            }
            delays.get(time[0]).put(time[1], time[2]);
        }

        int[] delayTimes = new int[n + 1];
        Arrays.fill(delayTimes, Integer.MAX_VALUE);
        delayTimes[0] = 0;
        delayTimes[k] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            Map<Integer, Integer> adj = delays.getOrDefault(node, new HashMap<>());
            for (int u : adj.keySet()) {
                if (delayTimes[u] > delayTimes[node] + adj.get(u)) {
                    delayTimes[u] = delayTimes[node] + adj.get(u);
                    queue.add(u);
                }
            }
        }

        int max = 0;
        for (int i : delayTimes) {
            max = Math.max(max, i);
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }

}
