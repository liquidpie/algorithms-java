package com.vivek.maths;

/**
 * 258. Add Digits
 *
 * Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
 *
 * Example 1:
 *
 * Input: num = 38
 * Output: 2
 * Explanation: The process is
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * Since 2 has only one digit, return it.
 *
 * Example 2:
 *
 * Input: num = 0
 * Output: 0
 *
 * Reference: https://leetcode.com/problems/add-digits/description/
 */
public class AddDigits {

    public static void main(String[] args) {
        AddDigits helper = new AddDigits();
        System.out.println(helper.addDigits(38));
    }

    public int addDigits(int num) {
        if (num == 0)
            return num;

        return (num - 1) % 9 + 1;
    }

    public int addDigitsUsingLoop(int num) {
        int result = num;

        while (result >= 10) {
            num = result;
            result  = 0;
            while (num > 0) {
                result += num % 10;
                num /= 10;
            }
        }
        return result;
    }

}
