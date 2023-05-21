package com.vivek.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement Stack using Queues
 *
 * Given a Queue data structure that supports standard operations like enqueue() and dequeue().
 * The task is to implement a Stack data structure using only instances of Queue and Queue operations allowed on the instances.
 *
 * Time Complexity:
 *
 *     Push operation: O(N), As all the elements need to be popped out from the Queue (q1) and push them back to Queue (q2).
 *     Pop operation: O(1), As we need to remove the front element from the Queue.
 * https://www.geeksforgeeks.org/implement-stack-using-queue/
 */
public class StackUsingQueue {

    Queue<Integer> q = new LinkedList<>();

    /**
     *     Enqueue x to q2.
     *     One by one dequeue everything from q1 and enqueue to q2.
     *     Swap the queues of q1 and q2.
     */
    void push(int x) {
        Queue<Integer> q_ = new LinkedList<>();
        q_.add(x);
        while (!q.isEmpty()) {
            q_.add(q.remove());
        }
        q = q_;
    }

    void pop() {
        if (q.isEmpty())
            return;
        q.remove();
    }

    int peek() {
        if (q.isEmpty())
            return -1;
        return q.peek();
    }

}
