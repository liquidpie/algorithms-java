package com.vivek.dp;

/**
 * Coin Change
 *
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Solution ::
 * So the first solution that came up in my mind was a greedy approach that I will sort the coins in descending order
 * based on their values and try to include the biggest coin because it will decrease the amount by a bigger value and
 * that can be filled by smaller coins so I'll get the minimum number of coins solution.
 * Like as in Example 1: coins = [1,2,5], amount = 11 so the sorted coins will be [5,2,1] now I'll include 5 first which results in 2 coins,
 * and the rest amount is 11 - (5x2) = 1 which can be fulfilled by 1 coin so I'll be using 3 coins total.
 *
 * BUT
 * This is not a correct solution, Let's look at a beautiful case coins=[9,6,5,1], amount=11. If I include 9 then the rest value is 2 which can be created by 2 1 coins, in this way I'll be using 3 coins but the correct solution is 2 coins which can be achieved using 2 coins 5 & 6.
 *
 * So what should be the correct solution???
 *
 * So let's understand the correct solution now. The problem occurred when we included the big element first what if we go for the solution in a subproblem manner? I mean let's try to achieve the solution for amount=1, then for amount=2 then for amount=3, and so on until we get the solution for the amount we want. The interesting thing in this is that whenever I'm trying to get the solution for n, I already have the solution for all the values below n which can be used.
 *
 * Did you ask, how? So, let's understand that in this way, I will try to include each coin to make a value equals to the current amount, If I am able to include this coin then check if including this coin results in the minimum number of coins till now if yes then I'll include this coin else not. Now here this past data will help. We'll understand this with an example but before that let's see the algorithm first.
 *
 * Algorithm:
 *  1. Create an array dp to keep track of all the solution from 0 to amount
 *  2. Fill this array with Maximum Value of Integer
 *  3. For amount=0 we need 0 coins so set dp[0]=0
 *  4. Now fill the array using the below loop from i=1 to the amount
 *      1. Try each coin one by one to create amount i using this coin j
 *          1. If the value of this coin is smaller or equal to i then do below else pick the next coin
 *          2. If this coin is included then the rest of the amount is i-coins[j]
 *          3. Now check if a solution exists for this amount i.e. dp[i-coins[j] != INT_MAX and including this coins gives
 *             better solution then the current one
 *          4. If yes then include this coin and replace dp[i] with new minimum value dp[i-coins[j] + 1
 *  5. If dp[amount]=INT_MAX i.e there is no solution so return -1 else return dp[amount]
 *
 * Time Complexity: O(amount * coins.length)
 * Space Complexity: O(amount)
 *
 * Solution: https://dev.to/shivams136/leetcode-322-coin-change-solution-4kmd
 *
 * https://leetcode.com/problems/coin-change
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {9, 6, 5, 1};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
    }

    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        int len = coins.length;

        // Fill DP array from amount=1 to amount's actual value
        for (int i = 1; i <= amount; i++) {
            // Try to include all the coins one by one
            for (int j = 0; j < len; j++) {
                // If this coin is usable(value less than current amount)
                if (coins[j] <= i) {
                    // What is the cost for rest of the amount
                    // If I use this coin
                    // eg. if amount=8 and coins[j]=5 then rest is min cost
                    // for 8-5 = 3
                    int rest = dp[i - coins[j]];
                    // If including this coin gives lesser value
                    // than current min value then include it
                    if (rest != Integer.MAX_VALUE && rest + 1 < dp[i])
                        dp[i] = rest + 1;
                }
            }
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }

}
