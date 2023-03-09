package com.vivek.dp;

/**
 * Calculate the minimum cost to reach the destination city from the source city
 *
 * Given an N × N matrix of non-negative integers, where each cell of the matrix (i, j) indicates the direct flight cost from the city i to city j.
 * Find the minimum cost to reach the destination city N-1 from the source city 0.
 *
 * For example,
 * Input: The cost matrix for 4 cities:
 *
 * [ 0    20  30  100 ]
 * [ 20   0   15  75  ]
 * [ 30   15  0   50  ]
 * [ 100  75  50  0   ]
 *
 * Output: The minimum cost is 80.
 *
 * The minimum cost path is:
 *
 * city 0 —> city 2 (cost = 30)
 * city 2 —> city 3 (cost = 50)
 *
 *
 * Input: The cost matrix for 5 cities:
 *
 * [ 0   25  20  10  105 ]
 * [ 20  0   15  80  80  ]
 * [ 30  15  0   70  90  ]
 * [ 10  10  50  0   100 ]
 * [ 40  50  5   10  0   ]
 *
 *
 * Output: The minimum cost is 100.
 *
 * The minimum cost path is:
 *
 * city 0 —> city 3 (cost = 10)
 * city 3 —> city 1 (cost = 10)
 * city 1 —> city 4 (cost = 80)
 *
 * Solution:
 *
 * The idea is to recur for all cities reachable from the source city and consider their minimum cost. The recurrence relation T(0) can be written as:
 * T(n) = minimum { T(i) + cost[i][n] }
 *     for all cities i between source city 0 and destination city n
 *
 * i.e., the minimum cost C(0, n) to reach city n from city 0 is
 * C(0, n) = minimum(cost[0][n],
 *             C(0, 1) + cost[1][n],
 *             C(0, 2) + cost[2][n],
 *             …
 *             C(0, n-1) + cost[n-1][n]
 *         )
 *
 * The time complexity of this solution would be exponential since we might end up computing the same subproblem repeatedly. We can use dynamic programming to optimize the code since this problem exhibits both properties of dynamic programming, i.e., overlapping subproblems and optimal substructure.
 *
 *
 * The idea is to construct an auxiliary array lookup[] for storing the subproblem solutions where each element lookup[i] of the lookup table stores the minimum cost to reach city i from city 0.
 *
 * The time complexity of the proposed solution is O(N3) for an N × N matrix. The auxiliary space required by the program is O(N).
 */
public class MinCostToReachDestFromSource {

    public static void main(String[] args) {
        int[][] cost =
                {
                        { 0, 25, 20, 10, 105 },
                        { 20, 0, 15, 80, 80 },
                        { 30, 15, 0, 70, 90 },
                        { 10, 10, 50, 0, 100 },
                        { 40, 50, 5, 10, 0 }
                };

        System.out.print("The minimum cost is " + findMinCost(cost));
    }

    static int findMinCost(int[][] cost) {
        // base case
        if (cost == null || cost.length == 0)
            return 0;

        // `N x N` matrix
        int N = cost.length;

        // lookup[i] stores the minimum cost to reach city i from city 0
        int[] lookup = new int[N];

        // initialize lookup[] with direct ticket price from the source city
        for (int i = 0; i < N; i++) {
            lookup[i] = cost[0][i];
        }

        // repeat loop till lookup[] is filled with all minimum values
        boolean isFilled = false;
        while (!isFilled) {
            isFilled = true;
            // fill lookup[] in a bottom up manner
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (lookup[i] > lookup[j] + cost[j][i]) {
                        lookup[i] = lookup[j] + cost[j][i];
                        isFilled = false;
                    }
                }
            }
        }

        // return the minimum cost to reach city N-1 from city 0
        return lookup[N - 1];
    }

}
