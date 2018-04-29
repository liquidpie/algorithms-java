package com.vivek.stack;

import java.util.Stack;

/**
 * Sort a Stack in ascending order
 */
public class SortStack {

    /**
     * Sorting can be performed with one more stack.
     * The idea is to pull an item from the original stack and push it on the other stack.
     * If pushing this item would violate the sort order of the new stack,
     * we need to remove enough items from it so that it’s possible to push the new item.
     * Since the items we removed are on the original stack, we’re back where we started.
     *
     * The algorithm is O(N^2)
     */
    Stack<Integer> sort(Stack<Integer> s) {
        Stack<Integer> r = new Stack<>();

        while (!s.isEmpty()) {
            int tmp = s.pop();

            while (!r.isEmpty() && r.peek() > tmp) {
                s.push(r.pop());
            }

            r.push(tmp);
        }

        return r;
    }
}
