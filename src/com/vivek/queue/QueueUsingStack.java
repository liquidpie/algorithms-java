package com.vivek.queue;

import java.util.Stack;

/**
 * Created by VJaiswal on 23/01/17.
 */
public class QueueUsingStack<T> {
    Stack<T> stackNewestOnTop = new Stack<T>();
    Stack<T> stackOldestOnTop = new Stack<T>();

    public void enqueue(T value) { // Push onto newest com.vivek.stack
        stackNewestOnTop.push(value);
    }

    public T peek() {
        sync();
        return stackOldestOnTop.peek();
    }

    public T dequeue() {
        sync();
        return stackOldestOnTop.pop();
    }

    void sync() {
        if (stackOldestOnTop.isEmpty()) {
            while (!stackNewestOnTop.isEmpty()) {
                stackOldestOnTop.push(stackNewestOnTop.pop());
            }
        }
    }
}
