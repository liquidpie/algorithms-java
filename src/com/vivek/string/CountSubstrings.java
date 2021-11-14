package com.vivek.string;

/**
 * Number of substrings of a string
 *
 * Input : str = “abc”
 * Output : 6
 * Every substring of the given string : “a”, “b”, “c”, “ab”, “bc”, “abc”
 *
 * Input : str = “abcd”
 * Output : 10
 * Every substring of the given string : “a”, “b”, “c”, “d”, “ab”, “bc”, “cd”, “abc”, “bcd” and “abcd”
 *
 * Approach:
 * Count of non-empty substrings is n*(n+1)/2
 * If we include empty string also as substring, the count becomes n*(n+1)/2 + 1
 *
 * How does above formula work?
 *
 *     Number of substrings of length one is n (We can choose any of the n characters)
 *     Number of substrings of length two is n-1 (We can choose any of the n-1 pairs formed by adjacent)
 *     Number of substrings of length three is n-2
 *     (We can choose any of the n-2 triplets formed by adjacent)
 *     In general, number of substrings of length k is n-k+1 where 1 <= k <= n
 *
 * https://www.geeksforgeeks.org/number-substrings-string
 */
public class CountSubstrings {

    static boolean includeEmptyStr = false;

    static int countSubstrings(String str) {
        if (str == null)
            return 0;
        int n = str.length();
        int count = (n * (n + 1)) / 2;

        return includeEmptyStr ? count + 1 : count;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("abcd"));
    }

}
