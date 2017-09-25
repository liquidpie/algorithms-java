package com.vivek.bitmanipulation;

/**
 * Created by VJaiswal on 25/09/17.
 */
public class SignOfInt {

    public static void main(String[] args) {
        int num = 121;
        // sizeof(int) * num_bits_in_byte - 1 = 31
        // Returns 0 if number is positive, else -1
        System.out.println(num >> 31);
        // Returns 1 if number is positive, else -1
        System.out.println(1 | num >> 31);
    }

}
