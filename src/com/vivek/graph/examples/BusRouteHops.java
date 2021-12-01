package com.vivek.graph.examples;

import java.util.*;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 *
 *     For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 *
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 *
 * Example 1:
 *
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 *
 * Example 2:
 *
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 *
 * Solution:
 * - For each of the bus stop, we maintain all the buses (bus routes) that go through it. To do that, we use a HashMap,
 *   where bus stop number is the key and all the buses (bus routes) that go through it are added to an ArrayList.
 * - We use BFS, where we process elements in a level-wise manner. We add the Start bus stop in the queue.
 * - Next, when we enter the while loop, we add all the bus stops that are reachable by all the bus routes that go via the Start.
 * - Thus, if we have the input as [[1, 2, 7], [3, 6, 7]] and Start as 6, then upon processing bus stop 6, we would add bus stops 3 and 7.
 * - With this approach, all the bus stops at a given level, are "equal distance" from the start node,
 *   in terms of number of buses that need to be changed.
 * - To avoid loops, we also maintain a HashSet that stores the buses that we have already visited.
 * Note that while in this approach, we use each stop for doing BFS, one could also consider each bus (route) also for BFS.
 *
 * Time Complexity: should be similar to that of a regular BFS.
 * Basically, we are visiting each bus-route once and in doing so, we are also visiting each bus stop for a given route.
 * So, it should be O(V+E), where V is the total number of bus routes and E is the total number of bus stops.
 *
 * Reference: https://leetcode.com/problems/bus-routes/discuss/122712/Simple-Java-Solution-using-BFS
 */
public class BusRouteHops {

    static int minHops(int[][] routes, int src, int dest) {
        if (src == dest)
            return 0;

        int n = routes.length;
        Map<Integer, Set<Integer>> busesAtStop = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                Set<Integer> buses = busesAtStop.getOrDefault(routes[i][j], new HashSet<>());
                buses.add(i);
                busesAtStop.put(routes[i][j], buses);
            }
        }

        Set<Integer> visited = new HashSet<>(); // it maintains what buses are visited
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

        int minHops = -1;

        while (!queue.isEmpty()) {
            int len = queue.size();
            ++minHops;
            for (int i = 0; i < len; i++) {
                int stop = queue.poll();

                for (int bus : busesAtStop.get(stop)) {
                    if (visited.contains(bus)) continue;
                    visited.add(bus);
                    for (int j = 0; j < routes[bus].length; j++) {
                        if (routes[bus][j] == dest)
                            return minHops;
                        queue.add(routes[bus][j]);
                    }
                }
            }

        }

        return minHops;
    }

    public static void main(String[] args) {
        int[][] routes = { {1,2,7}, {3,6,7} };
        int src = 1;
        int dest = 6;
        System.out.println(minHops(routes, src, dest));
    }

}
