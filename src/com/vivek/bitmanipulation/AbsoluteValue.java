package com.vivek.bitmanipulation;

/**
 * Created by VJaiswal on 25/09/17.
 */
public class AbsoluteValue {

    public static void main(String[] args) {
        // Compute the integer absolute value (abs)

        int num = -1010;
        final int mask = num >> 31;

        System.out.println((num + mask) ^ mask);

    }

}
