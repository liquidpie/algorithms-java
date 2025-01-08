package com.vivek.dp;

/**
 * You are given two non-negative integer arrays price and tastiness, both arrays have the same length n.
 * You are also given two non-negative integers maxAmount and maxCoupons.
 *
 * For every integer i in range [0, n - 1]:
 *
 * price[i] describes the price of ith fruit.
 * tastiness[i] describes the tastiness of ith fruit.
 * You want to purchase some fruits such that total tastiness is maximized and the total price does not exceed maxAmount.
 *
 * Additionally, you can use a coupon to purchase fruit for half of its price (rounded down to the closest integer).
 * You can use at most maxCoupons of such coupons.
 *
 * Return the maximum total tastiness that can be purchased.
 *
 * Note that:
 *
 * You can purchase each fruit at most once.
 * You can use coupons on some fruit at most once.
 *
 * Example 1:
 *
 * Input: price = [10,20,20], tastiness = [5,8,8], maxAmount = 20, maxCoupons = 1
 * Output: 13
 * Explanation: It is possible to make total tastiness 13 in following way:
 * - Buy first fruit without coupon, so that total price = 0 + 10 and total tastiness = 0 + 5.
 * - Buy second fruit with coupon, so that total price = 10 + 10 and total tastiness = 5 + 8.
 * - Do not buy third fruit, so that total price = 20 and total tastiness = 13.
 * It can be proven that 13 is the maximum total tastiness that can be obtained.
 *
 * Example 2:
 *
 * Input: price = [10,15,7], tastiness = [5,8,20], maxAmount = 10, maxCoupons = 2
 * Output: 28
 * Explanation: It is possible to make total tastiness 20 in following way:
 * - Do not buy first fruit, so that total price = 0 and total tastiness = 0.
 * - Buy second fruit with coupon, so that total price = 0 + 7 and total tastiness = 0 + 8.
 * - Buy third fruit with coupon, so that total price = 7 + 3 and total tastiness = 8 + 20.
 * It can be proven that 28 is the maximum total tastiness that can be obtained.
 *
 * Approach:
 * Use a 2D DP table dp[i][j] where i represents the money spent and j represents the number of coupons used.
 * For each item, consider two options:
 *
 *     Buy without a coupon.
 *     Buy with a coupon (if coupons are available).
 *
 * Update the DP table based on these decisions.
 *
 * Complexity:
 *
 *     Time: O(n×m×k)O(n×m×k), where nn is the number of items, mm is the money available, and kk is the number of coupons.
 *     Space: O(m×k)O(m×k) for the DP table.
 *
 * Reference:
 * https://leetcode.com/problems/maximize-total-tastiness-of-purchased-fruits/description/
 */
public class MaximizeTastinessOfPurchasedFruits {

    public static void main(String[] args) {
        int[] price = {10, 20, 20};
        int[] tastiness = {5, 8, 8};
        int maxAmount = 20;
        int maxCoupons = 1;

        int maxTastiness = maxTastiness(price, tastiness, maxAmount, maxCoupons);
        System.out.println("Maximum total tastiness: " + maxTastiness); // output: 13
    }

    static int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
        int[][] dp = new int[maxAmount + 1][maxCoupons + 1]; // dp[i][j]: max tastiness with i money and j coupons used
        int maxTastiness = Integer.MIN_VALUE;

        for (int idx = 0; idx < price.length; idx++) {
            for (int money = maxAmount; money >= 0; money--) {
                for (int coupons = maxCoupons; coupons >= 0; coupons--) {
                    // Option 1: Do not buy the current item
                    int currentTastiness = dp[money][coupons];

                    // Option 2: Buy without a coupon
                    if (money >= price[idx]) {
                        currentTastiness = Math.max(currentTastiness, dp[money - price[idx]][coupons] + tastiness[idx]);
                    }

                    // Option 3: Buy with a coupon (if coupons available)
                    if (coupons > 0 && money >= price[idx] / 2) {
                        currentTastiness = Math.max(currentTastiness, dp[money - price[idx] / 2][coupons - 1] + tastiness[idx]);
                    }

                    dp[money][coupons] = currentTastiness;
                    maxTastiness = Math.max(maxTastiness, dp[money][coupons]);

                }
            }
        }

        return maxTastiness;
    }

}
