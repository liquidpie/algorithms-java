package com.vivek.array.examples;

import java.util.Arrays;

/**
 * Check if any two intervals intersects among a given set of intervals
 *
 * The problem can be changed to called as Check if any two meetings overlap among a given set of meetings
 *
 * Input:  arr[] = {{1, 3}, {5, 7}, {2, 4}, {6, 8}}
 * Output: true
 * The intervals {1, 3} and {2, 4} overlap
 *
 *
 * Input:  arr[] = {{1, 3}, {7, 9}, {4, 6}, {10, 13}}
 * Output: false
 * No pair of intervals overlap.
 *
 * Approach 1: Using Sorting (Time Complexity O(nLogn))
 *
 * Approach 2: Using Counter
 * 1. Find the overall maximum element. Let it be max_ele
 * 2. Initialize an array of size max_ele with 0.
 * 3. For every interval [start, end], increment the value at index start, i.e. arr[start]++ and decrement the value at index (end + 1), i.e. arr[end + 1]- -.
 * 4. Compute the prefix sum of this array (arr[]).
 * 5. Every index, i of this prefix sum array will tell how many times i has occurred in all the intervals taken together. If this value is greater than 1, then it occurs in 2 or more intervals.
 * 6. So, simply initialize the result variable as false and while traversing the prefix sum array, change the result variable to true whenever the value at that index is greater than 1.
 *
 * Time Complexity : O(max_ele + n)
 *
 * Reference: https://www.geeksforgeeks.org/check-if-any-two-intervals-overlap-among-a-given-set-of-intervals
 */
public class CheckIfIntervalsOverlap {

    static boolean usingSorting(int[][] arr) {
        if (arr == null || arr.length == 1)
            return false;

        Interval[] intervals = buildInterval(arr);

        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end)
                return true;
        }
        return false;
    }

    static boolean usingCounter(int[][] arr) {
        if (arr == null || arr.length == 1)
            return false;

        Interval[] intervals = buildInterval(arr);

        int max = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (max < intervals[i].end)
                max = intervals[i].end;
        }

        int[] aux = new int[max + 1];

        for (int i = 0; i < intervals.length; i++) {
            aux[intervals[i].start]++;
            aux[intervals[i].end]--;
        }

        // calculate prefix sum
        for (int i = 1; i <= max; i++) {
            aux[i] += aux[i - 1];
            if (aux[i] > 1)
                return true;
        }

        return false;
    }

    private static Interval[] buildInterval(int[][] arr) {
        int n = arr.length;
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval(arr[i][0], arr[i][1]);
        }
        return intervals;
    }

    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 3}, {5, 7}, {2, 4}, {6, 8}
        };
        System.out.println(usingSorting(arr));
    }

}
