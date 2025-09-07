package com.vivek.stack.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 1944. Number of Visible People in a Queue
 *
 * There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order.
 * You are given an array heights of distinct integers where heights[i] represents the height of the ith person.
 *
 * A person can see another person to their right in the queue if everybody in between is shorter than both of them.
 * More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).
 *
 * Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.
 *
 * Example 1:
 *
 * Input: heights = [10,6,8,5,11,9]
 * Output: [3,1,2,1,1,0]
 * Explanation:
 * Person 0 can see person 1, 2, and 4.
 * Person 1 can see person 2.
 * Person 2 can see person 3 and 4.
 * Person 3 can see person 4.
 * Person 4 can see person 5.
 * Person 5 can see no one since nobody is to the right of them.
 *
 * Example 2:
 *
 * Input: heights = [5,1,2,3,10]
 * Output: [4,1,1,1,0]
 *
 * Solution:
 * https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii
 *
 * The questions seems to be tailor made for our templates here. Intuition - for every person we decide only to things.
 *
 *     The next person with greater or equal height (no person after this would be visible to the current person). But multiple people behind the current person with bigger height can see the current person. Let's take care of them in the second point.
 *     The previous person with strictly greater height than the current person.
 *
 * 1 and 2 both select mutually exclusive people. Every time we find a person from both the cases, we increase their corresponding number of visisble people by 1.
 *
 * Reference: https://leetcode.com/problems/number-of-visible-people-in-a-queue
 */
public class NumberOfVisiblePeopleInQueue {

    public static void main(String[] args) {
        NumberOfVisiblePeopleInQueue helper = new NumberOfVisiblePeopleInQueue();
        int[] heights = {10,6,8,5,11,9};
        System.out.println(Arrays.toString(helper.canSeePersonsCount(heights)));
    }

    public int[] canSeePersonsCount(int[] heights) {
        int[] result = new int[heights.length];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                // next greater section
                int top = stack.pop();
                result[top] += 1;
            }

            if (!stack.isEmpty()) {
                // previous greater section
                result[stack.peek()] += 1;
            }
            stack.push(i);
        }
        return result;
    }

}
