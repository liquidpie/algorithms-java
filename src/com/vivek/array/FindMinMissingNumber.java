package com.vivek.array;

/**
 * Find the smallest positive number missing from an unsorted array
 *

 Solution 2:

 We use array elements as index. To mark presence of an element x, we change the value at the index x to negative.
 But this approach doesn’t work if there are non-positive (-ve and 0) numbers.
 So we segregate positive from negative numbers as first step and then apply the approach.

 Following is the two step algorithm.
 1) Segregate positive numbers from others i.e., move all non-positive numbers to left side.
 In the following code, segregate() function does this part.
 2) Now we can ignore non-positive elements and consider only the part of array which contains all positive elements.
 We traverse the array containing all positive numbers and to mark presence of an element x,
 we change the sign of value at index x to negative. We traverse the array again
 and print the first index which has positive value. In the following code, findMissingPositive() function does this part.

 Note that in findMissingPositive, we have subtracted 1 from the values as indexes start from 0 in C.

 *
 * https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/
 */
public class FindMinMissingNumber {

    static int findMissing(int arr[], int size) {
        // First separate positive and negative numbers
        int shift = segregate(arr, size);
        return findMissingPositive(arr, size, shift);
    }

    /**
     *  Utility function that puts all non-positive (0 and negative) numbers on left side of
     *  arr[] and return count of such numbers
     */
    private static int segregate(int arr[], int size) {
        int j = 0;
        for(int i = 0; i < size; i++) {
            if (arr[i] <= 0) {
                int temp;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // increment count of non-positive integers
                j++;
            }
        }
        return j;
    }

    /**
     * Find the smallest positive missing number in an array that contains all positive integers
     */
    private static int findMissingPositive(int arr[], int size, int start) {

        // Mark arr[i] as visited by making arr[arr[i] - 1] negative. Note that
        // 1 is subtracted because index start from 0 and positive numbers start from 1
        for(int i = start; i < size; i++) {
            if(Math.abs(arr[i]) - 1 < size && arr[Math.abs(arr[i]) - 1] > 0)
                arr[Math.abs(arr[i]) - 1] = -arr[Math.abs(arr[i]) - 1];
        }

        // Return the first index value at which is positive
        for(int i = start; i < size; i++)
            if (arr[i] > 0)
                return i - start + 1;  // 1 is added because indexes and start is to remove offset

        return size + 1;
    }

    public static void main(String[] args) {
        int arr[] = {0, 10, 2, -10, -20};
        int arr_size = arr.length;
        int missing = findMissing(arr, arr_size);
        System.out.println("The smallest positive missing number is " +  missing);
    }

}
