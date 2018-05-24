package com.vivek.array;

import java.util.Arrays;

/**
 * Find next greater number with same set of digits
 *
 * Given a number n, find the smallest number that has same set of digits as n and is greater than n.
 */
public class NextNumberSameDigits {

    /**
     1) If all digits sorted in descending order, then output is always “Not Possible”. For example, 4321.
     2) If all digits are sorted in ascending order, then we need to swap last two digits. For example, 1234.
     3) For other cases, we need to process the number from rightmost side (why? because we need to find the smallest of all greater numbers)

     You can now try developing an algorithm yourself.

     Following is the algorithm for finding the next greater number.
     I) Traverse the given number from rightmost digit, keep traversing till you find a digit which
     is smaller than the previously traversed digit. For example, if the input number is “534976”,
     we stop at 4 because 4 is smaller than next digit 9. If we do not find such a digit, then output is “Not Possible”.

     II) Now search the right side of above found digit ‘d’ for the smallest digit greater than ‘d’.
     For “534976″, the right side of 4 contains “976”. The smallest digit greater than 4 is 6.

     III) Swap the above found two digits, we get 536974 in above example.

     IV) Now sort all digits from position next to ‘d’ to the end of number.
     The number that we get after sorting is the output. For above example, we sort digits in bold 536974.
     We get “536479” which is the next greater number for input 534976.
     */
    void findNextGreater(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int n = arr.length;

        int i;

        // start from the rightmost digit and find the first digit smaller than the digit next to it
        for (i = n - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i])
                break;
        }

        if (i == 0) { // digits are arranged decreasing number
            System.out.println("NOT POSSIBLE");
            return;
        }

        // find the smallest digit greater than digit at i
        int x = arr[i - 1];
        int smallest = i;
        for (int j = i; j < n; j++) {
            if (arr[j] > x && arr[j] < smallest) {
                smallest = arr[j];
            }
        }

        // swap digits at i and smallest indices
        swap(arr, i, smallest);

        // sort numbers from index i to last digit
        Arrays.sort(arr, i, n);

        // output
        System.out.println(String.valueOf(arr));
    }

    void swap(char[] arr, int x, int y) {
        char ch = arr[x];
        arr[x] = arr[y];
        arr[y] = ch;
    }

    public static void main(String[] args) {
        NextNumberSameDigits test = new NextNumberSameDigits();
        test.findNextGreater(534976);
    }
}
