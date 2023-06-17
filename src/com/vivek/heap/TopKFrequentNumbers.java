package com.vivek.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Top K Frequent Elements
 *
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentNumbers {

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Elem> freq = new HashMap<>();
        for (int num : nums) {
            if (!freq.containsKey(num)) {
                freq.put(num, new Elem(num));
            }
            freq.get(num).increment();
        }

        PriorityQueue<Elem> heap = new PriorityQueue<>((e1, e2) -> e1.f - e2.f);
        heap.addAll(freq.values());

        while (heap.size() > k) {
            heap.poll();
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll().x;
        }

        return result;
    }

    static class Elem {
        int x;
        int f;

        Elem(int x) {
            this.x = x;
            this.f = 0;
        }

        void increment() {
            f++;
        }
    }

}
