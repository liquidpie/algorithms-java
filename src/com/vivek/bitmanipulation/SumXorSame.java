package com.vivek.bitmanipulation;

import java.util.Scanner;

/**
 * Given an integer, n , find each x  such that:
 * 0 <= x <= n
 * n + x = n ^ x
 *
 * where ^ denotes the bitwise XOR operator.
 * Then print an integer denoting the total number of x's satisfying the criteria above.
 */
public class SumXorSame {

    public static void main(String... args) {
        try (Scanner in = new Scanner(System.in)) {
            long n = in.nextLong();
            long count = 0;
            while (n > 0) {
                if ((n & 1) == 0) {
                    count++;
                }
                n >>= 1;
            }
            System.out.println((long)Math.pow(2, count));
        }
    }
}
