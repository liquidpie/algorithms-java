package com.vivek.maths;

/**
 * Given a large number n and a prime p, how to efficiently compute n! % p?
 * Examples :
 *
 *
 * Input:  n = 5, p = 13
 * Output: 3
 * 5! = 120 and 120 % 13 = 3
 *
 * Input:  n = 6, p = 11
 * Output: 5
 * 6! = 720 and 720 % 11 = 5
 *
 * Reference: https://www.geeksforgeeks.org/compute-n-under-modulo-p
 */
public class ModFactorial {

    /**
     * One by one multiply result with i under modulo p. So the value of result doesn’t go beyond p before next iteration.
     *
     * Time Complexity of this solution is O(n)
     */
    static int modFact(int n, int p) {
        if (n >= p)
            return 0;

        int result = 1;
        for (int i = 2; i <= n; i++)
            result = (result * i) % p;

        return result;
    }

    /**
     * The largest possible power of a prime pi that divides n is,
     *     ⌊n/pi⌋ + ⌊n/(pi2)⌋ +  ⌊n/(pi3)⌋ + .....+ 0
     *
     * Every integer can be written as multiplication of powers of primes.  So,
     *   n! = p1k1 * p2k2 * p3k3 * ....
     *   Where p1, p2, p3, .. are primes and
     *         k1, k2, k3, .. are integers greater than or equal to 1.
     *
     * The idea is to find all primes smaller than n using Sieve of Eratosthenes.
     * For every prime ‘pi‘, find the largest power of it that divides n!. Let the largest power be ki.
     * Compute pi^ki % p using modular exponentiation. Multiply this with final result under modulo p.
     *
     * This is an interesting method, but time complexity of this is more than Simple Method as time complexity of Sieve itself is O(n log log n).
     */
    static int usingSieve(int n, int p) {
        if (n >= p)
            return 0;

        int res = 1;

        boolean[] primes = SieveOfEratosthenes.getPrimes(n);

        // consider all primes found by Sieve
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                // find largest power of prime i that divides n
                int k = LegendreFormula.largestPower(n, i);
                // Multiply result with (i^k) % p
                res = (res * ModularExponentiation.power(i, k, p)) % p;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(usingSieve(25, 29));
    }
}
