package com.vivek.array.examples;

import java.util.Arrays;

/**
 * Given an array of 1’s and 0’s and a set of ranges, find out the number of 0’s in each range (i, j) including both indexes.
 *
 * // Input Array: [1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1] size n
 * // Range Array: [[0,4], [3,6], [1, 5], [0, 7]] size m
 * // Output: [3, 3, 4, 6]
 *
 * FollowUp:
 *
 * For the same input find out the number of 0’s which are guarded by 1’s on left and right within the same range.
 * // Output: [2, 0, 0, 2]
 */
public class GuardedZeros {


    // First Problem
    static int[] guardedZeros(int[] input, int[][] ranges) {
        int[] index = new int[input.length];
        int counter = 0;
        int[] result = new int[ranges.length];
        for (int i = 0; i < index.length; i++) {
            if (input[i] == 0)
                counter++;
            index[i] = counter;
        }

        for (int i = 0; i < ranges.length; i++) {
            int l = Math.max(0, ranges[i][0] - 1);
            int r = ranges[i][1];
            result[i] = index[r] - index[l];
        }

        System.out.println(Arrays.toString(index));

        return result;
    }

    // Second Problem
    static int[] guardedZeros2(int[] input, int[][] ranges) {
        int[] result = new int[ranges.length];

        int[] nextOnesIndex = new int[input.length];

        int lastOnesIndex = -1;
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] == 1)
                lastOnesIndex = i;
            nextOnesIndex[i] = lastOnesIndex != -1 ? lastOnesIndex : i;
        }

        int[] counts = new int[input.length];
        int counter = 0;
        boolean startCounting = false;
        int lastCount = 0;
        for (int i = 0; i < counts.length; i++) {
            if (input[i] == 1) {
                startCounting = true;
                lastCount += counter;
                counter = 0;
            }
            if (input[i] == 0 && startCounting) {
                counter++;
            }
            counts[i] = lastCount;
        }

        for (int i = 0; i < ranges.length; i++) {
            int l = nextOnesIndex[ranges[i][0]];
            int r = ranges[i][1];
            result[i] = counts[r] - counts[l];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] input = {1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1};

        int[][] ranges = {{0,4}, {3,6}, {1, 5}, {0, 7}, {1, 9}};

        System.out.println(Arrays.toString(guardedZeros2(input, ranges)));
    }

}
