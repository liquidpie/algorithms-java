package com.vivek.bitmanipulation;

/**

 Problem: Write a program to print Binary representation of a given number.

 step 1: Check n > 0
 step 2: Right shift the number by 1 bit and recursive function call
 step 3: Print the bits of number

 */
public class BinaryRepresentationGivenNumber {

    static void bin(Integer n)
    {
        if (n > 1)
            bin(n>>1);

        System.out.printf("%d", n & 1);
    }

    // Driver code
    public static void main(String[] args) {
        bin(131);
        System.out.printf("\n");
        bin(3);
    }

}
