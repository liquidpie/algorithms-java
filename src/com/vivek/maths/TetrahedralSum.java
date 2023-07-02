package com.vivek.maths;

/**
 * A lot of sum
 *
 * You are given two integers N & K.
 *
 * Initially, you have an array Arr of size N where Arr[i]=i for all 1 <= i <= N. You need to perform k operations on the array.
 *
 * In one operation, you create another array Brr of size N, where Brr[i]=sum of Arr[i] for all j from 1 <= j <= i.
 * After each operation, you will replace values in Arr with values in Brr.
 *
 * Let's say N=4 and Arr=[1,2,3,4], then after performing one operation Arr=[1,3,6,10]
 *
 * Find the sum of all elements of Arr after performing exactly k operations modulo 10^9+7.
 *
 * Solution:
 *
 * First thing, we observe that
 * k=1: [1, 2, 3, 4, 5] = 1C0, 2C1, 3C2, 4C3, 5C4
 * k=2: [1, 3, 6, 10, 15] = 2C0, 3C1, 4C2, 5C3, 6C4
 * k=3: [1, 4, 10, 20, 35] = 3C0, 4C1, 5C2, 6C3, 7C4
 *
 * Did you see that pattern?
 * Our goal is to calculate the sum of (x+k)Cx where 0 <= x <= n-1
 *
 * What is nCr? nCr = n!/r!/(n-r)!
 * so we can just write this O(n+k) solution with the help of mod inverse.
 *
 * https://leetcode.com/discuss/interview-question/1977572/UBER-OA
 */
public class TetrahedralSum {

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(solveSmart(arr, k));
    }

    static long solveSmart(int[] A, int k){
        int M = 10_000_007;
        ++k;
        int n = A.length;
        long[] inv = new long[n + k];
        long[] invf = new long[n + k];
        long[] fact = new long[n + k];
        fact[0] = fact[1] = inv[1] = invf[1] = invf[0] = 1;
        for (int i = 2 ; i < n + k; i++){
            inv[i] = M - M / i * inv[M % i] % M;
            invf[i] = invf[i-1] * inv[i] % M;
            fact[i] = fact[i-1] * i % M;
        }
        long ans = 0;
        for (int i = 0; i < n; i++){
            ans = (ans + fact[i + k] * invf[i] % M * invf[k] % M) % M;
        }
        return ans;
    }

}
