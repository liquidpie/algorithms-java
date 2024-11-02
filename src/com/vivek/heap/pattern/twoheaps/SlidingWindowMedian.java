package com.vivek.heap.pattern.twoheaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Sliding Window Median
 *
 * Given an array of numbers and a number ‘k’, find the median of all the ‘k’ sized sub-arrays (or windows) of the array.
 * Example 1:
 * Input: nums=[1, 2, -1, 3, 5], k = 2
 * Output: [1.5, 0.5, 1.0, 4.0]
 *
 * Example 2:
 * Input: nums=[1, 2, -1, 3, 5], k = 3
 * Output: [1.0, 2.0, 3.0]
 *
 * Solution #
 * This problem follows the Two Heaps pattern and share similarities with Find the Median of a Number
 * Stream. We can follow a similar approach of maintaining a max-heap and a min-heap for the list of numbers
 * to find their median.
 * The only difference is that we need to keep track of a sliding window of ‘k’ numbers. This means, in each
 * iteration, when we insert a new number in the heaps, we need to remove one number from the heaps which
 * is going out of the sliding window. After the removal, we need to rebalance the heaps in the same way that we
 * did while inserting.
 *
 * Time complexity:
 * The time complexity of our algorithm is O(N ∗ K) where ‘N’ is the total number of elements in the input
 * array and ‘K’ is the size of the sliding window. This is due to the fact that we are going through all the ‘N’
 * numbers and, while doing so, we are doing two things:
 * 1. Inserting/removing numbers from heaps of size ‘K’. This will take O(logK)
 * 2. Removing the element going out of the sliding window. This will take O(K) as we will be searching this element in an array of size ‘K’ (i.e., a heap).
 *
 * Space complexity:
 * Ignoring the space needed for the output array, the space complexity will be O(K) because, at any time, we
 * will be storing all the numbers within the sliding window.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Two Heaps
 */
public class SlidingWindowMedian {

    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    static double[] findSlidingWindowMedian(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            insertNum(nums[i]);
            rebalanceHeaps();
            if (i - k + 1 >= 0) { // If we have at least k elements in the sliding window, add median to result array
                if (maxHeap.size() == minHeap.size()) {
                    result[i - k + 1] = (maxHeap.peek() + minHeap.peek()) / 2.0;
                } else {
                    result[i - k + 1] = maxHeap.peek();
                }

                // remove the element going out of the window
                int eltToRemove = nums[i - k + 1];
                if (eltToRemove <= maxHeap.peek()) {
                    maxHeap.remove(eltToRemove);
                } else {
                    minHeap.remove(eltToRemove);
                }
                rebalanceHeaps();
            }
        }

        return result;
    }

    private static void insertNum(int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
    }

    private static void rebalanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

}
