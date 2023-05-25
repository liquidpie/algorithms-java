package com.vivek.graph.examples;

/**
 * Find the minimum cost to reach destination using a train
 *
 * There are N stations on route of a train. The train goes from station 0 to N-1.
 * The ticket cost for all pair of stations (i, j) is given where j is greater than i.
 * Find the minimum cost to reach the destination.
 * Consider the following example:
 *
 * Input:
 * cost[N][N] = { {0, 15, 80, 90},
 *               {INF, 0, 40, 50},
 *               {INF, INF, 0, 70},
 *               {INF, INF, INF, 0}
 *              };
 * There are 4 stations and cost[i][j] indicates cost to reach j
 * from i. The entries where j < i are meaningless.
 *
 * Output:
 * The minimum cost is 65
 * The minimum cost can be obtained by first going to station 1
 * from 0. Then from station 1 to station 3.
 *
 * The minimum cost to reach N-1 from 0 can be recursively written as following:
 *
 * minCost(0, N-1) = MIN { cost[0][n-1],
 *                         cost[0][1] + minCost(1, N-1),
 *                         minCost(0, 2) + minCost(2, N-1),
 *                         ........,
 *                         minCost(0, N-2) + cost[N-2][n-1] }
 *
 * We can solve this problem using O(N) extra space and O(N2) time. The idea is based on the fact that
 * given input matrix is a Directed Acyclic Graph (DAG). The shortest path in DAG can be calculated using the approach discussed in below post.
 * -> Shortest Path in Directed Acyclic Graph <-
 *
 * We need to do less work here compared to above mentioned post as we know topological sorting of the graph.
 * The topological sorting of vertices here is 0, 1, …, N-1. Following is the idea once topological sorting is known.
 * The idea in below code is to first calculate min cost for station 1, then for station 2, and so on. These costs are stored in an array dist[0…N-1].
 *
 *     The min cost for station 0 is 0, i.e., dist[0] = 0
 *     The min cost for station 1 is cost[0][1], i.e., dist[1] = cost[0][1]
 *     The min cost for station 2 is minimum of following two.
 *         dist[0] + cost[0][2]
 *         dist[1] + cost[1][2]
 *     The min cost for station 3 is minimum of following three.
 *         dist[0] + cost[0][3]
 *         dist[1] + cost[1][3]
 *         dist[2] + cost[2][3]
 *
 * Similarly, dist[4], dist[5], … dist[N-1] are calculated.
 *
 * Time Complexity: O(n2) (two nested for loop)
 * Auxiliary Space: O(n)
 *
 * https://www.geeksforgeeks.org/find-the-minimum-cost-to-reach-a-destination-where-every-station-is-connected-in-one-direction/
 */
public class MinCostToReachDestination {

    static int INF = Integer.MAX_VALUE;
    static int N;

    static int minCost(int[][] cost) {
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = INF;
        }
        dist[0] = 0;

        // Go through every station and check if using it
        // as an intermediate station gives better path
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (dist[j] > dist[i] + cost[i][j]) {
                    dist[j] = dist[i] + cost[i][j];
                }
            }
        }
        
        return dist[N - 1];
    }

    public static void main(String[] args) {
        N = 4;
        int[][] cost = {
                {0, 15, 80, 90},
                {INF, 0, 40, 50},
                {INF, INF, 0, 70},
                {INF, INF, INF, 0}
        };
        // The Minimum cost to reach station 4 is 65
        System.out.println(minCost(cost));
    }

}
