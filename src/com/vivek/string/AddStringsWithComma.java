package com.vivek.string;

/**
 * Add Strings with Comma
 *
 * Add strings, format strings (add comma), and then use the add operation for fiboncacci seq ("1" + "1" = "2")
 *
 * Write a program to add 2 numbers represented as strings.
 * Follow up 1: The input strings are formatted with commas.
 * Follow up 2: The output string also need to be formatted with commas.
 *
 * Wayfair problem
 */
public class AddStringsWithComma {

    public static void main(String[] args) {
        System.out.println(addStringsApproach1("1,234,567", "890,123"));  // Output: 2,124,690
        System.out.println(addStringsApproach1("123", "456"));  // Output: 579
        System.out.println(addStringsApproach1("123,456", "789,012"));  // Output: 912,468
        System.out.println(addStringsApproach1("123,456", "789"));  // Output: 124,245

        System.out.println(addStringsApproach2("1,234,567", "890,123"));  // Output: 2,124,690
        System.out.println(addStringsApproach2("123", "456"));  // Output: 579
        System.out.println(addStringsApproach2("123,456", "789,012"));  // Output: 912,468
        System.out.println(addStringsApproach2("123,456", "789"));  // Output: 124,245
    }

    static String addStringsApproach1(String num1, String num2) {
        num1 = num1.replaceAll(",", "");
        num2 = num2.replaceAll(",", "");
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }

        return formatWithCommas(sb.reverse().toString());
    }

    private static String formatWithCommas(String num) {
        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            count++;
            sb.append(num.charAt(i));
            if (count % 3 == 0 && i != 0) {
                sb.append(',');
            }
        }
        return sb.reverse().toString();
    }

    static String addStringsApproach2(String num1, String num2) {
        num1 = num1.replaceAll(",", "");
        num2 = num2.replaceAll(",", "");

        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);

        int result = n1 + n2;
        return String.format("%,d", result);
    }

}
