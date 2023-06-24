package com.vivek.array.pattern.leftright;

/**
 * Capacity To Ship Packages Within D Days
 *
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 *
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 *
 * Example 1:
 *
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Example 2:
 *
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 *
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 *
 * Intuition
 * Logic : capacity can only be between the maximum of the array to sum of the array.
 *
 * Approach
 * We initialize start of the array from max of weights[] and end to sum of all elements of weights.
 * We will then perform binary search from start to end and pass the mid value to a function which will check whether can we make groups less than or equal to the mid value
 * Complexity
 *
 * Time complexity: O( nlogn )
 * Bcoz a binary search which runs on logn and inside every binary search a linear loop runs on n.
 * Space complexity: O( 1 )
 * There's no extra space utilised.
 *
 * Solution: https://tutorialcup.com/leetcode-solutions/capacity-to-ship-packages-within-d-days-leetcode-solution.htm
 *
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days
 */
public class CapacityShipPackagesWithinDays {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int d = 5;
        int ans = shipWithinDays(arr,d);
        System.out.println(ans);
    }

    static int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int w: weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2, requirement = 1, tillnow = 0;
            for (int w: weights) {
                if (tillnow + w > mid) {
                    requirement += 1;
                    tillnow = 0;
                }
                tillnow += w;
            }
            if (requirement > days) left = mid + 1;
            else right = mid;
        }
        return left;
    }


}
