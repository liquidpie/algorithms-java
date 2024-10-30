package com.vivek.array.pattern.cyclesort;

/**
 * Find the Duplicate Number (easy)
 *
 * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’.
 * The array has only one duplicate but it can be repeated multiple times.
 * Find that duplicate number without using any extra space.
 * You are, however, allowed to modify the input array.
 *
 * Example 1:
 * Input: [1, 4, 4, 3, 2]
 * Output: 4
 *
 * Example 2:
 * Input: [2, 1, 3, 3, 5, 4]
 * Output: 3
 *
 * Example 3:
 * Input: [2, 4, 1, 4, 4]
 * Output: 4
 *
 * Solution #
 * This problem follows the Cyclic Sort pattern and shares similarities with Find the Missing Number.
 * Following a similar approach, we will try to place each number on its correct index.
 * Since there is only one duplicate, if while swapping the number with its index both the numbers being swapped are same,
 * we have found our duplicate!
 *
 * Time complexity #
 * The time complexity of the above algorithm is O(n).
 *
 * Space complexity #
 * The algorithm runs in constant space O(1) but modifies the input array.
 */
public class DuplicateNumberArrayModified {

    public static void main(String[] args) {
        System.out.println(findNumber(new int[]{1, 4, 4, 3, 2}));
        System.out.println(findNumber(new int[]{2, 1, 3, 3, 5, 4}));
        System.out.println(findNumber(new int[]{2, 4, 1, 4, 4}));
    }

    static int findNumber(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int j = arr[i] - 1;
            if (arr[i] != arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else if (arr[i] == arr[j] && i != j) {
                return arr[i];
            } else {
                i++;
            }
        }
        return -1;
    }

}
