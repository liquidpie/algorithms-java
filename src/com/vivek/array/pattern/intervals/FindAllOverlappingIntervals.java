package com.vivek.array.pattern.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of intervals, find all the conflicting intervals.
 *
 * Example:
 *      Intervals: [[4,5], [2,3], [3,6], [5,7], [7,8]]
 * Output:
 *      [4,5] and [3,6] conflict.
 *      [3,6] and [5,7] conflict.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Merge Intervals
 */
public class FindAllOverlappingIntervals {

    public static void main(String[] args) {
        var result = conflicts(new int[][]{{4, 5}, {2, 3}, {3, 6}, {5, 7}, {7, 8}});
        System.out.println(result);
    }

    static List<List<int[]>> conflicts(int[][] arr) {
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int index = 0;
        List<List<int[]>> result = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[index][1] > arr[i][0]) {
                result.add(List.of(arr[index], arr[i]));
            } else {
                index++;
            }
        }

        return result;
    }

}
