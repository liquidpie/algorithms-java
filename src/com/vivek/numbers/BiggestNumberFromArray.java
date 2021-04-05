package com.vivek.numbers;

import java.util.Collections;
import java.util.Vector;

/**
 * Given an array of numbers, arrange them in a way that yields the largest value.
 * For example, if the given numbers are {54, 546, 548, 60}, the arrangement 6054854654 gives the largest value.
 * And if the given numbers are {1, 34, 3, 98, 9, 76, 45, 4}, then the arrangement 998764543431 gives the largest value.
 *
 * A simple solution that comes to our mind is to sort all numbers in descending order, but simply sorting doesn’t work.
 * For example, 548 is greater than 60, but in output 60 comes before 548. As a second example, 98 is greater than 9, but 9 comes before 98 in output.
 *
 * Given two numbers X and Y, we compare two numbers XY (Y appended at the end of X) and YX (X appended at the end of Y).
 * If XY is larger, then X should come before Y in output, else Y should come before. For example, let X and Y be 542 and 60.
 * To compare X and Y, we compare 54260 and 60542. Since 60542 is greater than 54260, we put Y first.
 */
public class BiggestNumberFromArray {

    static void printLargest(Vector<String> arr) {

        Collections.sort(arr, (X, Y) -> {

            // first append Y at the end of X
            String XY = X + Y;
            // then append X at the end of Y
            String YX = Y + X;

            return XY.compareTo(YX) > 0 ? -1 : 1;
        });

        for (String s : arr)
            System.out.print(s);
    }

    public static void main(String[] args) {

        Vector<String> arr;
        arr = new Vector<>();

        // output should be 6054854654
        arr.add("54");
        arr.add("546");
        arr.add("548");
        arr.add("60");
        printLargest(arr);
    }

}
