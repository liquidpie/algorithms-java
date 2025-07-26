package com.vivek.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 *
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * Example 3:
 *
 * Input: nums = [1,0,1,2]
 * Output: 3
 *
 * Reference: https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        LongestConsecutiveSequence helper = new LongestConsecutiveSequence();
        int[] nums = {100,4,200,1,3,2};
        System.out.println(helper.longestConsecutive(nums));
    }

    /**
     * Approach: this approach doesn't work if there is a duplicate element
     *
     * Uses a dictionary where each key represents a number from the input array,
     * and its corresponding value indicates the length of a consecutive sequence with that
     * number as either the upper or lower bound of the sequence.
     *
     * Complexity
     *
     *     Time complexity: O(n)
     *     Space complexity: O(n) auxiliary space
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> table = new HashMap<>();

        int maxCount = 0;
        for (int num : nums) {
            int x = table.getOrDefault(num - 1, 0);
            int y = table.getOrDefault(num + 1, 0);
            int count = x + y + 1;
            table.put(num - x, count);
            table.put(num + y, count);

            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }

    /**
     * https://leetcode.com/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-interview-150
     *
     * First turn the input into a set of numbers. That takes O(n) and then we can ask in O(1) whether we have a certain number.
     *
     * Then go through the numbers. If the number x is the start of a streak (i.e., x-1 is not in the set),
     * then test y = x+1, x+2, x+3, ... and stop at the first number y not in the set.
     *
     * The length of the streak is then simply y-x and we update our global best with that.
     * Since we check each streak only once, this is overall O(n).
     */
    public int longestConsecutiveApproach1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxCount = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int n = num + 1;
                while (set.contains(n)) {
                    n = n + 1;
                }
                maxCount = Math.max(maxCount, n - num);
            }
        }

        return maxCount;
    }

}
