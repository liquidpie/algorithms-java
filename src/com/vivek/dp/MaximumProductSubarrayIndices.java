package com.vivek.dp;

import java.util.Arrays;

/**
 * Maximum Product Subarray and return subarray indices
 *
 * Following the problem of Maximum Product Subarray, instead of returning the product,
 * Return the start and end index of the subarray.
 *
 * Find the start and end indices of the subarray whose product is maximum.
 * eg. 1 4 -3 2 6 1 0 =>. startIndex = 3, endIndex = 4 (2,6 with product 12)
 *
 * Approach:
 * --------------------------------------------------
 * Use the same approach as the original problem.
 * When the result is updated, end index will be same at the current index
 * Iterate backwards starting from the end till the product is matched, that index will be the start index
 *
 * https://stackoverflow.com/questions/55547870/cant-find-a-range-of-maximum-product-subarray
 *
 * Complexity Analysis
 *
 * Time complexity : O(N) where N is the size of nums. The algorithm achieves linear runtime since we are going through nums only once.
 * Space complexity : O(1)
 */
public class MaximumProductSubarrayIndices {

    static int[] maxProductSubarray(int[] nums) {
        if (nums.length == 0)
            return null;

        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int result = max_so_far;
        int end = 0;

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            int temp_max = Math.max(current, Math.max(max_so_far * current, min_so_far * current));
            min_so_far = Math.min(current, Math.min(max_so_far * current, min_so_far * current));
            // Update max_so_far after min_so_far to avoid overwriting it
            max_so_far = temp_max;
            // Update the result with the maximum product found so far
            if (result < max_so_far) {
                result = max_so_far;
                end = i;
            }
        }

        // Find the start index
        int start = end;
        int product = 1;
        while (start >= 0) {
            product *= nums[start];
            if (product == result) {
                break;
            }
            start--;
        }

        return new int[]{start, end};
    }

    public static void main(String[] args) {
        {
            int[] nums = {2, 3, -2, 4};
            System.out.println(Arrays.toString(maxProductSubarray(nums))); // [0, 1]
        }
        {
            int[] nums = {1, 4, -3, 2, 6, 1, 0};
            System.out.println(Arrays.toString(maxProductSubarray(nums))); // [3, 4]
        }
    }

}
