package com.vivek.greedy;

/**
 * Jump Game II
 *
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 *     0 <= j <= nums[i] and
 *     i + j < n
 *
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 * https://www.youtube.com/watch?v=a9r98S-pDvk
 */
public class JumpGame2 {

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }

    static int jump(int[] nums) {
        int currReach = 0;
        int currMax = 0;
        int jumps = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (i + nums[i] > currMax)
                currMax = i + nums[i];

            if (i == currReach) {
                jumps++;
                currReach = currMax;
            }
        }

        return jumps;
    }

}
