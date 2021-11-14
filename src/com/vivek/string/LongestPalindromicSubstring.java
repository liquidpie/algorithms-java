package com.vivek.string;

/**
 * Longest Palindromic Substring
 *
 * Given a string, find the longest substring which is palindrome.
 *
 * For example,
 *
 * Input: Given string :"forgeeksskeegfor",
 * Output: "geeksskeeg"
 *
 * Input: Given string :"Geeks",
 * Output: "ee"
 *
 * Method 1: Brute Force.
 * Approach: The simple approach is to check each substring whether the substring is a palindrome or not. To do this first, run three nested loops, the outer two loops pick all substrings one by one by fixing the corner characters, the inner loop checks whether the picked substring is palindrome or not.
 *
 * Method 2: Dynamic Programming.
 * Approach: The time complexity can be reduced by storing results of sub-problems
 *
 *     1. Maintain a boolean table[n][n] that is filled in bottom up manner.
 *     2. The value of table[i][j] is true, if the substring is palindrome, otherwise false.
 *     3. To calculate table[i][j], check the value of table[i+1][j-1], if the value is true and str[i] is same as str[j], then we make table[i][j] true.
 *     4. Otherwise, the value of table[i][j] is made false.
 *     5. We have to fill table previously for substring of length = 1 and length =2 because
 *          as we are finding , if table[i+1][j-1] is true or false , so in case of
 *          (i) length == 1 , lets say i=2 , j=2 and i+1,j-1 doesn’t lies between [i , j]
 *          (ii) length == 2 ,lets say i=2 , j=3 and i+1,j-1 again doesn’t lies between [i , j].
 *
 * Method 3:
 * The time complexity of the Dynamic Programming based solution is O(n^2) and it requires O(n^2) extra space. We can find the longest palindrome substring in (n^2) time with O(1) extra space.
 *
 * 1. The idea is to generate all even length and odd length palindromes and keep track of the longest palindrome seen so far.
 * 2. To generate odd length palindrome, Fix a center and expand in both directions for longer palindromes, i.e. fix i (index) as the center and two indices as i1 = i+1 and i2 = i-1
 * 3. Compare i1 and i2 if equal then decrease i2 and increase i1 and find the maximum length.
 *      Use a similar technique to find the even-length palindrome.
 * 4. Take two indices i1 = i and i2 = i-1 and compare characters at i1 and i2 and find the maximum length till all pairs of compared characters are equal and store the maximum length.
 * 5. Print the maximum length.
 *
 * Reference: https://www.geeksforgeeks.org/longest-palindromic-substring-set-2
 */
public class LongestPalindromicSubstring {

    static int longestPalindromicSubstr(String str) {
        int maxLength = 1;
        int n = str.length();

        int start = 0;
        int low, high;

        for (int i = 1; i < n; i++) {
            // Find the longest even length palindrome with center points as i-1 and i.
            low = i - 1;
            high = i;
            while (low >= 0 && high < n && str.charAt(low) == str.charAt(high)) {
                low--;
                high++;
            }

            // Move back to the last possible valid palindrome substring
            ++low; --high;
            if (str.charAt(low) == str.charAt(high) && high - low + 1 > maxLength) {
                start = low;
                maxLength = high - low + 1;
            }

            // Find the longest odd length palindrome with center points as i
            low = i - 1;
            high = i + 1;
            while (low >= 0 && high < n && str.charAt(low) == str.charAt(high)) {
                low--;
                high++;
            }

            low++; high--;
            if (str.charAt(low) == str.charAt(high) && high - low + 1 > maxLength) {
                start = low;
                maxLength = high - low + 1;
            }

        }

        // print palindrome
        String palindrome = str.substring(start, start + maxLength);
        System.out.println(palindrome);

        return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(longestPalindromicSubstr("JKABCBALML"));
    }

}
