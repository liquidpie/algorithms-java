package com.vivek.stack.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503. Next Greater Element II
 *
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number.
 * The second 1's next greater number needs to search circularly, which is also 2.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 *
 * Solution: run the loop two times
 *
 * Reference:
 * https://leetcode.com/problems/next-greater-element-ii/description/
 */
public class NextGreaterElementII {

    public static void main(String[] args) {
        NextGreaterElementII helper = new NextGreaterElementII();
        int[] nums = {1, 2, 1};
        System.out.println(Arrays.toString(helper.nextGreaterElements(nums)));
    }

    public int[] nextGreaterElements(int[] nums) {
        int[] nextGreater = new int[nums.length];
        Arrays.fill(nextGreater, -1);

        Stack<Integer> stack = new Stack<>();

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    int stackTop = stack.pop();
                    nextGreater[stackTop] = i;
                }
                stack.push(i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nextGreater[i] = nextGreater[i] != -1 ? nums[nextGreater[i]] : -1;
        }

        return nextGreater;
    }

}
