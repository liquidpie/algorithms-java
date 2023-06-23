package com.vivek.dp;

/**
 * House Robber II
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 3
 *
 * Analysis ::
 *
 * This is an extension of House Robber. There are two cases here
 * 1) 1st element is included and last is not included
 * 2) 1st is not included and last is included. Therefore, we can use the similar dynamic programming approach to scan
 *    the array twice and get the larger value.
 *
 * Algorithm ::
 *  1. If the array is empty, return 0
 *  2. If the array has just one element, return that element
 *  3. Create a function robLinear() that returns the maximum sum that can be obtained in a linear array
 *  4. robLinear() works as follows:
 *      4.1. Initiate two variables included and excluded as 0, which stores the max results that be obtained by including and excluding the current element respectively
 *      4.2. At every next iteration, included becomes the current element + excluded result of previous element
 *      4.3. Excluded becomes the maximum of included and excluded results of previous element
*   5. Return the maximum of robLinear(1 , N – 1) and robLinear(2 , N)
 *
 * https://www.programcreek.com/2014/05/leetcode-house-robber-ii-java/
 *
 * https://tutorialcup.com/leetcode-solutions/house-robber-ii-leetcode-solution.htm
 *
 * https://leetcode.com/problems/house-robber-ii
 */
public class HouseRobberII {

    public static void main(String[] args) {
        System.out.println(rob(new int[] {1,2,3,1}));
    }

    static int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int max1 = robHelper(nums, 0, nums.length - 2) ;
        int max2 = robHelper(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    static int robHelper(int[] nums, int l, int r) {
        if (l == r)
            return nums[l];
        int[] dp = new int[nums.length];
        dp[l] = nums[l];
        dp[l + 1] = Math.max(nums[l + 1], dp[l]);

        for (int k = l + 2; k <= r; k++) {
            dp[k] = Math.max(dp[k - 1], nums[k] + dp[k - 2]);
        }

        return dp[r];
    }

}
