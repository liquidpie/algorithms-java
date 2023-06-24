package com.vivek.recursion;

/**
 * K-th Symbol in Grammar
 *
 * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 *
 * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
 * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
 *
 * Example 1:
 *
 * Input: n = 1, k = 1
 * Output: 0
 * Explanation: row 1: 0
 * Example 2:
 *
 * Input: n = 2, k = 1
 * Output: 0
 * Explanation:
 * row 1: 0
 * row 2: 01
 * Example 3:
 *
 * Input: n = 2, k = 2
 * Output: 1
 * Explanation:
 * row 1: 0
 * row 2: 01
 *
 * Solution:
 *
 * Look at the sequence recursively. In generating a new row, the first half is identical to the process you used to get the previous row,
 * so that part of the expansion is already done. The second half is merely the same sequence inverted (0 for 1, 1 for 0).
 * This is one classic way to generate a parity map: flip all the bits and append, representing adding a 1 to the start of each binary number.
 * Thinking of expanding the sequence 0-3 to 0-7, we start with
 *
 * 00 => 0
 * 01 => 1
 * 10 => 1
 * 11 => 0
 * We now replicate the 2-digit sequence twice: first with a leading 0, which preserves the original parity; second with a leading 1,
 * which inverts the parity.
 *
 * 000 => 0
 * 001 => 1
 * 010 => 1
 * 011 => 0
 * 100 => 1
 * 101 => 0
 * 110 => 0
 * 111 => 1
 *
 * Solution: https://walkccc.me/LeetCode/problems/0779/
 *
 * https://leetcode.com/problems/k-th-symbol-in-grammar
 */
public class KthSymbol {

    public static void main(String[] args) {
        int n = 2; int k = 2;
        System.out.println(kthSymbol(n, k));
    }

    static int kthSymbol(int n, int k) {
        if (n == 1)
            return 0;
        if (k % 2 == 1)
            return kthSymbol(n - 1, (k + 1) / 2) == 0 ? 0 : 1; // left node, n-1 th row is half of nth row
        return kthSymbol(n - 1, k / 2) == 0 ? 1 : 0; // right node
    }

}
