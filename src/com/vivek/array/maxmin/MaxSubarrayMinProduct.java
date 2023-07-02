package com.vivek.array.maxmin;

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
        int[] nums = {3, 1, 6, 4, 5, 2};
        System.out.println(maxSumMinProduct(nums));
    }

    static int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        int[] presum = new int[n];
        presum[0] = nums[0];

        // Find the prefix sum array
        for (int i = 1; i < n; i++) {
            presum[i] += presum[i - 1] + nums[i];
        }

        // l[] and r[] stores index of
        // nearest smaller elements on
        // left and right respectively
        int[] l = new int[n];
        int[] r = new int[n];

        Stack<Integer> stack = new Stack<>();
        l[0] = 0;
        // Find all left index
        for (int i = 1; i < n; i++) {
            // Until stack is non-empty
            // & top element is greater
            // than the current element
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();

            if (!stack.isEmpty())
                l[i] = stack.peek() + 1;
            else
                l[i] = 0;

            // Push the current index i
            stack.push(i);
        }

        stack.clear();

        // Find all right index
        for (int i = n - 1; i >= 0; i--) {
            // Until stack is non-empty
            // & top element is greater
            // than the current element
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();

            if (!stack.isEmpty())
                r[i] = stack.peek() - 1;
            else
                r[i] = n - 1;

            // Push the current index i
            stack.push(i);
        }

        // stores the maximum product
        int maxProduct = 0;
        int tempProduct;

        for (int i = 0; i < n; i++) {
            // calculate the product
            tempProduct = nums[i] * (presum[r[i]] - (l[i] == 0 ? 0 : presum[l[i] - 1]));
            // update the max product
            maxProduct = Math.max(maxProduct, tempProduct);

        }

        return maxProduct;
    }

}
