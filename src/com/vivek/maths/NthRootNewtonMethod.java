package com.vivek.maths;

/**
 * For example, 3 is a square root of 9, since 3^2 = 9, and −3 is also a square root of 9, since (−3)^2 = 9.
 *
 * Any non-zero number considered as a complex number has n different complex nth roots, including the real ones (at most two).
 * The nth root of 0 is zero for all positive integers n, since 0^n = 0. In particular, if n is even and x is a positive real number,
 * one of its nth roots is real and positive, one is negative, and the others (when n > 2) are non-real complex numbers;
 * if n is even and x is a negative real number, none of the nth roots is real. If n is odd and x is real, one nth root is real and has the same sign as x,
 * while the other (n – 1) roots are not real. Finally, if x is not real, then none of its nth roots are real.
 *
 * As this problem involves a real valued function A^(1/N) we can solve this using Newton’s method, which starts with an initial guess and iteratively shift towards the result.
 * We can derive a relation between two consecutive values of iteration using Newton’s method as follows,
 *
 * according to newton’s method
 * x(K+1) = x(K) – f(x) / f’(x)
 * here    f(x)  = x^(N) – A
 * so    f’(x) = N*x^(N - 1)
 * and     x(K) denoted the value of x at Kth iteration
 * putting the values and simplifying we get,
 * x(K + 1) = (1 / N) * ((N - 1) * x(K) + A / x(K) ^ (N - 1))
 *
 * Reference:
 * https://www.youtube.com/watch?v=JIHtSc4VbdM,
 * https://www.geeksforgeeks.org/n-th-root-number/
 *
 */
public class NthRootNewtonMethod {

    static double nthRoot(int A, int N) {

        // initially guessing a random number between 0 and 9
        double xPre = Math.random() % 10;

        // smaller eps, denotes more accuracy
        double eps = 0.001;

        // initializing difference between two roots by INT_MAX
        double delX = 2147483647;

        // xK denotes current value of x
        double xK = 0.0;

        // loop until we reach desired accuracy
        while (delX > eps) {
            // calculating current value from previous value by newton's method
            xK = ((N - 1.0) * xPre +
                    (double)A / Math.pow(xPre, N - 1)) / (double)N;
            delX = Math.abs(xK - xPre);
            xPre = xK;
        }

        return xK;
    }

    public static void main(String[] args) {
        int N = 4;
        int A = 81;

        double nthRootValue = nthRoot(A, N);
        System.out.println("Nth root is " + Math.round(nthRootValue * 1000.0) / 1000.0);
    }

}
