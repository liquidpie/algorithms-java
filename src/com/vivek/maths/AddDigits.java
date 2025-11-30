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
 * Solution:
 *      This question essentially asks you to find the digital root.
 *      https://en.wikipedia.org/wiki/Digital_root
 *
 * Reference: https://leetcode.com/problems/add-digits/description/
 */
public class AddDigits {

    public static void main(String[] args) {
        AddDigits helper = new AddDigits();
        System.out.println(helper.addDigits(38));
    }

    public int addDigits(int num) {
        return num == 0 ? 0 : num % 9 == 0 ? 9 : num % 9;
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
