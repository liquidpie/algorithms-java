package com.vivek.maths;

import java.util.ArrayList;
import java.util.List;

/**
 * 1323. Maximum 69 Number
 *
 * You are given a positive integer num consisting only of digits 6 and 9.
 *
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 *
 * Example 1:
 *
 * Input: num = 9669
 * Output: 9969
 * Explanation:
 * Changing the first digit results in 6669.
 * Changing the second digit results in 9969.
 * Changing the third digit results in 9699.
 * Changing the fourth digit results in 9666.
 * The maximum number is 9969.
 *
 * Example 2:
 *
 * Input: num = 9996
 * Output: 9999
 * Explanation: Changing the last digit 6 to 9 results in the maximum number.
 *
 * Example 3:
 *
 * Input: num = 9999
 * Output: 9999
 * Explanation: It is better not to apply any change.
 *
 * Reference: https://leetcode.com/problems/maximum-69-number/description/
 */
public class Maximum69Number {

    public static void main(String[] args) {
        Maximum69Number helper = new Maximum69Number();
        System.out.println(helper.maximum69Number(9669));
    }

    public int maximum69Number(int num) {
        List<Integer> digits = new ArrayList<>();
        while (num > 0) {
            digits.add(num % 10);
            num /= 10;
        }

        int i = digits.size();
        while (i > 0 && digits.get(i - 1) == 9)
            i--;

        if (i != 0)
            digits.set(i - 1, 9);

        int res = 0;
        for (int j = digits.size() - 1; j >= 0; j--)
            res = res * 10 + digits.get(j);

        return res;
    }

}
