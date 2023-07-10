package com.vivek.array.pattern.maxmin;

import java.util.Stack;

/**
 * Maximum Subarray Min-Product
 *
 * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
 *
 * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
 * Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums. Since the answer may be large, return it modulo 109 + 7.
 *
 * Note that the min-product should be maximized before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,2]
 * Output: 14
 * Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
 * 2 * (2+3+2) = 2 * 7 = 14.
 * Example 2:
 *
 * Input: nums = [2,3,3,1,2]
 * Output: 18
 * Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
 * 3 * (3+3) = 3 * 6 = 18.
 * Example 3:
 *
 * Input: nums = [3,1,5,6,4,2]
 * Output: 60
 * Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
 * 4 * (5+6+4) = 4 * 15 = 60.
 *
 * Solution:
 * The approach uses a Stack and Prefix Sum Array.
 *
 * The idea is to use the stack to get the index of nearest smaller elements on the left and right of each element.
 * Now, using these, the required product can be obtained. Follow the steps below to solve the problem:
 *
 *  - Initialize an array presum[] to store all the resultant prefix sum array of the given array.
 *  - Initialize two arrays l[] and r[] to store the index of the nearest left and right smaller elements respectively.
 *  - For every element arr[i], calculate l[i] and r[i] using a stack.
 *  - Traverse the given array and for each index i, the product can be calculated by:
 *      arr[i] * (presum[r[i]] – presum[l[i]-1])
 *
 * We store elements in a stack st in a non-decreasing order. When we need to remove an element j from the stack, it means:
 *
 * Elements from j to i - 1 are all greater than j.
 * Elements after the new top of the stack iare all greater than j.
 * Therefore, we can multiply the current element j to the sum of all elements between the new top of the stack and i.
 *
 * https://leetcode.com/problems/maximum-subarray-min-product/solutions/1198896/o-n-monostack-with-picture/
 *
 * Print the maximum product after completing all the above steps
 *
 * Time Complexity: O(N)
 * Auxiliary Space: O(N)
 *
 * https://www.geeksforgeeks.org/maximize-product-of-subarray-sum-with-its-minimum-element/
 *
 * https://leetcode.com/problems/maximum-subarray-min-product/
 */
public class MaxSubarrayMinProduct {

    public static void main(String[] args) {
        int[] nums = {2,3,3,1,2};
        System.out.println(maxSumMinProduct(nums));
    }

    static int maxSumMinProduct(int[] nums) {
        int mod = 1_000_000_007;
        int n = nums.length;
        long[] presum = new long[n + 1];

        // Find the prefix sum array
        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + nums[i];
        }

        long ans = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] > nums[i])) {
                final int minVal = nums[stack.pop()];
                final long sum = presum[i] - presum[stack.isEmpty() ? 0 : stack.peek() + 1];
                ans = Math.max(ans, minVal * sum);
            }
            stack.push(i);
        }

        return (int) ans % mod;
    }

}
