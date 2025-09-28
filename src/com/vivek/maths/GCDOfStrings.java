package com.vivek.maths;

/**
 * 1071. Greatest Common Divisor of Strings
 *
 * For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
 *
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 *
 * Example 1:
 *
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 *
 * Example 2:
 *
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 *
 * Example 3:
 *
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 *
 * Solution:
 *
 * Suppose str1 = "ABCABC" and str2 = "ABC"
 * str1 + str2 = ABCABCABC
 * str2 + str1 = ABCABCABC
 * str1 + str2 == str2 + str1
 * return str.substr(0, gcd(size(str1), gcd(size(str2))))
 * where gcd(3, 6) = 3
 * So, answer is "ABC"
 *
 * Also str1 = "LEET", str2 = "CODE"
 * str1 + str2 = "LEETCODE"
 * str2 + str1 = "CODELEET"
 * So, return ""
 *
 * https://leetcode.com/problems/greatest-common-divisor-of-strings/solutions/3124997/super-easy-solution-fully-explained-c-python3-java/?envType=study-plan-v2&envId=leetcode-75
 *
 * Reference:
 * https://leetcode.com/problems/greatest-common-divisor-of-strings
 */
public class GCDOfStrings {

    public static void main(String[] args) {
        GCDOfStrings helper = new GCDOfStrings();
        System.out.println(helper.gcdOfStrings2("ABABAB", "AB"));
    }

    public String gcdOfStrings(String str1, String str2) {
        if (str1.isEmpty())
            return "";
        if (str2.isEmpty())
            return "";

        if (str1.equals(str2))
            return str1;

        if (str1.length() > str2.length()) {
            return str1.startsWith(str2) ? gcdOfStrings(str1.substring(str2.length()), str2)
                    : gcdOfStrings("", str2);
        } else {
            return str2.startsWith(str1) ? gcdOfStrings(str1, str2.substring(str1.length()))
                    : gcdOfStrings(str1, "");
        }
    }


    public String gcdOfStrings2(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }


}
