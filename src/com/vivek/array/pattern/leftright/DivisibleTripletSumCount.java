package com.vivek.array.pattern.leftright;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2964. Number of Divisible Triplet Sums
 *
 * Given a 0-indexed integer array nums and an integer d, return the number of triplets (i, j, k) such that
 * i < j < k and (nums[i] + nums[j] + nums[k]) % d == 0.
 *
 * Example 1:
 * Input: nums = [3,3,4,7,8], d = 5
 * Output: 3
 * Explanation: The triplets which are divisible by 5 are: (0, 1, 2), (0, 2, 4), (1, 2, 4).
 * It can be shown that no other triplet is divisible by 5. Hence, the answer is 3.
 *
 * Example 2:
 * Input: nums = [3,3,3,3], d = 3
 * Output: 4
 * Explanation: Any triplet chosen here has a sum of 9, which is divisible by 3. Hence, the answer is the total number of triplets which is 4.
 *
 * Example 3:
 * Input: nums = [3,3,3,3], d = 6
 * Output: 0
 * Explanation: Any triplet chosen here has a sum of 9, which is not divisible by 6. Hence, the answer is 0.
 *
 * Approach::
 *
 * This problem is really similar to 3Sum, which in turn is based off the infamous Two Sum and Two Sum II.
 * However, one key difference is i < j < k and (nums[i] + nums[j] + nums[k]) % d == 0.
 * One important thing to note is that we don't have to filter out duplicate triplets.
 *
 * In other words,
 *
 * Input: nums = [3,3,4,8], d = 15
 * Output: 2
 * Explanation: The valid triplets are (0, 2, 4), (1, 2, 4). Since the elements in index 0 and 1 are the same,
 *              we can create two tuples where the elements are [3, 4, 8]
 *
 * For any triplet (nums[x] + nums[y] + nums[z]) must satisfy:
 *  - (nums[x] + nums[y] + nums[z]) % d = 0
 *  This means:
 *  - (nums[x] + nums[y]) % d + nums[z] % d = 0 (mod d)
 *  Rearranging:
 *  - (nums[x] + nums[y]) % d = -(nums[z] % d) (mod d)
 *
 *  If (nums[x] + nums[y]) % d must equal -(nums[z] % d), this is equivalent to:
 *      remainder = d - (nums[x] % d)
 *
 *      Adding d ensures the result is non-negative
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 *
 * https://leetcode.com/problems/number-of-divisible-triplet-sums/description/
 */
public class DivisibleTripletSumCount {

    public static void main(String[] args) {
        {
            int[] nums = {3, 3, 4, 7, 8};
            int d = 5;
            System.out.println(divisibleTripletCount(nums, d)); // Output: 3
        }
        {
            int[] nums = {3, 3, 3, 3};
            int d = 3;
            System.out.println(divisibleTripletCount(nums, d)); // Output: 4
        }
        {
            int[] nums = {3, 3, 3, 3};
            int d = 6;
            System.out.println(divisibleTripletCount(nums, d)); // Output: 0
        }
    }

    static int divisibleTripletCount(int[] nums, int d) {
        int n = nums.length;
        // remainder to occurrence count
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 2; i < n; i++) {
            for (int j = 0; j < i - 1; j++) {
                int remainder = (nums[j] + nums[i - 1]) % d;
                map.put(remainder, map.getOrDefault(remainder, 0) + 1);
            }
            int target = (d - nums[i] % d) % d;
            count += map.getOrDefault(target, 0);
        }

        return count;
    }

}
