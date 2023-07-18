package com.vivek.numbers;

/**
 * Double Base Palindrome
 *
 * Double base Palindrome as the name suggest is a number which is Palindrome in 2 bases. One of the base is 10 i.e. decimal and another base is k.(which can be 2 or others).
 * Note : The palindromic number, in either base, may not include leading zeros.
 * Example : The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
 *
 * A Palindrome is a word, phrase, number, or other sequence of characters which reads the same backward as forward, such as madam or 12321.
 *
 * Find the sum of all numbers less than n which are palindromic in base 10 and base k.
 *
 * Examples:
 *
 * Input :  10 2
 * Output : 25
 * Explanation : (here n = 10 and k = 2)
 *               1 3 5 7 9 (they are all palindrome
 *               in base 10 and 2) so sum is :
 *               1 + 3 + 5 + 7 + 9 = 25
 *
 * Input :  100 2
 * Output : 157
 * Explanation : 1 + 3 + 5 + 7 + 9 + 33 + 99 = 157
 *
 * Method 1 : This method is simple. For every number less than n :
 *
 *     Check if it is a palindrome in base 10
 *     If yes, then convert it into base k
 *     Check if it is palindrome in base k
 *     If yes, then add it in sum.
 *
 * This method is quite lengthy as it checks for every number whether it is a palindrome or not. So, for number as large as 1000000, it checks for every number.
 * If k = 2, then a palindrome in base 2 can only be odd number, which might reduce the comparisons to 1000000 / 2 = 500000 (which is still large).
 *
 * https://www.geeksforgeeks.org/double-base-palindrome/
 *
 * Time Complexity: O(n*log(n))
 * Auxiliary Space: O(log(n))
 *
 * https://www.geeksforgeeks.org/double-base-palindrome/
 */
public class DoubleBasePalindrome {

    public static void main(String[] args) {
        System.out.println(sumPalindrome(100, 2));
    }

    static int sumPalindrome(int n, int k) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += isPalindrome(i, k) ? i : 0;
        }
        return sum;
    }

    static boolean isPalindrome(int n, int k) {
        int t = n;
        int r = 0;
        while (t > 0) {
            r = t % 10 + 10 * r;
            t /= 10;
        }

        if (r != n)
            return false;

        String nBin = Integer.toBinaryString(n);
        String rBin = (new StringBuilder(nBin)).reverse().toString();

        return nBin.equals(rBin);

    }
}
