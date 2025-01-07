package com.vivek.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Find the closest numbers in the array and print the pairs
 *
 * The closest numbers are the one with minimum absolute difference
 */
public class ClosestNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(2, 6, 4, 8, 11, 15));
        printClosestNumbers(numbers);
    }

    static void printClosestNumbers(List<Integer> numbers) {
        Collections.sort(numbers);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < numbers.size(); i++) {
            int diff = Math.abs(numbers.get(i) - numbers.get(i - 1));
            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        for (int i = 1; i < numbers.size(); i++) {
            if (Math.abs(numbers.get(i) - numbers.get(i - 1)) == minDiff) {
                System.out.print(numbers.get(i - 1) + " " + numbers.get(i));
                System.out.println();
            }
        }
    }

}
