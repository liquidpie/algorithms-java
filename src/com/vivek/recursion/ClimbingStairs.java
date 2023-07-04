package com.vivek.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * Climbing Stairs
 *
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Solution:
 *
 * → If the staircase has only one step, then it can be achieved using only 1 way by climbing 1 step.
 * → If stair case has two steps, then it can be achieved using two way,
 *
 * — (First Way)→ 1 (step)+ 1 (step): →
 * — (Second Way)→ Directly taking 2 Steps.
 *
 * → If stair case has three steps, then it can be achieved using three way,
 *
 * —(First Way)→ 1 (step)+ 1 (step) + 1 (step): →
 * —(Second Way)→ 2 (steps) + 1 (step): →
 * —(Third Way)→1 (step) + 2 (steps): →
 *
 * By checking the process, you will realize
 *
 * if staircase has 1 step = 1 way.
 * if staircase has 2 steps = 2 ways.
 * if staircase has 3 steps = 3 ways (here combination of (1 + 2) ).
 * if staircase has 4 steps = 5 ways (here combination of (3 + 2) ), like below for 4.
 *
 * 1) 1 + 1 + 1 + 1
 * 2) 2 + 2
 * 3) 1 + 1 + 2
 * 4) 2 + 1 + 1
 * 5) 1 + 2 + 1
 *
 * Based on data, you will realize that
 * 1, 2, 3 (2+1), 5 (3+2), 8 (5+3)……
 *
 * Every next steps result is a combination of previous two steps.
 * It’s like fibonacci sequence.
 *
 * Lets implement these things in code.
 *
 * First of all we will put condition like, if given value is 1,2,3 we will return that value as it is, because for 1,2 and 3 the ways are same.
 * Now for 4, 5… to n, we will need to do addition of previous two values.
 *
 * https://blog.devgenius.io/leetcode-70-climbing-stairs-get-solution-with-images-1939a8e6d525
 *
 * https://leetcode.com/problems/climbing-stairs/description/
 */
public class ClimbingStairs {

    static Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }


    static int climbStairs(int n) {
        if (n <= 3)
            return n;

        if (memo.containsKey(n))
            return memo.get(n);

        int a = climbStairs(n - 1);
        int b = climbStairs(n - 2);

        memo.put(n, a + b);

        return a + b;
    }

    static int climbStairsIterative(int n) {
        if(n <= 3){
            return n;
        }
        int a = 3, b = 2;
        for(int i = 0; i < n-3; i++){
            a = a + b;
            b = a - b;
        }
        return a;
    }

}
