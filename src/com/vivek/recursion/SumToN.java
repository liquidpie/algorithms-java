package com.vivek.recursion;

public class SumToN {

    public static void main(String[] args) {
        System.out.println(sumTo(4));
    }

    static int sumTo(int n) {
        if (n == 0)
            return 0;

        int subProblemResult = sumTo(n - 1);
        return subProblemResult + n;
    }

}
