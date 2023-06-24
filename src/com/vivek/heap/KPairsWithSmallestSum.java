package com.vivek.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Find K Pairs with Smallest Sums
 *
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 * Solution: https://walkccc.me/LeetCode/problems/0373/
 * 1. Keep a priority Queue to store sum of pairs & their indices
 * 2. Populate heap with pairs of all elements of first array with the first element of second array
 * 3. While popping elements from heap, check if new subsequent pairs can be added to heap
 *
 * Time Complexity: O(k log k)
 * Space Complexity: O(k)
 *
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums
 */
public class KPairsWithSmallestSum {

    public static void main(String[] args) {
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k = 3;

        System.out.println(kSmallestPairs(nums1, nums2, k));
    }

    static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<Item> priorityQueue = new PriorityQueue<>((a, b) -> a.sum - b.sum);

        for (int i = 0; i < nums1.length; i++) {
            priorityQueue.add(new Item(nums1[i] + nums2[0], i, 0));
        }

        while (!priorityQueue.isEmpty() && result.size() < k) {
            Item item = priorityQueue.poll();
            int i = item.i;
            int j = item.j;
            result.add(List.of(nums1[i], nums2[j]));
            if (j + 1 < nums2.length) {
                priorityQueue.add(new Item(nums1[i] + nums2[j + 1], i, j + 1));
            }
        }

        return result;
    }

    static class Item {
        int sum;
        int i;
        int j;

        public Item(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }

}
