package com.vivek.array.pattern.leftright;

/**
 * Remove Duplicates
 *
 * Given an array of sorted numbers, remove all duplicates from it.
 * You should not use any extra space; after removing the duplicates in-place return the new length of the array.
 *
 * Example 1
 * Input: [2, 3, 3, 3, 6, 9, 9]
 * Output: 4
 * Explanation: The first four elements after removing the duplicates will be [2, 3, 6, 9].
 *
 * Solution #
 * In this problem, we need to remove the duplicates in-place such that the resultant length of the array remains sorted.
 * As the input array is sorted, therefore, one way to do this is to shift the elements left whenever we encounter duplicates.
 * In other words, we will keep one pointer for iterating the array and one pointer for placing the next non-duplicate number.
 * So our algorithm will be to iterate the array and whenever we see a non-duplicate number we move it next to the
 * last non-duplicate number we’ve seen.
 *
 * Time Complexity #
 *      The time complexity of the above algorithm will be O(N), where ‘N’ is the total number of elements in the given array.
 * Space Complexity #
 *      The algorithm runs in constant space O(1).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Two-pointer
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {2, 3, 3, 3, 6, 9, 9};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;

        int nextNonDuplicate = 1;  // index of the next non-duplicate element
        for (int i = 1; i < arr.length; i++) {
            if (arr[nextNonDuplicate - 1] != arr[i]) {
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }
        return nextNonDuplicate;
    }

}
