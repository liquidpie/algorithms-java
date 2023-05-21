package com.vivek.heap.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Cheapest Flights Within K Stops
 *
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
 * Example 1:
 *
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 *
 * Example 2:
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
 *
 * Example 3:
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph is shown above.
 * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 *
 *          * Approach: We store the flights info in a map. We can then use
 *          * a min heap to find the flight route with cheapest prices. We use
 *          * an array to keep track of the cost so far, source stop and stops
 *          * left.
 *          *
 *          * After that, we pop the element from the queue which is an array.
 *          * If the dst stop is reached then we return the cost. Otherwise,
 *          * we add the adjacent stops in the queue by updating the price
 *          * and the number of stops.
 *          *
 *          * Time Complexity: O(nlgn)
 *          * Space Complexity: O(n)
 *
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
public class CheapestFlightWithinKStops {

    public static void main(String[] args) {
        int[][] flights = {
                {0,1,100},
                {1,2,100},
                {2,0,100},
                {1,3,600},
                {2,3,200}
        };

        System.out.println(findCheapestPrice(4, flights, 0, 3, 1));
    }

    static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] flight : flights) {
            if (!prices.containsKey(flight[0])) {
                prices.put(flight[0], new HashMap<>());
            }
            prices.get(flight[0]).put(flight[1], flight[2]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]);
        pq.add(new int[] {0, src, k + 1});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst)
                return price;

            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int nextCity : adj.keySet()) {
                    pq.add(new int[] {price + adj.get(nextCity), nextCity, stops - 1});
                }
            }
        }

        return -1;
    }

}
