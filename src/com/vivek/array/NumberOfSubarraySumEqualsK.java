package com.vivek.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Subarray Sum Equals K
 *
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * Idea ::
 * The main idea is to use a hash map to store the frequency of prefix sums.
 *
 * So, we’ll iterate over the array, finding how many subarrays exist whose sum equals k and ends at the current point on each iteration.
 *
 * So, if the value of the prefix sum up to this point is ‘prefixSum,’ the next step is to determine how many prefix sums exist whose
 * value is (prefixSum – k). It can be found in O(1) by using the hash map, which stores the frequency of prefix sums.
 *
 * Time Complexity :
 * The time complexity of the above code is O(n) because we are traversing the array only once.
 *
 * Space Complexity :
 * The space complexity of the above code is O(n) because we are using a hash map to store the frequency of prefix sums.
 *
 * https://leetcode.com/problems/subarray-sum-equals-k
 */
public class NumberOfSubarraySumEqualsK {

    public static void main(String[] args) {
        int target = 2;
        int[] nums = {1,1,1,2,-1};
        System.out.println(countSubarrayWithSum(target, nums));
    }

    static int countSubarrayWithSum(int target, int[] nums) {
        int result = 0;
        Map<Integer, Integer> prefixSumFreq = new HashMap<>();
        int prefixSum = 0;
        prefixSumFreq.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            result += prefixSumFreq.getOrDefault(prefixSum - target, 0);
            prefixSumFreq.put(prefixSum, prefixSumFreq.getOrDefault(prefixSum, 0) + 1);
        }

        return result;
    }

}
