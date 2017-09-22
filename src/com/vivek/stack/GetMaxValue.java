package com.vivek.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by VJaiswal on 19/03/17.
 */
public class GetMaxValue {

    static Stack<Long> stack = new Stack<>();
    static Stack<Long> maxStack = new Stack<>();

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int opt = in.nextInt();
                switch (opt) {
                    case 1:
                        long elm = in.nextLong();
                        push(elm);
                        break;
                    case 2:
                        pop();
                        break;
                    case 3:
                        System.out.println(getMax());
                        break;
                }
            }
        }
    }

    static void push(long value) {
        stack.push(value);
        if (maxStack.isEmpty() || value >= maxStack.peek()) {
            maxStack.push(value);
        }
    }

    static void pop() {
        if (stack.peek().equals(maxStack.peek())) {
            maxStack.pop();
        }
        stack.pop();
    }

    static long getMax() {
        return maxStack.isEmpty() ? Integer.MIN_VALUE : maxStack.peek();
    }

}
