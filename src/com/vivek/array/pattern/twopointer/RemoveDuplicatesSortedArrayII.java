package com.vivek.array.pattern.twopointer;

/**
 * 80. Remove Duplicates from Sorted Array II
 *
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Example 2:
 *
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Solution:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/solutions/4062386/0-n-easy-to-understand-solution/?envType=study-plan-v2&envId=top-interview-150
 *
 * Here's the approach implemented in the code:
 *
 *     Initialize an integer variable i to 0. This variable will keep track of the current position in the modified nums vector.
 *
 *     Use a for loop to iterate through each element ele in the nums vector using the range-based for loop.
 *
 *     Inside the loop, check the following conditions:
 *
 *     i == 0: This condition ensures that the first element is always included in the modified vector.
 *     i == 1: This condition ensures that the second element is always included in the modified vector.
 *     nums[i-2] != ele: This condition checks if the current element ele is not the same as the element two positions before the current position i. This ensures that only two occurrences of any element are included in the modified vector.
 *
 *     If any of the above conditions are met, copy the current element ele to the nums[i] position in the modified vector, and increment i by 1 to move to the next position.
 *
 *     Repeat this process for all elements in the nums vector.
 *
 *     Finally, return the value of i, which represents the length of the modified vector with duplicates removed.
 *
 * Reference:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii
 */
public class RemoveDuplicatesSortedArrayII {

    public static void main(String[] args) {
        RemoveDuplicatesSortedArrayII helper = new RemoveDuplicatesSortedArrayII();
        {
            int[] nums = {1,1,1,2,2,3};
            System.out.println(helper.removeDuplicates(nums));
        }
        {
            int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
            System.out.println(helper.removeDuplicates(nums));
        }
    }

    public int removeDuplicates(int[] nums) {
        int idx = 0;

        for (int num : nums) {

            if (idx == 0 || idx == 1 || nums[idx - 2] != num) {
                nums[idx] = num;
                idx++;
            }
        }

        return idx;
    }

}
