package com.vivek.hashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 645. Set Mismatch
 *
 * You have a set of integers s, which originally contains all the numbers from 1 to n.
 * Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set,
 * which results in repetition of one number and loss of another number.
 *
 * You are given an integer array nums representing the data status of this set after the error.
 *
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 *
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [1,2]
 *
 * Reference: https://leetcode.com/problems/set-mismatch
 */
public class SetMismatch {

    public static void main(String[] args) {
        SetMismatch helper = new SetMismatch();
        int[] nums = {3, 2, 2};
        System.out.println(Arrays.toString(helper.findErrorNums(nums)));
    }

    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] result = new int[2];
        Set<Integer> set = new HashSet<>();
        int expectedSum = (n * (n + 1)) / 2;

        int total = 0;

        for (int i = 0; i < n; i++) {
            total += nums[i];
            if (!set.add(nums[i]))
                result[0] = nums[i];
        }

        result[1] = expectedSum - total + result[0];

        return result;
    }

}
