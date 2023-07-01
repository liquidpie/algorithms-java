package com.vivek.recursion;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacci(8));
    }

    static int fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
