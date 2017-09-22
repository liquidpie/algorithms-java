package com.vivek.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Consider an com.vivek.array of  integers, , where all but one of the integers occur in pairs.
 * In other words, every element in  occurs exactly twice except for one unique element.
 *
 * Given , find and print the unique element.
 */
public class XorLonelyInteger {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 1, 4, 3, 5, 4};
        int num;
        num = findLonelyIntegerByXor(arr);
//        num = findLonelyIntegerBySet(arr);
        System.out.println(num);
    }

    private static int findLonelyIntegerBySet(int[] arr) { // solution 1
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (!set.add(i)) {
                set.remove(i);
            }
        }
        Integer[] a = (Integer[]) set.toArray();
        return a[0].intValue();
    }

    private static int findLonelyIntegerByXor(int[] arr) { // solution 2
        int value = 0;
        for (int i : arr)
            value ^= i;
        return value;
    }

}
