package com.vivek.deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Longest contiguous subarray such that difference of max and min is at-most K
 *
 * Given an array arr[] of length N, the task is to find the length of longest subsequence such that the difference of its maximum element and minimum element is not more than an integer K.
 *
 * Example:
 * arr = [4, 2, 3, 6, 2, 2, 3, 2, 7], k = 1
 * Ans: 4
 * [2, 2, 3, 2] is such subarray
 *
 * Solution:
 *
 * 1. "Absolute difference between any two elements is less than or equal to limit" is basically => "Absolute difference between min and max elements of subarray"
 * 2. Now the question becomes => find the longest subarray in which the absolute difference between min and max is less than or equal to limit.
 *    What we can do is to have two pointers: left and right, and then find the longest subarray for every right pointer (iterate it) by shrinking left pointer.
 *    And return the longest one among them.
 * 3. Let's work on sliding window max first.
 *    By using max Deque. We maintain list of max element candidates in monotonically decreasing order.
 *    Everytime the right pointer reaches a new position, we need to dequeue the "tail" element who is smaller than the nums[right].
 *    Since, those "old small tail" elements will never be the range maximum from now on.
 *    After "clean up" the "old small tail" elements, add nums[right] into the deque, and then, the head of deque is the current maximum.
 *
 *    Same for the min Deque. Move right pointer by 1, and clean up "old big tail" elements, add nums[right], the head of deque is the current minimum.
 *
 *    What we should do next is to shrink left pointer because of limit. If current.max - current.min > limit. We should move the left pointer.
 *    Accordingdly, we need to update our min max deques. If head of max deque is equal to the nums[left], that means, it is the one, we need to dequeue it,
 *    since we are gonna move the left pointer by 1. (Note: nums[left] will be never larger than head of max deque,
 *    and if nums[left] is smaller than the head, we good, keep moving left pointer until satisfying the limit).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Reference: https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/609743/Java-Detailed-Explanation-Sliding-Window-Deque-O(N)
 *
 */
public class LongestContiguousSubArrayDiffAtMostK {

    static int longestSubArray(int[] arr, int k) {
        Deque<Integer> minDeque = new LinkedList<>();
        Deque<Integer> maxDeque = new LinkedList<>();
        int res = 1;
        int l= 0;

        for (int r = 0; r < arr.length; r++) {
            while (!maxDeque.isEmpty() && arr[r] > maxDeque.peekLast()) {
                maxDeque.removeLast();
            }
            maxDeque.addLast(arr[r]);

            while (!minDeque.isEmpty() && arr[r] < minDeque.peekLast()) {
                minDeque.removeLast();
            }
            minDeque.addLast(arr[r]);

            if (maxDeque.peekFirst() - minDeque.peekFirst() > k) {
                if (maxDeque.peekFirst() == arr[l]) maxDeque.removeFirst();
                if (minDeque.peekFirst() == arr[l]) minDeque.removeFirst();
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }


    public static void main(String[] args) {
        int[] arr = {4, 4, 3, 6, 2, 2, 3, 2, 7};
        int k = 1;
        System.out.println(longestSubArray(arr, k));
    }

}
