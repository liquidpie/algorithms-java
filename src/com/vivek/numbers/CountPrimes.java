package com.vivek.numbers;

import java.util.Arrays;

/**
 * Count Primes
 *
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 *
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 *
 * Example 3:
 *
 * Input: n = 1
 * Output: 0
 *
 * Solution:
 * This code implements a solution to count the number of prime numbers less than a given integer n using the Sieve of Eratosthenes algorithm.
 *
 * The algorithm works as follows:
 *
 *     - Initialize an array isPrime of boolean values to store whether each number from 0 to n is prime or not. Initially, all numbers are assumed to be prime.
 *     - Mark 0 and 1 as not prime, as they are not prime numbers (According to test cases).
 *     - Loop through the array from 2 to the square root of n.
 *     - For each number, if it is marked as prime, then mark all of its multiples as not prime by updating -
 *              isPrime[j] = false where j = 2 * i, 3 * i, ..., n
 *     - Finally, loop through the isPrime array and count the number of prime numbers.
 *
 * Time and Space Complexity:
 *
 * The time complexity of this algorithm is O(n * log(log(n))), as the loop only needs to run through the numbers up to
 * the square root of n and marking the multiples of each prime number.
 * The space complexity is O(n), as a boolean array of size n + 1 is used to store the prime numbers.
 *
 *
 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 * https://www.geeksforgeeks.org/sieve-of-eratosthenes/
 * https://math.stackexchange.com/questions/58799/why-in-sieve-of-erastothenes-of-n-number-you-need-to-check-and-cross-out-numbe
 *
 * https://leetcode.com/problems/count-primes
 */
public class CountPrimes {

    public static void main(String[] args) {
        int n = 2;
        System.out.println(countPrimes(n));
    }

    static int countPrimes(int n) {
        if (n < 2) // 0 & 1 are not prime numbers
            return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i < n; i++) {
            for (int j = 2 * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (isPrime[i])
                count++;
        }
        return count;
    }

}
