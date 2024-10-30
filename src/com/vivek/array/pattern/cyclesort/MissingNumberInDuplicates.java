package com.vivek.array.pattern.cyclesort;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all Missing Numbers (easy)
 *
 * We are given an unsorted array containing numbers taken from the range 1 to ‘n’. The array can have
 * duplicates, which means some numbers will be missing. Find all those missing numbers.
 *
 * Example 1:
 * Input: [2, 3, 1, 8, 2, 3, 5, 1]
 * Output: 4, 6, 7
 * Explanation: The array should have all numbers from 1 to 8, due to duplicates 4, 6, and 7 are missing
 *
 * Example 2:
 * Input: [2, 4, 1, 2]
 * Output: 3
 *
 * Example 3:
 * Input: [2, 3, 2, 1]
 * Output: 4
 *
 * Time complexity #
 * The time complexity of the above algorithm is O(n).
 *
 * Space complexity #
 * Ignoring the space required for the output array, the algorithm runs in constant space O(1).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Cycle Sort
 */
public class MissingNumberInDuplicates {

    public static void main(String[] args) {
        System.out.println(findNumbers(new int[]{2, 3, 1, 8, 2, 3, 5, 1}));
        System.out.println(findNumbers(new int[]{2, 4, 1, 2}));
        System.out.println(findNumbers(new int[]{2, 3, 2, 1}));
    }

    static List<Integer> findNumbers(int[] arr) {
        List<Integer> missingNumbers = new ArrayList<>();
        int i = 0;
        while (i < arr.length) {
            int j = arr[i] - 1;
            if (arr[i] != arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
                i++;
            }
        }

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != j + 1) {
                missingNumbers.add(j + 1);
            }
        }
        return missingNumbers;
    }

}
