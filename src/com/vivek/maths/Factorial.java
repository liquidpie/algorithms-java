package com.vivek.maths;

import java.math.BigInteger;

public class Factorial {

    static int recursive(int n) {
        if (n == 0)
            return 1;
        return n * recursive(n - 1);
    }

    /**
     * Factorial can also be calculated iteratively as recursion can be costly for large numbers.
     */
    static int iterative(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Given a very large number N, the task is to find the factorial of the number using Log.
     *
     * Approach: The most common iterative version runs in expected O(N) time.
     * But as numbers become big it will be wrong to assume that multiplication takes constant time.
     * The naive approach takes O(K*M) time for multiplication where K is the length of the multiplier and M is the length of the multiplicand.
     * Therefore, the idea is to use logarithmic properties:
     *
     * N! = product(i=1, N) and ln(ab) = ln(a) + ln(b)
     *
     * Therefore,
     * ln(N!) = ln(product(i=1, N)) = ∑ ln(i)
     *
     * And,
     * e ^ ln(N!) = N!
     *
     * Reference: https://www.geeksforgeeks.org/factorial-of-large-numbers-using-logarithmic-identity
     */
    static long usingLogIdentity(int n) {
        if (n == 0)
            return 1;

        double sum = 0;

        for (long counter = 1; counter <= n; counter++) {
            sum = sum + Math.log(counter);
        }

        return Math.round(Math.exp(sum));
    }

    /**
     * Approach:
     *
     * factorial(n)
     * 1) Create an array ‘res[]’ of MAX size where MAX is number of maximum digits in output.
     * 2) Initialize value stored in ‘res[]’ as 1 and initialize ‘res_size’ (size of ‘res[]’) as 1.
     * 3) Do following for all numbers from x = 2 to n.
     * ……a) Multiply x with res[] and update res[] and res_size to store the multiplication result.
     * How to multiply a number ‘x’ with the number stored in res[]?
     * The idea is to use simple school mathematics. We one by one multiply x with every digit of res[]. The important point to note here is digits are multiplied from rightmost digit to leftmost digit. If we store digits in same order in res[], then it becomes difficult to update res[] without extra space. That is why res[] is maintained in reverse way, i.e., digits from right to left are stored.
     * multiply(res[], x)
     * 1) Initialize carry as 0.
     * 2) Do following for i = 0 to res_size – 1
     * ….a) Find value of res[i] * x + carry. Let this value be prod.
     * ….b) Update res[i] by storing last digit of prod in it.
     * ….c) Update carry by storing remaining digits in carry.
     * 3) Put all digits of carry in res[] and increase res_size by number of digits in carry.
     *
     * Reference: https://www.geeksforgeeks.org/factorial-large-number
     */
    static String usingMultiplication(int n) {
        int[] res = new int[500];

        res[0] = 1;
        int resSize = 1;

        for (int i = 2; i <= n; i++) {
            resSize = multiply(res, i, resSize);
        }

        StringBuilder sb = new StringBuilder();
        for (int j = resSize - 1; j >= 0; j--) {
            sb.append(res[j]);
        }
        return sb.toString();
    }

    private static int multiply(int[] res, int n, int size) {
        int carry = 0;

        for (int i = 0; i < size; i++) {
            int mul = (res[i] * n) + carry;
            res[i] = mul % 10;
            carry = mul / 10;
        }

        while (carry > 0) {
            res[size] = carry % 10;
            carry = carry / 10;
            size++;
        }
        return size;
    }

    static BigInteger usingBigInteger(int n) {
        BigInteger res = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(usingMultiplication(100));
    }

}
