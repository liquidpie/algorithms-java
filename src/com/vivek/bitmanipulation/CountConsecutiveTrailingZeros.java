package com.vivek.bitmanipulation;

/**
 * Created by VJaiswal on 26/09/17.
 */
public class CountConsecutiveTrailingZeros {

    public static void main(String[] args) {
        int n = 4;
        int v = (n ^ (n - 1)) >> 1;

        int count = 0;

        while (v > 0) {
            count++;
            v >>= 1;
        }

        System.out.println(count);
    }

}
