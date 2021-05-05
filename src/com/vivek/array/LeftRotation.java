package com.vivek.array;

import java.util.Scanner;

/**
 * Created by VJaiswal on 18/01/17.
 */
public class LeftRotation {

    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        int[] out = new int[n];

        for (int i = 0; i < n; i++) {
            out[i] = a[(i + n + k) % n];
        }
        return out;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        int[] output = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(output[i] + " ");

        System.out.println();

    }

}
