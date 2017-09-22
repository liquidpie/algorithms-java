package com.vivek.bitmanipulation;

import java.util.Scanner;

/**
 * Created by VJaiswal on 21/09/17.
 */
public class LowestSetBit {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int input  = in.nextInt();

            int bin = input & ~(input - 1); // all com.vivek.bits cleared except lowest set bit

            int index = 0;
            while ((bin & 1) != 1) {
                bin = bin >> 1;
                index++;
            }
            System.out.println("Lowest Set Bit at index : " + index);
        }
    }

}
