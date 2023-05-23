package com.vivek.search;

/**
 * Binary Searchable Numbers
 *
 * Given an unsorted array of n distinct integers, return the number of elements that are binary searchable.
 *
 * Problem asks how many elements are larger than all elements to their left, and smaller than all elements to their right.
 *
 * Simple Java O(n) solution, counting the number of elements which are greater than the maximum element on its left
 * and less than the minimum element on its right. To do this we can keep two auxiliary arrays for the greatest elems
 * and least elems. Space Complexity: O(n)
 *
 * https://leetcode.com/playground/CngutXHK
 * https://leetcode.com/discuss/interview-question/352743/Google-or-Onsite-or-Guaranteed-Binary-Search-Numbers
 */
public class BinarySearchableNumbers {

    public static void main(String[] args) {
        System.out.println(howManyBinarySearchable(new int[] {1, 3, 2}));
        System.out.println(howManyBinarySearchable(new int[] {2, 1, 3, 5, 4, 6}));
        System.out.println(howManyBinarySearchable(new int[] {1, 5, 7, 11, 12, 18}));
        System.out.println(howManyBinarySearchable(new int[] {5, 4, 3, 2, 1, 0}));
        System.out.println(howManyBinarySearchable(new int[] {1, 3, 2, 4, 5, 7, 6, 8}));
    }

    static int howManyBinarySearchable(int[] input) {
        int n = input.length;
        if (n == 0)
            return 0;

        int[] maxLeft = new int[n];
        int[] minRight = new int[n];

        int maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxLeft[i] = maxSoFar;
            maxSoFar = Math.max(maxSoFar, input[i]);
        }

        int minSoFar = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            minRight[i] = minSoFar;
            minSoFar = Math.min(minSoFar, input[i]);
        }

        // count elements having binary search property
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (input[i] > maxLeft[i] && input[i] < minRight[i]) {
                count++;
            }
        }

        return count;
    }
}
