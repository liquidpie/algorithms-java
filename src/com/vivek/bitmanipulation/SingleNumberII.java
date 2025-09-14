package com.vivek.bitmanipulation;

/**
 * 137. Single Number II
 *
 * Given an integer array nums where every element appears three times except for one, which appears exactly once.
 * Find the single element and return it.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,2]
 * Output: 3
 *
 * Example 2:
 *
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 * Explanation:
 *
 *     Initialize the variable ans to 0. This variable will store the resulting single number.
 *
 *     Iterate from i = 0 to i = 31. This loop considers each bit position from the least significant bit (LSB) to the most significant bit (MSB) of a 32-bit integer.
 *
 *     Inside the loop, initialize a variable sum to 0. This variable will keep track of the number of 1s at the current bit position (i) for all the numbers in the input array.
 *
 *     Iterate through each number num in the input array:
 *         Right-shift num by i positions: num >> i. This operation moves the bit at position i to the least significant bit position.
 *         Perform a bitwise AND with 1: (num >> i) & 1. This extracts the value of the bit at position i from num. If it is 1, the result will be 1; otherwise, it will be 0.
 *         Add the result of (num >> i) & 1 to sum. This counts the number of 1s at bit position i for all the numbers in the array.
 *
 *     Take the modulo of sum by 3: sum %= 3. This step is performed to handle the numbers that appear three times. If sum is divisible by 3, it means the bit at position i has a balanced number of 1s. Otherwise, it is an unbalanced line.
 *
 *     Left-shift the value of sum by i positions: sum << i. This step creates a bitmask pos where only the bit at position i is set to the value of sum. This bitmask identifies the position of the unbalanced line.
 *
 *     Use the bitwise OR operation with ans and pos: ans |= pos. This sets the corresponding bit in ans to 1 if the bit at position i is part of an unbalanced line.
 *
 *     After the loop completes, the value stored in ans represents the single number that appears only once in the array.
 *
 * Reference: https://leetcode.com/problems/single-number-ii
 */
public class SingleNumberII {

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (final int num : nums) {
                sum += num >> i & 1;
            }
            sum %= 3;
            ans |= sum << i;
        }

        return ans;
    }

}
