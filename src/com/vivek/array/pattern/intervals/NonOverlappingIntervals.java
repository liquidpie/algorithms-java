package com.vivek.array.pattern.intervals;

import java.util.Arrays;

/**
 * Non-overlapping Intervals
 *
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals
 * you need to remove to make the rest of the intervals non-overlapping.
 *
 * Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 *
 * Example 2:
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 *
 * Example 3:
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 *
 * https://leetcode.com/problems/non-overlapping-intervals/description/
 *
 */
public class NonOverlappingIntervals {

    public static void main(String[] args) {
        {
            int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
            int result = eraseOverlapIntervals(intervals);
            System.out.println(result);
        }
        {
            int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
            int result = eraseOverlapIntervals(intervals);
            System.out.println(result);
        }
        {
            int[][] intervals = {{1, 2}, {2, 3}};
            int result = eraseOverlapIntervals(intervals);
            System.out.println(result);
        }
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int n = intervals.length;
        // sort array based on end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        // keep track of maximum number of non-overlapping intervals using greedy approach
        int count = 1;
        int end = intervals[0][1];

        for (int i = 1; i < n; i++) {
            // compare previous interval end time with next interval start time
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }

        return n - count;
    }

}
