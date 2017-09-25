package com.vivek.bitmanipulation;

/**
 * Created by VJaiswal on 25/09/17.
 */
public class SignOfTwoInts {

    public static void main(String[] args) {
        // Detect if two integers has the same sign or not
        int x = 1;
        int y = -1;

        boolean isSignDifferent = (x ^ y) < 0;

        System.out.println(isSignDifferent);
    }

}
