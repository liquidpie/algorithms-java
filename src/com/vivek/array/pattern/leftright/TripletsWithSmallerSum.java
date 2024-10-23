package com.vivek.array.pattern.leftright;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Triplets with Smaller Sum (medium)
 *
 * Given an array arr of unsorted numbers and a target sum, count all triplets in it such that
 * arr[i] + arr[j] + arr[k] < target where i , j , and k are three different indices.
 * Write a function to return the count of such triplets.
 *
 * Example 1:
 * Input: [-1, 0, 2, 3], target=3
 * Output: 2
 * Explanation: There are two triplets whose sum is less than the target: [-1, 0, 3], [-1, 0, 2]
 *
 * Example 2:
 * Input: [-1, 4, 2, 1, 3], target=5
 * Output: 4
 * Explanation: There are four triplets whose sum is less than the target:
 *    [-1, 1, 4], [-1, 1, 3], [-1, 1, 2], [-1, 2, 3]
 *
 * Solution #
 * This problem follows the Two Pointers pattern and shares similarities with Triplet Sum to Zero.
 * The only difference is that, in this problem, we need to find the triplets whose sum is less than the given target.
 * To meet the condition i != j != k we need to make sure that each number is not used more than once.
 *
 * Following a similar approach, first, we can sort the array and then iterate through it, taking one number at a time.
 * Let’s say during our iteration we are at number ‘X’, so we need to find ‘Y’ and ‘Z’ such that X + Y + Z < target.
 * At this stage, our problem translates into finding a pair whose sum is less than “ target − X”
 * (as from the above equation Y + Z == target − X). We can use a similar approach as discussed in Triplet Sum to Zero.
 *
 * Time complexity - O(N2)
 * Space complexity #
 * The space complexity of the above algorithm will be O(N ) which is required for sorting if we are not using an in-place sorting algorithm.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Two Pointer
 */
public class TripletsWithSmallerSum {

    public static void main(String[] args) {
        System.out.println(searchTriplets(new int[] { -1, 0, 2, 3 }, 3));
        System.out.println(getTriplets(new int[] { -1, 0, 2, 3 }, 3));
        System.out.println(searchTriplets(new int[] { -1, 4, 2, 1, 3 }, 5));
        System.out.println(getTriplets(new int[] { -1, 4, 2, 1, 3 }, 5));
    }

    public static int searchTriplets(int[] arr, int target) {
        if (arr == null || arr.length < 3)
            throw new IllegalArgumentException();

        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int tripletSum = arr[i] + arr[left] + arr[right];
                if (tripletSum < target) {
                    // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between
                    // left and right to get a sum less than the target sum
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    /**
     * Similar Problems #
     * Problem: Write a function to return the list of all such triplets instead of the count. How will the time
     * complexity change in this case?
     *
     * Time complexity #
     * So, overall searchTriplets() will take O(N ∗ logN + N3), which is asymptotically equivalent to O(N3).
     *
     * Space complexity #
     * Ignoring the space required for the output array, the space complexity of the above algorithm will be O(N) which is required for sorting.
     */
    public static List<List<Integer>> getTriplets(int[] arr, int target) {
        if (arr == null || arr.length < 3)
            throw new IllegalArgumentException();

        Arrays.sort(arr);
        List<List<Integer>> triplets = new LinkedList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int tripletSum = arr[i] + arr[left] + arr[right];
                if (tripletSum < target) {
                    // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between
                    // left and right to get a sum less than the target sum
                    for (int j = right; j > left; j--) {
                        triplets.add(List.of(arr[i], arr[left], arr[j]));
                    }
                    left++;
                } else {
                    right--;
                }
            }
        }

        return triplets;
    }

}
