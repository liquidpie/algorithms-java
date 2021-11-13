package com.vivek.array;

/**
 * Add two binary strings
 *
 * Given two binary strings, return their sum (also a binary string)
 *
 * Example:
 * a = "11", b = "1"
 * Output: "100"
 *
 * Remove any leading zeroes from the output
 */
public class BinarySum {

    static String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;

        String result = "";

        int s = 0;
        while (i >= 0 || j >= 0 || s == 1) {
            s += ((i >= 0) ? a.charAt(i) - '0' : 0);
            s += ((j >= 0) ? b.charAt(j) - '0' : 0);

            result = (char) (s % 2 + '0') + result;
            s /= 2;

            i--;
            j--;
        }

        return result.replaceAll("^0+", "");
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "001"));
    }

}
