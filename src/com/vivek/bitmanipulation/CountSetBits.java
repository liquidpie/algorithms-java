package com.vivek.bitmanipulation;

import java.util.Scanner;

/**
 * Created by VJaiswal on 27/01/17.
 */
public class CountSetBits {

    public static void main(String... args) {
        try (Scanner in = new Scanner(System.in)) {
            long n = in.nextLong();
            int count = 0;
            while (n > 0) {
                if ((n & 1) == 1) {
                    count++;
                }
                n >>= 1;
            }
            System.out.println(count);
        }
    }
}
