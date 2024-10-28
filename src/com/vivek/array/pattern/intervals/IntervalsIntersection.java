package com.vivek.array.pattern.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Intervals Intersection (medium)
 *
 * Problem Statement #
 * Given two lists of intervals, find the intersection of these two lists. Each list consists of disjoint intervals
 * sorted on their start time.
 *
 * Example 1:
 * Input: arr1=[[1, 3], [5, 6], [7, 9]], arr2=[[2, 3], [5, 7]]
 * Output: [2, 3], [5, 6], [7, 7]
 * Explanation: The output list contains the common intervals between the two lists.
 *
 * Example 2:
 * Input: arr1=[[1, 3], [5, 7], [9, 12]], arr2=[[5, 10]]
 * Output: [5, 7], [9, 10]
 * Explanation: The output list contains the common intervals between the two lists.
 *
 *
 * Solution #
 * This problem follows the Merge Intervals pattern. As we have discussed under Insert Interval,
 * there are five overlapping possibilities between two intervals ‘a’ and ‘b’.
 * A close observation will tell us that whenever the two intervals overlap, one of the interval’s start time lies
 * within the other interval. This rule can help us identify if any two intervals overlap or not.
 *
 * Now, if we have found that the two intervals overlap, how can we find the overlapped part?
 * Again from the above diagram, the overlapping interval will be equal to:
 *
 *      start = max(a.start, b.start)
 *      end = min(a.end, b.end)
 *
 * That is, the highest start time and the lowest end time will be the overlapping interval.
 * So our algorithm will be to iterate through both the lists together to see if any two intervals overlap.
 * If two intervals overlap, we will insert the overlapped part into a result list and move on to the next interval which is finishing early.
 *
 * Time complexity #
 * As we are iterating through both the lists once, the time complexity of the above algorithm is O(N + M ),
 * where ‘N’ and ‘M’ are the total number of intervals in the input arrays respectively.
 *
 * Space complexity #
 * Ignoring the space needed for the result list, the algorithm runs in constant space O(1).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Merge Intervals
 */
public class IntervalsIntersection {

    public static void main(String[] args) {
        int[][] result = intersect(new int[][]{{1, 3}, {5, 6}, {7, 9}}, new int[][]{{2, 3}, {5, 7}});
        System.out.println(Arrays.deepToString(result));
    }

    static int[][] intersect(int[][] arr1, int[][] arr2) {
        Interval[] intervals1 = Util.buildInterval(arr1);
        Interval[] intervals2 = Util.buildInterval(arr2);

        List<Interval> result = new ArrayList<>();

        int i = 0;
        int j = 0;
        while (i < intervals1.length && j < intervals2.length) {
            // check if one of the interval's start time lies within the other interval
            if ((intervals1[i].start >= intervals2[j].start && intervals1[i].start <= intervals2[j].end)
                || (intervals2[j].start >= intervals1[i].start && intervals2[j].start <= intervals1[i].end)) {
                int start = Math.max(intervals1[i].start, intervals2[j].start);
                int end = Math.min(intervals1[i].end, intervals2[j].end);
                result.add(new Interval(start, end));
            }

            // move next from the interval which is finishing first
            if (intervals1[i].end < intervals2[j].end) {
                i++;
            } else {
                j++;
            }
        }
        return Util.buildArray(result);
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval(int[] arr) {
            this.start = arr[0];
            this.end = arr[1];
        }
    }

    static class Util {

        static Interval[] buildInterval(int[][] arr) {
            Interval[] intervals = new Interval[arr.length];
            for (int i = 0; i < arr.length; i++) {
                intervals[i] = new Interval(arr[i]);
            }
            return intervals;
        }

        static int[][] buildArray(List<Interval> intervals) {
            int[][] array = new int[intervals.size()][2];
            for (int i = 0; i < intervals.size(); i++) {
                array[i] = new int[]{intervals.get(i).start, intervals.get(i).end};
            }
            return array;
        }
    }

}
