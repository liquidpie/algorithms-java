package com.vivek.array.pattern.cyclesort;

import java.util.Arrays;

/**
 * Find the Corrupt Pair (easy) #
 *
 * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’.
 * The array originally contained all the numbers from 1 to ‘n’, but due to a data error,
 * one of the numbers got duplicated which also resulted in one number going missing.
 * Find both these numbers.
 *
 * Solution #
 * This problem follows the Cyclic Sort pattern and shares similarities with Find all Duplicate Numbers.
 * Following a similar approach, we will place each number at its correct index. Once we are done with the cyclic sort,
 * we will iterate through the array to find the number that is not at the correct index.
 * Since only one number got corrupted, the number at the wrong index is the duplicated number
 * and the index itself represents the missing number.
 *
 * Example 1:
 * Input: [3, 1, 2, 5, 2]
 * Output: [2, 4]
 * Explanation: '2' is duplicated and '4' is missing.
 *
 * Example 2:
 * Input: [3, 1, 2, 3, 6, 4]
 * Output: [3, 5]
 * Explanation: '3' is duplicated and '5' is missing.
 *
 * Time complexity #
 * The time complexity of the above algorithm is O(n).
 *
 * Space complexity #
 * The algorithm runs in constant space O(1).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Cycle Sort
 */
public class CorruptPair {

    public static void main(String[] args) {
        {
            int[] result = corruptPair(new int[]{3, 1, 2, 5, 2});
            System.out.println(Arrays.toString(result));
        }
        {
            int[] result = corruptPair(new int[]{3, 1, 2, 3, 6, 4});
            System.out.println(Arrays.toString(result));
        }
    }

    static int[] corruptPair(int[] arr) {
        int[] result = new int[2];

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
                result[0] = arr[j];
                result[1] = j + 1;
            }
        }
        return result;
    }

}
