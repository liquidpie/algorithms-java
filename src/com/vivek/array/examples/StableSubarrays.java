package com.vivek.array.examples;

import java.util.Arrays;

/**
 * Count of Stable Subarrays
 *
 * Given an array. Find the count of the contiguous subarrays of length 3 or more such that
 *
 * - first element of subarray equals to last element of subarray
 * - first and last elements of subarray equals to the sum of the internal elements of the subarray
 *
 * subarray[0] = subarray[n] = sum(subarray[1..n - 1]
 *
 * Example:
 *  arr= [9, 3, 3, 3, 9]
 *  output = 2
 *
 *  [3, 3, 3] and [9, 3, 3, 3, 9] are stable subarrays
 */
public class StableSubarrays {

    public static void main(String[] args) {
        int[] arr = {9, 3, 3, 3, 9};
        System.out.println(findStableSegment(arr.length, arr)); // Output: 2
    }

    static int findStableSegment(int n, int[] capacity) {
        int count = 0;
        if (capacity.length < 3)
            return -1;
        for (int i = 0; i < capacity.length - 2; i++) {
            for (int j = i + 2; j < capacity.length; j++) {
                if (capacity[i] != capacity[j])
                    continue;
                int interiorSum = Arrays.stream(capacity, i, j).sum() - capacity[i];
                if (capacity[i] == interiorSum)
                    count++;
            }
        }
        return count;
    }

}
