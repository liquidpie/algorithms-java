package com.vivek.array;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Find k numbers with most occurrences in the given array
 *
 * Given an array of n numbers and a positive integer k.
 * The problem is to find k numbers with most occurrences, i.e., the top k numbers having the maximum frequency.
 * If two numbers have the same frequency then the larger number should be given preference.
 * The numbers should be displayed in decreasing order of their frequencies.
 * It is assumed that the array consists of k numbers with most occurrences.
 *
 *     Input:
 *     arr[] = {3, 1, 4, 4, 5, 2, 6, 1},
 *     k = 2
 *     Output: 4 1
 *     Explanation:
 *     Frequency of 4 = 2
 *     Frequency of 1 = 2
 *     These two have the maximum frequency and
 *     4 is larger than 1.
 *
 *
 *     Input :
 *     arr[] = {7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9},
 *     k = 4
 *     Output: 5 11 7 10
 *     Explanation:
 *     Frequency of 5 = 3
 *     Frequency of 11 = 2
 *     Frequency of 7 = 2
 *     Frequency of 10 = 1
 *     These four have the maximum frequency and
 *     5 is largest among rest.
 *
 * References:
 * https://www.geeksforgeeks.org/find-k-numbers-occurrences-given-array
 */
public class MostOccurredKElements {

    /**
     * Algorithm :
     *
     *     Create a Hashmap hm, to store key-value pair, i.e. element-frequency pair.
     *     Traverse the array from start to end.
     *     For every element in the array update hm[array[i]]++
     *     Store the element-frequency pair in a Priority Queue and create the Priority Queue, this takes O(n) time.
     *     Run a loop k times, and in each iteration remove the top of the priority queue and print the element.
     */
    static void usingMaxHeap(int[] arr, int k) {
        if (arr == null)
            return;
        int n = arr.length;
        Map<Integer, Integer> mp
                = new HashMap<Integer, Integer>();

        for (int elt : arr) {
            mp.put(elt, mp.getOrDefault(elt, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue())
                            ? Integer.compare(b.getKey(), a.getKey())
                            : Integer.compare(b.getValue(), a.getValue()));

        for (Map.Entry<Integer, Integer> entry : mp.entrySet())
            queue.offer(entry);

        for (int i = 0; i < k; i++) {
            System.out.println(queue.poll().getKey());
        }
    }

}
