package com.vivek.array.pattern.cyclesort;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all Duplicate Numbers (easy)
 *
 * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’. The array has some
 * duplicates, find all the duplicate numbers without using any extra space.
 *
 * Example 1:
 * Input: [3, 4, 4, 5, 5]
 * Output: [4, 5]
 *
 * Example 2:
 * Input: [5, 4, 7, 2, 3, 5, 3]
 * Output: [3, 5]
 *
 * Solution #
 * This problem follows the Cyclic Sort pattern and shares similarities with Find the Duplicate Number.
 * Following a similar approach, we will place each number at its correct index.
 * After that, we will iterate through the array to find all numbers that are not at the correct indices.
 * All these numbers are duplicates.
 *
 * Time complexity #
 * The time complexity of the above algorithm is O(n).
 *
 * Space complexity #
 * Ignoring the space required for storing the duplicates, the algorithm runs in constant space O(1).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Cycle Sort
 */
public class AllDuplicateNumbers {

    public static void main(String[] args) {
        System.out.println(allDuplicates(new int[]{3, 4, 4, 5, 5}));
        System.out.println(allDuplicates(new int[]{5, 4, 7, 2, 3, 5, 3}));
    }

    static List<Integer> allDuplicates(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        int i = 0;
        while (i < arr.length) {
            int j = arr[i] - 1;
            if (arr[i] != arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }  else {
                i++;
            }
        }

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != j + 1)
                duplicates.add(arr[j]);
        }
        return duplicates;
    }

}
