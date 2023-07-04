package com.vivek.array.pattern.intervals;

import java.util.*;

/**
 * Given a set of time intervals in any order, merge all overlapping intervals into one and output the result
 * which should have only mutually exclusive intervals.
 *
 * Example:
 *
 *     Input: Intervals = {{1,3},{2,4},{6,8},{9,10}}
 *     Output: {{1, 4}, {6, 8}, {9, 10}}
 *     Explanation: Given intervals: [1,3],[2,4],[6,8],[9,10], we have only two overlapping intervals here,[1,3] and [2,4]. Therefore we will merge these two and return [1,4],[6,8], [9,10].
 *
 *     Input: Intervals = {{6,8},{1,9},{2,4},{4,7}}
 *     Output: {{1, 9}}
 *
 *
 * Approach:
 *
 * The idea to solve this problem is, first sort the intervals according to the starting time.
 * Once we have the sorted intervals, we can combine all intervals in a linear traversal.
 * The idea is, in sorted array of intervals, if interval[i] doesn’t overlap with interval[i-1],
 * then interval[i+1] cannot overlap with interval[i-1] because starting time of interval[i+1]
 * must be greater than or equal to interval[i].
 *
 * Follow the steps mentioned below to implement the approach:
 *
 *     Sort the intervals based on the increasing order of starting time.
 *     Push the first interval into a stack.
 *     For each interval do the following:
 *         If the current interval does not overlap with the top of the stack then, push the current interval into the stack.
 *         If the current interval overlap with the top of the stack then, update the stack top with the ending time of the current interval.
 *     The end stack contains the merged intervals.
 *
 *
 * https://www.geeksforgeeks.org/merging-intervals/
 *
 */
public class MergeOverlappingIntervals {

    public static void main(String[] args) {
//        int[][] arr = {{1, 6}, {4, 8}, {6, 10}, {11, 12}};
        int[][] arr = {{8, 10}, {10, 12}, {14, 19}};

        Interval[] intervals = new Interval[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intervals[i] = new Interval(arr[i][0], arr[i][1]);
        }

//        System.out.println(mergeOverlappingIntervals(intervals));
        System.out.println(mergeOverlappingIntervalsSpaceOptimized(intervals));
    }

    /**
     * Time complexity: O(N*log(N))
     * Auxiliary Space: O(N)
     */
    static List<Interval> mergeOverlappingIntervals(Interval[] intervals) {
        // Create an empty stack of intervals
        Stack<Interval> stack = new Stack<>();
        Arrays.sort(intervals, Comparator.comparingInt(i -> i.start));

        // push the first interval to stack
        stack.push(intervals[0]);

        // Start from the next interval and merge if necessary
        for (int i = 1; i < intervals.length; i++) {
            // get interval from stack top
            Interval top = stack.peek();
            // if current interval is not overlapping with stack top,
            // push it to the stack
            if (top.end < intervals[i].start) {
                stack.push(intervals[i]);
            } else if (top.end < intervals[i].end) {
                top.end = intervals[i].end;
                stack.pop();
                stack.push(top);
            }
        }

        List<Interval> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    /**
     * The above solution requires O(n) extra space for the stack. We can avoid the use of extra space by doing
     * merge operations in place. Below are detailed steps.
     *
     * Follow the steps mentioned below to implement the approach:
     *
     *     Sort all intervals in increasing order of start time.
     *     Traverse sorted intervals starting from the first interval,
     *     Do the following for every interval.
     *        If the current interval is not the first interval and it overlaps with the previous interval,
     *        then merge it with the previous interval. Keep doing it while the interval overlaps with the previous one.
     *        Otherwise, Add the current interval to the output list of intervals.
     *
     * Time Complexity: O(N*log(N))
     * Auxiliary Space Complexity: O(1)
     */
    static List<Interval> mergeOverlappingIntervalsSpaceOptimized(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i.start));

        int index = 0; // Stores index of last element in output array (modified intervals[])

        // Start from the next interval and merge if necessary
        for (int i = 1; i < intervals.length; i++) {
            // If this is not first Interval and overlaps with the previous one
            if (intervals[index].end >= intervals[i].start) {
                // Merge previous and current Intervals
                intervals[index].end = Math.max(intervals[index].end, intervals[i].end);
            } else {
                index++;
                intervals[index] = intervals[i];
            }

        }

        return new ArrayList<>(Arrays.asList(intervals).subList(0, index + 1));
    }


    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "{" + start + ", " + end + '}';
        }
    }

}
