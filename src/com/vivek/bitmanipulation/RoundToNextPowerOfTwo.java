package com.vivek.bitmanipulation;

/**
 * Created by VJaiswal on 26/09/17.
 */
public class RoundToNextPowerOfTwo {

    public static void main(String[] args) {
        int n = 100;

        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n++;

        System.out.println(n);

    }

}
