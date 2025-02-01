package com.vivek.array.pattern.twopointer;

import java.util.Arrays;

/**
 * Triplet Sum Close to Target (medium)
 *
 * Given an array of unsorted numbers and a target number,
 * find a triplet in the array whose sum is as close to the target number as possible, return the sum of the triplet.
 * If there are more than one such triplet, return the sum of the triplet with the smallest sum.
 *
 * Example 1:
 * Input: [-2, 0, 1, 2], target=2
 * Output: 1
 * Explanation: The triplet [-2, 1, 2] has the closest sum to the target.
 *
 * Example 2:
 * Input: [-3, -1, 1, 2], target=1
 * Output: 0
 * Explanation: The triplet [-3, 1, 2] has the closest sum to the target.
 *
 * Example 3:
 * Input: [1, 0, 1, 1], target=100
 * Output: 3
 * Explanation: The triplet [1, 1, 1] has the closest sum to the target.
 *
 * Solution #
 * This problem follows the Two Pointers pattern and is quite similar to Triplet Sum to Zero.
 * We can follow a similar approach to iterate through the array, taking one number at a time.
 * At every step, we will save the difference between the triplet and the target number, so that in the end,
 * we can return the triplet with the closest sum.
 *
 * Time complexity #
 * Sorting the array will take O(N ∗ logN). Overall searchTriplet() will take O(N ∗ logN + N2), which is
 * asymptotically equivalent to O(N2).
 *
 * Space complexity #
 * The space complexity of the above algorithm will be O(N ) which is required for sorting.
 *
 * Reference:
 *
 * Grokking the Coding Interview
 * Pattern: Two Pointer
 */
public class TripletSumCloseToTarget {

    public static void main(String[] args) {
        System.out.println(searchTriplet(new int[] { -2, 0, 1, 2 }, 2));
        System.out.println(searchTriplet(new int[] { -3, -1, 1, 2 }, 1));
        System.out.println(searchTriplet(new int[] { 1, 0, 1, 1 }, 100));
    }

    public static int searchTriplet(int[] arr, int targetSum) {
        if (arr == null || arr.length < 3)
            throw new IllegalArgumentException();

        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int tripletSum = arr[i] + arr[left] + arr[right];
                if (tripletSum == targetSum) {
                    return 0;
                }
                else if (tripletSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
                minDiff = Math.min(minDiff, Math.abs(targetSum - tripletSum));
            }
        }

        return targetSum - minDiff;
    }

}
