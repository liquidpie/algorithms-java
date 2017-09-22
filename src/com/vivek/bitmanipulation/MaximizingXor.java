package com.vivek.bitmanipulation;

import java.util.Scanner;

/**
 * Created by VJaiswal on 24/01/17.
 */
public class MaximizingXor {

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        int r = in.nextInt();

        System.out.println(maximizeXor(l, r));
    }

    static int maximizeXor(int l, int r) {
        int place = 0;
        while ((l ^ r) != 0) {
            l = l >> 1;
            r = r >> 1;
            place++;
        }

        return pow(2, place) - 1;
    }

    static int pow(int n, int p) {
        if (p == 1)
            return n;
        return n * pow(n, p - 1);
    }

    /*
    static int maxXor(int l, int r) {
        int result = l ^ r;
        result |= result >> 1;
        result |= result >> 2;
        result |= result >> 4;
        result |= result >> 8;
        return result;
    }
    */

}
