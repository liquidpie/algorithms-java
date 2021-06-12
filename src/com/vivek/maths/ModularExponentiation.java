package com.vivek.maths;

/**
 * Given three numbers x, y and p, compute (xy) % p.
 *
 * Examples :
 *
 * Input:  x = 2, y = 3, p = 5
 * Output: 3
 * Explanation: 2^3 % 5 = 8 % 5 = 3.
 *
 * Input:  x = 2, y = 5, p = 13
 * Output: 6
 * Explanation: 2^5 % 13 = 32 % 13 = 6.
 *
 * Below is the fundamental modular property that is used for efficiently computing power under modular arithmetic.
 *
 * (ab) mod p = ( (a mod p) (b mod p) ) mod p
 *
 * For example a = 50,  b = 100, p = 13
 * 50  mod 13  = 11
 * 100 mod 13  = 9
 *
 * (50 * 100) mod 13 = ( (50 mod 13) * (100 mod 13) ) mod 13
 * or (5000) mod 13 = ( 11 * 9 ) mod 13
 * or 8 = 8
 *
 * Reference: https://www.geeksforgeeks.org/modular-exponentiation-power-in-modular-arithmetic
 */
public class ModularExponentiation {

    static int power(int x, int y, int p) {
        int res = 1;

        x = x % p; // Update x if it is more than or equal to p

        if (x == 0)
            return 0; // In case x is divisible by p

        while (y > 0) {
            if ((y & 1) != 0) { // If y is odd, multiply x with result
                res = (res * x) % p;
            }
            y = y >> 1;
            x = (x * x) % p;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(power(2, 5, 13));
    }

}
