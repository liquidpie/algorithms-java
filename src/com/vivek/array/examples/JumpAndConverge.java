package com.vivek.array.examples;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A jump means adding or subtracting 1.
 * “Converge” means all the elements get to the same value.
 * Given an integer array, find if all the elements could converge with 1 jump (or less) and return the converge value.
 *
 * input elements could be numbers between 0 and 9 inclusive
 * 9 plus 1, gives you 0
 * 0 minus 1, gives you 9
 *
 * Test Case
 * [ 1, 0, 9, 9, 1, 1, 0, 3]
 * Output = -1
 *
 * As if you jump down from 1 to 0 on all 1s, and you jump up on all 9 to 0, all numbers are going to converge into 0 value.
 * So your output is the converge value, 0 in this case. Return -1 if there is no convergence like would be the case
 * if in the example we add a “3” at the end (or any other number).
 *
 * Solution:
 * because we can only jump one step, if difference between the largest and the smallest <=2 steps, it will work for all the other.
 * in order to handle 0 and 9, we not only check the original value for 0 but also the case of 0+10.
 *
 * https://leetcode.com/discuss/interview-question/2636856/Uber-Phone-Interview
 */
public class JumpAndConverge {

    public static void main(String[] args) {
        int[] nums = {1, 0, 9, 9, 1, 1, 0};
        System.out.println(convergeValue(nums));
    }

    // Todo: Not working
    static int convergeValue(int[] nums) {
        // get unique elements
        List<Integer> arr = new ArrayList<>(Set.copyOf(Arrays.stream(nums).boxed().collect(Collectors.toList())));
        // sort elements
        Collections.sort(arr);
        if (arr.size() == 1)
            return arr.get(0);

        int first = arr.get(0);
        // initialize result with first element possible combinations
//        int firstNext = first == 9 ? 0 : first + 1;
//        int firstPrev = first == 0 ? 9 : first - 1;
        List<Integer> result = List.of(first, first + 1, first - 1);
        for (int val : arr) {
            int next = val == 9 ? 0 : val + 1;
            int prev = val == 0 ? 9 : val - 1;

            // Filter everytime the common objects
            // if any common elemts is left then that is the converge element
            Predicate<Integer> notPredicate = (e) -> e == val || e == next || e == prev;
            result = result.stream().filter(notPredicate.negate()).toList();
        }

        return result.size() > 0 ? result.get(0) : -1;

    }

}
