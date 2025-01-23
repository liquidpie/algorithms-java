package com.vivek.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function that takes a list of integers and outputs a list of pairs representing the consecutive ranges
 * contained in the input list. Note: the input list is sorted.
 *
 * arr1 = [1,3,4]
 * Output = [(1,1),(3,4)]
 *
 * arr2 = [1,2,3,4,5]
 * Output = [(1,5)]
 *
 * arr3 = [1,1,1,3,4,5,6,7,9]
 * Output = [(1,1), (3,7), (9,9)]
 *
 * Reference:
 * https://leetcode.com/discuss/interview-question/1965481/shopify-phone-consecutive-ranges
 */
public class ConsecutiveRanges {

    public static void main(String[] args) {
        List<Integer> arr1 = List.of(1, 3, 4);
        System.out.println(consecutiveRanges(arr1));

        List<Integer> arr2 = List.of(1, 2, 3, 4, 5);
        System.out.println(consecutiveRanges(arr2));

        List<Integer> arr3 = List.of(1, 1, 1, 3, 4, 5, 6, 7, 9);
        System.out.println(consecutiveRanges(arr3));

        List<Integer> arr4 = List.of(1, 1, 1, 2, 3);
        System.out.println(consecutiveRanges(arr4));
    }

    static List<List<Integer>> consecutiveRanges(List<Integer> list) {

        List<List<Integer>> result = new ArrayList<>();

        int start = 0;
        int end = 0;
        int current = 1;
        while (current < list.size()) {
            if (list.get(current) - list.get(end) <= 1) {
                end++;
            } else {
                result.add(List.of(list.get(start), list.get(end)));
                start = current;
                end = current;
            }
            current++;
        }

        // add the remaining pair
        result.add(List.of(list.get(start), list.get(end)));

        return result;
    }

}
