package com.vivek.array;

import java.util.Scanner;

/**
 * Created by VJaiswal on 08/06/17.
 *

 You are given two arrays,  and , both containing  integers.

 A pair of indices  is beautiful if the  element of com.vivek.array  is equal to the  element of com.vivek.array . In other words, pair  is beautiful if and only if .

 Given  and , there are  pairs of beautiful indices . A pair of indices in this set is pairwise disjoint if and only if for each  it holds that  and .

 Change exactly  element in  so that the resulting number of pairwise disjoint beautiful pairs is maximal, and print this maximal number to stdout.

 Input Format

 The first line contains a single integer,  (the number of elements in  and ).
 The second line contains  space-separated integers describing com.vivek.array .
 The third line contains  space-separated integers describing com.vivek.array .

 Constraints

 > 1 <= N <= 1000
 > 1 <= A[i] <= 1000
 > 1 <= B[i] <= 1000


 */
public class BeautifulPairs {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int a[] = new int[1001]; // constraint
            int sum = 0;
            for(int i = 0; i < n; i++)
                a[in.nextInt()]++;

            for(int i = 0; i < n; i++) {
                int v = in.nextInt();
                if(a[v] > 0) {
                    sum++;
                    a[v]--;
                }
            }

            if(sum < n)
                System.out.println(sum + 1);
            else
                System.out.println(sum - 1);
        }
    }

}
