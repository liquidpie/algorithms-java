package com.vivek.sort.examples;

import java.util.Arrays;

/**
 * 561. Array Partition
 *
 * Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) such that the sum of min(ai, bi) for all i is maximized. Return the maximized sum.
 *
 * Example 1:
 *
 * Input: nums = [1,4,3,2]
 * Output: 4
 * Explanation: All possible pairings (ignoring the ordering of elements) are:
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * So the maximum possible sum is 4.
 *
 * Example 2:
 *
 * Input: nums = [6,2,6,5,1,2]
 * Output: 9
 * Explanation: The optimal pairing is (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9.
 *
 * Solution:
 * Approach 1:
 * The algorithm is first sort the input array and then the sum of 1st, 3rd, 5th..., is the answer.
 *
 * Approach 2:
 *
 * Use a bucket of size 20001 to handle nums from -10000 to 10000.
 * Fill the bucket with frequencies.
 * Walk through the bucket from smallest to largest.
 * Alternate picks using a flag: add the number when flag is True, then flip the flag.
 *
 * This simulates sorted pairing without actually sorting.
 * Complexity
 *
 * Time Complexity: ( O(n) ) — counting sort pass.
 * Space Complexity: ( O(n) ) — for the bucket.
 *
 * https://leetcode.com/problems/array-partition/solutions/6685962/beats-100-conquer-bucket-based-sorting-f-0t9g/
 *
 *
 * Reference: https://leetcode.com/problems/array-partition
 */
public class ArrayPartition {

    public static void main(String[] args) {
        ArrayPartition helper = new ArrayPartition();
        System.out.println(helper.arrayPairSumApproach2(new int[]{ 6,2,6,5,1,2 }));
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;

        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }

        return sum;
    }

    public int arrayPairSumApproach2(int[] nums) {
        int[] bucket = new int[20001];
        for (int num : nums)
            bucket[num + 10000]++;

        int sum = 0;
        boolean add = true;

        for (int i = 0; i < 20001; i++) {
            while (bucket[i] > 0) {
                if (add)
                    sum += i - 10000;
                add = !add;
                bucket[i]--;
            }
        }

        return sum;
    }

}
