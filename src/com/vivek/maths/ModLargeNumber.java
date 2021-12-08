package com.vivek.maths;

/**
 * Todo
 *
 * Given a big number ‘num’ represented as string and an integer x, find value of “num % x” or “num mod x”. Output is expected as an integer.
 *
 * Examples :
 *
 * Input:  num = "12316767678678",  a = 10
 * Output: num (mod a) ≡ 8
 *
 * The idea is to process all digits one by one and use the property that xy (mod a) ≡ ((x (mod a) * y) (mod a)).
 *
 * Reference: https://www.geeksforgeeks.org/how-to-compute-mod-of-a-big-number/
 */
public class ModLargeNumber {

    static int mod(String num, int a) {

        int res = 0;

        // One by one process all digits of 'num'
        for (int i = 0; i < num.length(); i++)
            res = (res * 10 + (int) num.charAt(i) - '0') % a;

        return res;
    }

    public static void main(String[] args) {

        String num = "12316767678678";
        System.out.println(mod(num, 10));
    }

}
