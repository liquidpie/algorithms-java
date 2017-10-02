package com.vivek.bitmanipulation;

/**
 * Created by VJaiswal on 26/09/17.
 */
public class ReverseBits {

    public static void main(String[] args) {
        int n = 2;

        System.out.println("Input:  " + Integer.toBinaryString(n));

        System.out.println("Output: " + Integer.toBinaryString(~n));

    }

}
