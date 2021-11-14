package com.vivek.dp;

/**
 * Jump Game VII
 *
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
 *
 *     i + minJump <= j <= min(i + maxJump, s.length - 1), and
 *     s[j] == '0'.
 *
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 *
 * Example 1:
 *
 * Input: s = "011010", minJump = 2, maxJump = 3
 * Output: true
 * Explanation:
 * In the first step, move from index 0 to index 3.
 * In the second step, move from index 3 to index 5.
 *
 * Example 2:
 *
 * Input: s = "01101110", minJump = 2, maxJump = 3
 * Output: false
 *
 * Approach:
 *
 * 1. It's a bottom-up DP implementation. The boolean value represents whether this position is reachable from start. So the first step is to generate the table. Here the table was pre-labeled True or False, thus '1's are already labeled False.
 * 2. To determine the state of dp[i], one need to check the states in window dp[i-maxJ : i-minJ], because any one of them can reach i if it's labeled True.
 * 3. Then you need to check if there is a True in this window. Notice that this is a sliding window problem, so you don't need to calculate it everytime. You only need to remove the effect from dp[i-maxJ-1] and add the dp[i-minJ]. This is done by these two lines of code pre += dp[i - minJ] and pre -= dp[i - maxJ - 1]
 * 4. The if statements if i >= minJ: and if i > maxJ: are dealing with the initial boundary.
 *
 * Reference: https://leetcode.com/problems/jump-game-vii
 */
public class JumpGame {

    static boolean canReach(String s, int minJ, int maxJ) {
        int n = s.length();
        int pre = 0;
        boolean[] dp = new boolean[n];
        dp[0] = true;

        for (int i = 1; i < n; i++) {
            if (i >= minJ && dp[i - minJ])
                pre++;
            if (i > maxJ && dp[i - maxJ - 1])
                pre--;

            dp[i] = pre > 0 && s.charAt(i) == '0';
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(canReach("011010", 2, 3));
    }

}
