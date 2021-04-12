package com.vivek.maths;

public class NthRoot {

    /**
     * Returns nth-root for a given number upto 3 decimal places
     *
     * Uses binary search to compute the nth-root
     *
     * @param x Number whose nth root to be taken
     * @param n nth root
     *
     * Reference: https://www.geeksforgeeks.org/calculating-n-th-real-root-using-binary-search/
     */
    static double nthRoot(int x, int n) {
        double low = 1;
        double high = x;

        double epsilon = 0.001; // used for taking approximations

        double next = (low + high) / 2;
        while (Math.abs(Math.pow(next, n)) - x >= epsilon) {
            if (Math.pow(next, n) > x) {
                high = next;
            } else {
                low = next;
            }

            next = (low + high) / 2;
        }

        return next;
    }

    public static void main(String[] args) {
        System.out.println(nthRoot(5, 2));
    }

}
