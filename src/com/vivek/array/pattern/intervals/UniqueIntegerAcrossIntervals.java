package com.vivek.array.pattern.intervals;

import java.util.*;

/**
 * Given n ranges [a1, b1], [a2, b2], [a3, b3].... [an, bn]. All the entries are integers. Give total number of
 * unique integers across all the ranges.
 * Ex: [6,10],[1,4],[7,12]
 * Output: 11
 *
 * Solution:
 *
 * To find the total number of unique integers across all the ranges, we can follow these steps:
 *
 *     Initialize an empty set to store unique integers.
 *     Iterate through each range [a, b] in the given list of ranges.
 *     For each range, iterate through all the integers from a to b (inclusive) and add them to the set.
 *     After iterating through all the ranges, the set will contain all the unique integers.
 *     Finally, return the size of the set, which represents the total number of unique integers across all the ranges.
 *
 *
 */
public class UniqueIntegerAcrossIntervals {

    /**
     * This solution fails miserably for longer ranges. Check the below optimized solution
     */
    static int countUniqueIntegersUsingSet(int[][] ranges) {
        Set<Integer> uniqueIntegers = new HashSet<>();
        for (int[] range : ranges) {
            int a = range[0];
            int b = range[1];
            for (int num = a; num <= b; num++) {
                uniqueIntegers.add(num);
            }
        }
        return uniqueIntegers.size();
    }

    static int countUniqueIntegersUsingMergeOverlapIntervals(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        int index = 0;

        // Start from the next interval and merge if necessary
        for (int i = 1; i < ranges.length; i++) {
            // If this is not first Interval and overlaps with the previous one
            if (ranges[index][1] >= ranges[i][0]) {
                // Merge previous and current Intervals
                ranges[index][1] = Math.max(ranges[index][1], ranges[i][1]);
            } else {
                index++;
                ranges[index] = ranges[i];
            }

        }

        int count = 0;
        for (int i = 0; i < index + 1; i++) {
            count += ranges[i][1] - ranges[i][0] + 1;
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] ranges = {{6, 10}, {1, 4}, {7, 12}};
//        int result = countUniqueIntegersUsingSet(ranges);
        int result = countUniqueIntegersUsingMergeOverlapIntervals(ranges);
        System.out.println(result);  // Output: 11
    }

}
