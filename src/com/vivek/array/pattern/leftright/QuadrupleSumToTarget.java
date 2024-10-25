package com.vivek.array.pattern.leftright;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Quadruple Sum to Target (medium)
 *
 * Given an array of unsorted numbers and a target number, find all unique quadruplets in it, whose sum is
 * equal to the target number.
 *
 * Example 1:
 * Input: [4, 1, 2, -1, 1, -3], target=1
 * Output: [-3, -1, 1, 4], [-3, 1, 1, 2]
 * Explanation: Both the quadruplets add up to the target.
 *
 * Example 2:
 * Input: [2, 0, -1, 1, -2, 2], target=2
 * Output: [-2, 0, 2, 2], [-1, 0, 1, 2]
 * Explanation: Both the quadruplets add up to the target.
 *
 * Time complexity #
 * Sorting the array will take O(N ∗ logN). Overall searchQuadruplets() will take O(N ∗ logN + N3),
 * which is asymptotically equivalent to O(N3).
 *
 * Space complexity #
 * The space complexity of the above algorithm will be O(N ) which is required for sorting.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Two Pointer
 */
public class QuadrupleSumToTarget {

    public static void main(String[] args) {
        System.out.println(searchQuadruplets(new int[] { 4, 1, 2, -1, 1, -3 }, 1));
        System.out.println(searchQuadruplets(new int[] { 2, 0, -1, 1, -2, 2 }, 2));
    }

    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> quadruplets = new ArrayList<>();
        for (int i = 0; i < arr.length - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) // skip duplicate elements
                continue;
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1])
                    continue; // skip duplicate elements
                searchPairs(arr, target, i, j, quadruplets);
            }
        }
        return quadruplets;
    }

    private static void searchPairs(int[] arr, int target, int first, int second, List<List<Integer>> quadruplets) {
        int left = second + 1;
        int right = arr.length - 1;
        while (left < right) {
            int sum = arr[first] + arr[second] + arr[left] + arr[right];
            if (sum == target) {
                quadruplets.add(List.of(arr[first], arr[second], arr[left], arr[right]));
                left++;
                right--;
                while (left < right && arr[left] == arr[left - 1]) // skip same element to avoid duplicate quadruplets
                    left++;
                while (left < right && arr[right] == arr[right + 1]) // skip same element to avoid duplicate quadruplets
                    right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

    }

}
