package com.vivek.stack;

import java.util.Stack;

/**
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 *
 * Example 2:
 *
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 *
 * Number of sub arrays = N * (N + 1) / 2
 *
 * Approach: The Naive approach is to generate all possible (contiguous) subarrays, find their minimum and add them to result. The time complexity will be O(N2).
 *
 * Efficient Approach:
 * The general intuition for solution to the problem is to find sum(A[i] * f(i)), where f(i) is the number of subarrays in which A[i] is the minimum.
 *
 * In order to find f[i], we need to find out:
 *
 * left[i], the length of strictly larger numbers on the left of A[i],
 * right[i], the length of larger numbers on the right of A[i].
 * We make two arrays left[ ] and right[ ] such that:
 * left[i] + 1 equals to the number of subarrays ending with A[i], and A[i] is only single minimum.
 * Similarly, right[i] + 1 equals to the number of subarrays starting with A[i], and A[i] is first minimum.
 * Finally, f(i) = (left[i]) * (right[i]), where f[i] equals total number of subarrays in which A[i] is minimum.x
 *
 * Complexity Analysis:
 *
 *     Time Complexity: O(N), where N is the length of A.
 *     Space Complexity: O(N).
 *
 * https://leetcode.com/problems/sum-of-subarray-minimums/
 * https://www.geeksforgeeks.org/sum-of-minimum-elements-of-all-subarrays/
 * https://www.youtube.com/watch?v=Ulb3ixSpE4Y
 */
public class SubarrayMinimumsSum {

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        System.out.println(subarrayMinSums(arr));
    }

    static int subarrayMinSums(int[] arr) {
        int n = arr.length;
        int mod = 1000_000_007;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Pair> lStack = new Stack<>();
        Stack<Pair> rStack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int count = 1;
            while (!lStack.isEmpty() && arr[i] < lStack.peek().elem) {
                count += lStack.pop().numMin;
            }
            lStack.push(new Pair(arr[i], count));
            left[i] = count;
        }

        for (int i = n - 1; i >= 0; i--) {
            int count = 1;
            while (!rStack.isEmpty() && arr[i] <= rStack.peek().elem) {
                count += rStack.pop().numMin;
            }
            rStack.push(new Pair(arr[i], count));
            right[i] = count;
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (sum + (long) arr[i] * left[i] * right[i]) % mod;
        }

        return (int) sum;
    }

    static class Pair {
        int elem;
        int numMin;

        public Pair(int elem, int numMin) {
            this.elem = elem;
            this.numMin = numMin;
        }
    }

}
