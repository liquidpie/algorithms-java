package com.vivek.array;

import java.util.Arrays;

/**
 * Count Markers (asked in Uber)
 *
 * There is a long road with markers on it after each unit of distance. There are some ubers standing on the road. You are given the starting and ending coordinate of each uber (both inclusive).
 * Note: At any given marker there may be multiple ubers or there may be none at all.
 *
 * Your task is to find the number of markers on which at least one uber is present. An uber with coordinates (l, r) is considered to be present on a marker m if and only if l ≤ m ≤ r.
 *
 * Example:
 *
 * For coordinates=[[4, 7], [-1, 5], [3, 6]], the output should be easyCountUber(coordinates) = 9.
 *
 * Solution:
 * Problem is just a variation of Merge Overlapping Intervals
 *
 *     Sort all intervals in increasing order of start time.
 *     Traverse sorted intervals starting from the first interval,
 *     Do the following for every interval.
 *        If the current interval is not the first interval and it overlaps with the previous interval,
 *        then merge it with the previous interval. Keep doing it while the interval overlaps with the previous one.
 *        Otherwise, Add the current interval to the output list of intervals.
 *
 * https://leetcode.com/discuss/interview-question/1505109/Count-markers-or-Uber-or-codesignal/1173486
 */
public class CountMarkers {

    public static void main(String[] args) {
        int[][] coordinates = { {4, 7}, {-1, 5}, {3, 6} };
        System.out.println(countMarkers(coordinates));
    }

    static int countMarkers(int[][] coordinates) {
        // sort the coordinates by start time
        Arrays.sort(coordinates, (c1, c2) -> c1[0] - c2[0]);
        int index = 0;
        for (int i = 1; i < coordinates.length; i++) {
            if (coordinates[index][1] >= coordinates[i][0]) {
                coordinates[index][1] = Math.max(coordinates[index][1], coordinates[i][1]);
            } else {
                index++;
                coordinates[index] = coordinates[i];
            }
        }

        int count = 0;
        for (int i = 0; i < index + 1; i++) {
            count += coordinates[i][1] - coordinates[i][0] + 1;
        }
        return count;
    }

}
