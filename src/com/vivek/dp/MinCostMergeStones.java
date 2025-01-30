package com.vivek.dp;

import java.util.Arrays;

/**
 * 1000. Minimum Cost to Merge Stones
 *
 * There are n piles of stones arranged in a row. The ith pile has stones[i] stones.
 *
 * A move consists of merging exactly k consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these k piles.
 *
 * Return the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.
 *
 * Example 1:
 *
 * Input: stones = [3,2,4,1], k = 2
 * Output: 20
 * Explanation: We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 *
 * Example 2:
 *
 * Input: stones = [3,2,4,1], k = 3
 * Output: -1
 * Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
 *
 * Example 3:
 *
 * Input: stones = [3,5,1,2,6], k = 3
 * Output: 25
 * Explanation: We start with [3, 5, 1, 2, 6].
 * We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
 * We merge [3, 8, 6] for a cost of 17, and we are left with [17].
 * The total cost was 25, and this is the minimum possible.
 *
 * Solution:
 * https://leetcode.com/problems/minimum-cost-to-merge-stones/solutions/1432667/explained-to-make-you-visualise-the-solution-detailed-explanation/
 *
 * 1. We know that after all merge operations , we will be left with only one pile.
 * 2. Now let's begin thinking , that in order to actually create one pile at the end ,
 * we in the previous step should be left with K piles so as to merge them into one pile .
 * However, in order to obtain the minimum cost of creating one pile , we must end up creating K piles in previous step with minimum cost.
 * 3. Now the question arises how do we create K piles in the previous steps such that those costs minimum cost .
 * To understand this , we must understand what does a pile represents .
 * A pile here is basically a continuous segment in the array without any restriction on the length . How does we arrive to the definition of a pile ?
 * 4. Now based on above figure, but what I meant to explain was that when you are merging a consecutive segment of k piles,
 * some of these piles could have been from already merged segment (say x shown in diagram ),
 * and we eventually are increasing the length of this segment if we involve x in other merging operations which simply means the pile is same but its length is increased .
 * And at the end , we just require k piles with minimum cost .
 * 5. If my attempt to make you understand what a pile is successful,
 * then the above problem reduces to actually dividing the array into k segments such that the total cost for reaching to this k piles in minimum .
 * 6. Let's define the state for the problem :
 *      dp(i,j,k) = means the minimum cost to merge array from index i to index j
 *     into k different piles
 * 7. Now , since state is known , here are transitions between the states
 *
 *     Transitions
 *     ============
 *
 *     dp(i,j,1) = dp(i,j,k) + sum[i..j]
 *                 where sum[i..j] represents the sum between index i and index j .
 *                 Which means that in order to create one pile from index i to
 *                 to index j (dp(i,j,1)) in minimum cost , we have to create k piles
 *                 from index i to index j (dp(i,j,k)) and merge the operation cost
 *                 which is sum of the segment.
 *
 *     dp(i,j,k) = dp(i,t,1) + dp(t+1,j,k-1) where  t lies between index i to j
 *                 where i is inclusive and j is exclusive .
 *                 which means that in order to create k pile from index i to
 *                 index j , we first choose any segment of arbitary length
 *                 and try creating the pile from (i,t) and then check for the
 *                 minimum cost of creating (k-1) piles from the rest of the
 *                 array .
 *
 *     Base Cases :
 *     ==============
 *
 *     dp(i,i,1) = Since only merge operation has cost therfore , and we dont need
 *                 merge in the interval i to i to create 1 pile, therefore cost is 0 .
 *
 * The answer to the problem will be stored in dp[1][n][1], which represents the minimal cost of merging all piles from 1 to n into a single pile.
 *
 * Better Solution: https://leetcode.com/problems/minimum-cost-to-merge-stones/
 *
 * Reference:
 * https://leetcode.com/problems/minimum-cost-to-merge-stones/description/
 */
public class MinCostMergeStones {

    public static void main(String[] args) {
        {
            int[] stones = {3, 2, 4, 1};
            int k = 2;
            System.out.println(mergeStones(stones, k)); // Output: 20
        }
        {
            int[] stones = {6, 4, 9, 3, 1};
            int k = 3;
            System.out.println(mergeStones(stones, k)); // Output: 36
        }
    }

    static int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) > 0) return -1;

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + stones[i];

        int[][] dp = new int[n][n];
        for (int m = k; m <= n; ++m)
            for (int i = 0; i + m <= n; ++i) {
                int j = i + m - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int mid = i; mid < j; mid += k - 1)
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                if ((j - i) % (k - 1) == 0)
                    dp[i][j] += prefix[j + 1] - prefix[i];
            }
        return dp[0][n - 1];
    }

}
