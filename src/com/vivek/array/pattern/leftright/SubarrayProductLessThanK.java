package com.vivek.array.pattern.leftright;

import java.util.ArrayList;
import java.util.List;

/**
 * Subarrays with Product Less than a Target (medium)
 *
 * Given an array with positive numbers and a target number, find all of its contiguous subarrays whose
 * product is less than the target number.
 *
 * Example 1:
 * Input: [2, 5, 3, 10], target=30
 * Output: [2], [5], [2, 5], [3], [5, 3], [10]
 * Explanation: There are six contiguous subarrays whose product is less than the target
 *
 * Example 2:
 * Input: [8, 2, 6, 5], target=50
 * Output: [8], [2], [8, 2], [6], [2, 6], [5], [6, 5]
 * Explanation: There are seven contiguous subarrays whose product is less than the target.
 *
 * Solution #
 * This problem follows the Sliding Window and the Two Pointers pattern and shares similarities with Triplets
 * with Smaller Sum with two differences:
 * 1. In this problem, the input array is not sorted.
 * 2. Instead of finding triplets with sum less than a target, we need to find all subarrays having a product less than the target.
 *
 * Time complexity #
 * The main for-loop managing the sliding window takes O(N) but creating subarrays can take up to O(N2)
 * in the worst case. Therefore overall, our algorithm will take O(N3).
 *
 * Space complexity #
 * Ignoring the space required for the output list, the algorithm runs in O(N ) space which is used for the temp list.
 * Can you try estimating how much space will be required for the output list?
 *
 * It is not all the Combinations of all elements of the array!
 * For an array with distinct elements, finding all of its contiguous subarrays is like finding the number of ways
 * to choose two indices i and j in the array such that i <= j .
 * If there are a total of n elements in the array, here is how we can count all the contiguous subarrays:
 * When i = 0 , j can have any value from ‘0’ to ‘n-1’, giving a total of ‘n’ choices.
 * When i = 1 , j can have any value from ‘1’ to ‘n-1’, giving a total of ‘n-1’ choices.
 * Similarly, when i = 2 , j can have ‘n-2’ choices.
 * ...
 * ...
 * When i = n-1 , j can only have ‘1’ choice. Let’s combine all the choices:
 * n + (n-1) + (n-2) + ... 3 + 2 + 1 Which gives us a total of: n ∗ (n + 1)/2
 * So, at the most, we need a space of O(n2) for all the output lists.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Two Pointer
 */
public class SubarrayProductLessThanK {

    public static void main(String[] args) {
        System.out.println(findSubarrays(new int[] { 2, 5, 3, 10 }, 30));
        System.out.println(findSubarrays(new int[] { 8, 2, 6, 5 }, 50));
    }

    public static List<List<Integer>> findSubarrays(int[] arr, int k) {
        List<List<Integer>> subarrays = new ArrayList<>();
        int product = 1;
        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];

            while (product >= k && left < arr.length) {
                product /= arr[left++];
            }

            // since the product of all numbers from left to right is less than the target therefore,
            // all subarrays from left to right will have a product less than the target too; to avoid
            // duplicates, we will start with a subarray containing only arr[right] and then extend it
            List<Integer> list = new ArrayList<>();
            for (int i = right; i >= left; i--) {
                list.add(0, arr[i]);
                subarrays.add(new ArrayList<>(list));
            }

        }
        return subarrays;
    }

}
