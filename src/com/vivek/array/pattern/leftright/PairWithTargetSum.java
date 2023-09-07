package com.vivek.array.pattern.leftright;

import java.util.Arrays;

/**
 * Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the
 * given target.
 * Write a function to return the indices of the two numbers (i.e. the pair) such that they add up to the given
 * target.
 *
 * Example 1:
 * Input: [1, 2, 3, 4, 6], target=6
 * Output: [1, 3]
 * Explanation: The numbers at index 1 and 3 add up to 6: 2+4=6
 *
 * Example 2:
 * Input: [2, 5, 9, 11], target=11
 * Output: [0, 2]
 * Explanation: The numbers at index 0 and 2 add up to 11: 2+9=11
 *
 * Solution:
 * We can follow the Two Pointers approach. We will start with one pointer pointing to the beginning of the array and another pointing at the end. At every step, we will see if the numbers pointed by the two pointers add up to the target sum. If they do, we have found our pair; otherwise, we will do one of two things:
 * 1. If the sum of the two numbers pointed by the two pointers is greater than the target sum,
 * this means that we need a pair with a smaller sum. So, to try more pairs, we can decrement the end-pointer.
 * 2. If the sum of the two numbers pointed by the two pointers is smaller than the target sum,
 * this means that we need a pair with a larger sum. So, to try more pairs, we can increment the start-pointer.
 *
 * Time Complexity #
 *      The time complexity of the above algorithm will be O(N), where ‘N’ is the total number of elements in the
 *      given array.
 * Space Complexity #
 *      The algorithm runs in constant space O(1).
 *
 * An Alternate approach #
 * Instead of using a two-pointer or a binary search approach, we can utilize a HashTable to search for the required pair.
 * We can iterate through the array one number at a time. Let’s say during our iteration we are at number ‘X’,
 * so we need to find ‘Y’ such that “X + Y == T arget”. We will do two things here:
 *  1. Search for ‘Y’ (which is equivalent to “T arget − X”) in the HashTable. If it is there, we have found the required pair.
 *  2. Otherwise, insert “X” in the HashTable, so that we can search it for the later numbers.
 */
public class PairWithTargetSum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6};
        int target = 6;
        System.out.println(Arrays.toString(search(arr, target)));
    }

    static int[] search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[] {left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {-1, -1};
    }

}
