package com.vivek.array;

/**
 * 334. Increasing Triplet Subsequence
 *
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k)
 * such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 *
 * Example 2:
 *
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 *
 * Example 3:
 *
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: One of the valid triplet is (3, 4, 5), because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 *
 * Reference:
 * https://leetcode.com/problems/increasing-triplet-subsequence
 */
public class IncreasingTripletSequence {

    public static void main(String[] args) {
        IncreasingTripletSequence helper = new IncreasingTripletSequence();
        int[] nums = {1,6,2,5,1};
        System.out.println(helper.increasingTriplet(nums));
    }

    /**
     * We’re looking for three numbers in increasing order. Brute force would be too slow.
     * Instead, we track the smallest and second smallest numbers seen so far — if we find a number larger than both, the triplet exists.
     *
     * Initialize two variables (first, second) as infinity. Iterate through the array:
     *
     *     If num <= first, update first.
     *     Else if num <= second, update second.
     *     Else, return true — we found a triplet.
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return false;

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }

        return false;
    }


    /**
     * suppose there is a NUMS[i]....think in that way what if we already know the smallest number in between
     * 0 to i th index and greatest number in between n-1 to i th index.
     * And we know this for every index in nums array
     * Now we have to just iterate over nums and check that the curr number is not equal to smallest number till
     * that index and also it is not equal to the greatest number till that index....in that case just return TRUE
     * and return False if we iterate over nums array and we didn't find any index that supports the condition!
     *
     * 1 --> Keep track of smallest number till the i th index from the starting ( do this for every index value )
     * 2 --> keep track of greater number till the i th index from the last ( do this for every index value )
     * 3 --> check for every index value
     *
     * nums[i] != smallest till now from the start && nums[i] != greatest till now from the last
     * if it matches then return true
     * otherwise in the last return false
     */
    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return false;

        int[] smallest = new int[n];
        int[] largest = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                smallest[i] = nums[i];
            } else {
                smallest[i] = Math.min(nums[i], smallest[i - 1]);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                largest[i] = nums[i];
            } else {
                largest[i] = Math.max(nums[i], largest[i + 1]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > smallest[i] && nums[i] < largest[i])
                return true;
        }

        return false;
    }

}
