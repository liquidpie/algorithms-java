package com.vivek.string;

/**
 * Add Strings
 *
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
 *
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.
 *
 * Example 1:
 *
 * Input: num1 = "11", num2 = "123"
 * Output: "134"
 *
 * Example 2:
 *
 * Input: num1 = "456", num2 = "77"
 * Output: "533"
 *
 * Example 3:
 *
 * Input: num1 = "0", num2 = "0"
 * Output: "0"
 *
 * https://leetcode.com/problems/add-strings
 */
public class AddStrings {

    public static void main(String[] args) {
        {
            String num1 = "11";
            String num2 = "123";
            System.out.println(addStrings(num1, num2));
        }
    }

    static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 && j >= 0) {
            int sum = (num1.charAt(i) - '0') + (num2.charAt(j) - '0') + carry;
            carry = sum / 10;
            sum %= 10;
            sb.append(sum);
            i--;
            j--;
        }

        while (i >= 0) {
            int sum = (num1.charAt(i) - '0') + carry;
            carry = sum / 10;
            sum %= 10;
            sb.append(sum);
            i--;
        }

        while (j >= 0) {
            int sum = (num2.charAt(j) - '0') + carry;
            carry = sum / 10;
            sum %= 10;
            sb.append(sum);
            j--;
        }

        if (carry > 0)
            sb.append(carry);

        return sb.reverse().toString();
    }

}
