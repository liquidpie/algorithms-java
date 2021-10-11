package com.vivek.array.examples;

/**
 * There's a town with N folks. Out of N, there could be someone who is spy.
 * A spy satisfies following constraints
 * 1. Spy trusts nobody
 * 2. Everybody trusts spy
 * 3. There could be only one person who satisfies constraint 1 & 2
 *
 * Input: You'll be given number of folks N and an array of trust with first element trusting second element
 * Output: Return who's Spy else return -1
 *
 * Example:
 * N = 3, trust = [[1, 3], [2, 3]]
 * Result: 3
 *
 * N = 3, trust = [[1, 3], [2, 3], [3, 1]]
 * Result = -1
 */
public class FindSpy {

    static int findSpy(int N, int[][] trust) {
        int[] spies = new int[N];
        for (int i = 0; i < trust.length; i++) {
            spies[trust[i][0] - 1]--;
            spies[trust[i][1] - 1]++;
        }

        for (int i = 0; i < N; i++) {
            if (spies[i] == N - 1) {
                return i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int N = 3;
        int[][] trust = {
                {1, 3},
                {2, 3},
                {3, 1}
        };
        System.out.println(findSpy(N, trust));
    }

}
