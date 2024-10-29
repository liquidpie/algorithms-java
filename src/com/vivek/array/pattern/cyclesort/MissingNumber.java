package com.vivek.array.pattern.cyclesort;

/**
 * Missing Number
 *
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 * Example 1:
 *
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3].
 * 2 is the missing number in the range since it does not appear in nums.
 *
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2].
 * 2 is the missing number in the range since it does not appear in nums.
 *
 * Example 3:
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9].
 * 8 is the missing number in the range since it does not appear in nums.
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
        System.out.println(missingNumber(nums));
    }

    static int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = (n * (n + 1)) / 2;
        for (int num : nums) {
            sum -= num;
        }

        return sum;
    }

    /**
     * Time complexity #
     * The time complexity of the above algorithm is O(n). In the while loop,
     * although we are not incrementing the index i when swapping the numbers,
     * this will result in more than ‘n’ iterations of the loop,
     * but in the worst- case scenario, the while loop will swap a total of ‘n-1’ numbers
     * and once a number is at its correct index,
     * we will move on to the next number by incrementing i .
     * In the end, we iterate the input array again to find the first number missing from its index,
     * so overall, our algorithm will take O(n) + O(n − 1) + O(n) which is asymptotically equivalent to O(n).
     *
     * Space complexity #
     * The algorithm runs in constant space O(1).
     *
     * Reference:
     * Grokking the Coding Interview
     * Pattern: Cycle Sort
     */
    static int usingCycleSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] < arr.length && arr[i] != arr[arr[i]]) {
                int temp = arr[i];
                arr[i] = arr[temp];
                arr[temp] = temp;
            } else {
                i++;
            }
        }

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != j)
                return j;
        }
        return arr.length;
    }
}
