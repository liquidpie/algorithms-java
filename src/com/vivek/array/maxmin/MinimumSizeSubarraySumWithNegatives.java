package com.vivek.array.maxmin;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Shortest Subarray with Sum at Least K
 *
 * Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *
 * Input: nums = [1], k = 1
 * Output: 1
 * Example 2:
 *
 * Input: nums = [1,2], k = 4
 * Output: -1
 * Example 3:
 *
 * Input: nums = [2,-1,2], k = 3
 * Output: 3
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= 10^9
 *
 * Approach ::
 *
 * Complexity:
 * Every index will be pushed exactly once.
 * Every index will be popped at most once.
 *
 * Time O(N)
 * Space O(N)
 *
 *
 * How to think of such solutions?
 * Basic idea, for array starting at every A[i], find the shortest one with sum at leat K.
 * In my solution, for B[i], find the smallest j that B[j] - B[i] >= K.
 * Keep this in mind for understanding two while loops.
 *
 *
 * What is the purpose of first while loop?
 * For the current prefix sum B[i], it covers all subarray ending at A[i-1].
 * We want know if there is a subarray, which starts from an index, ends at A[i-1] and has at least sum K.
 * So we start to compare B[i] with the smallest prefix sum in our deque, which is B[D[0]], hoping that [i] - B[d[0]] >= K.
 * So if B[i] - B[d[0]] >= K, we can update our result res = min(res, i - d.popleft()).
 * The while loop helps compare one by one, until this condition isn't valid anymore.
 *
 *
 * Why we pop left in the first while loop?
 * This the most tricky part that improve my solution to get only O(N).
 * D[0] exists in our deque, it means that before B[i], we didn't find a subarray whose sum at least K.
 * B[i] is the first prefix sum that valid this condition.
 * In other words, A[D[0]] ~ A[i-1] is the shortest subarray starting at A[D[0]] with sum at least K.
 * We have already find it for A[D[0]] and it can't be shorter, so we can drop it from our deque.
 *
 *
 * What is the purpose of second while loop?
 * To keep B[D[i]] increasing in the deque.
 *
 *
 * Why keep the deque increase?
 * If B[i] <= B[d.back()] and moreover we already know that i > d.back(), it means that compared with d.back(),
 * B[i] can help us make the subarray length shorter and sum bigger. So no need to keep d.back() in our deque.
 *
 * Solution: https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k
 *
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 */
public class MinimumSizeSubarraySumWithNegatives {

    public static void main(String[] args) {
        int target = 167;
        int[] nums = {84,-37,32,40,95};
        System.out.println(minSubArrayLen(target, nums));
    }

    static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++)
            prefixSum[i + 1] = prefixSum[i] + nums[i];

        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);

        for (int i = 1; i < n + 1; i++) {
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.getFirst()] >= target)
                minLen = Math.min(minLen, i - deque.pollFirst());

            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.getLast()])
                deque.pollLast();

            deque.addLast(i);
        }

        return minLen != Integer.MAX_VALUE ? minLen : -1;
    }

}
