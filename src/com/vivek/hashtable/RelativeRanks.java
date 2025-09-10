package com.vivek.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 506. Relative Ranks
 *
 * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition.
 * All the scores are guaranteed to be unique.
 *
 * The athletes are placed based on their scores, where the 1st place athlete has the highest score,
 * the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
 *
 *     The 1st place athlete's rank is "Gold Medal".
 *     The 2nd place athlete's rank is "Silver Medal".
 *     The 3rd place athlete's rank is "Bronze Medal".
 *     For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
 *
 * Return an array answer of size n where answer[i] is the rank of the ith athlete.
 *
 * Example 1:
 *
 * Input: score = [5,4,3,2,1]
 * Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
 *
 * Example 2:
 *
 * Input: score = [10,3,8,9,4]
 * Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].
 *
 * Reference: https://leetcode.com/problems/relative-ranks
 */
public class RelativeRanks {

    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++)  {
            map.put(score[i], i);
        }

        Arrays.sort(score);
        int rank = 0;
        for (int i = n - 1; i >= 0; i--) {
            rank++;

            if (rank == 1) {
                result[map.get(score[i])] = "Gold Medal";
            } else if (rank == 2) {
                result[map.get(score[i])] = "Silver Medal";
            } else if (rank == 3) {
                result[map.get(score[i])] = "Bronze Medal";
            } else {
                result[map.get(score[i])] = String.valueOf(rank);
            }
        }

        return result;
    }

}
