package com.vivek.bitmanipulation;

/**
 * 338. Counting Bits
 *
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
 * ans[i] is the number of 1's in the binary representation of i.
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 *
 * Example 2:
 *
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 * Intuition :
 *
 * To find the number of 1s in the binary representation of numbers from 0 to ( n ), we can use a pattern:
 *
 *     1. If ( i ) is even, the number of 1s in ( i ) is the same as the number of 1s in ( i/2 )
 *        (right-shifting an even number halves it without adding a new 1).
 *     2. If ( i ) is odd, the number of 1s in ( i ) is the number of 1s in ( i - 1 ) plus one additional 1
 *        (as adding 1 to an even number makes it odd).
 *
 * Approach :
 *
 *     Initialize the Array: Create an array ans of size ( n + 1 ) and set the base case ans[0] = 0.
 *     Iterate from 1 to n:
 *         For each ( i ):
 *             If ( i ) is even, set ans[i] = ans[i / 2].
 *             If ( i ) is odd, set ans[i] = ans[i - 1] + 1.
 *     Return the array ans after completing the iteration.
 *
 * https://leetcode.com/problems/counting-bits/solutions/6032841/0-ms-runtime-beats-100-user-step-by-steps-solution-easy-to-understand/?envType=study-plan-v2&envId=leetcode-75
 *
 * Reference: https://leetcode.com/problems/counting-bits/
 */
public class CountingBits {

    public int[] countBits(int n) {
        int[] result = new int[n + 1];

        for (int x = 1; x <= n; x++) {
            result[x] = result[x >> 1] + (x & 1);
        }

        return result;
    }

}
