package com.vivek.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    static int lisDP(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(dp[i], max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {50, 3, 10, 7, 40, 80};
        System.out.println(lisDP(arr));
    }

}
