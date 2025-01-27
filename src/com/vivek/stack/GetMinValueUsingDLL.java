package com.vivek.stack;

/**
 * Design a stack which returns min value in constant time
 *
 * Approach:
 * We've used an auxiliary minStack to maintain in previous case. This required two data structures to return min value.
 * Here we're going to use Doubly Linked List with a modified Node which contains reference to min value.
 *
 * Tail always points to the last element
 *
 * Example: 2->3->1->4
 */
public class GetMinValueUsingDLL {

    private static Node tail = null;

    static void push(int num) {
        Node node = new Node(num);
        if (tail == null) {
            node.min = num;
        } else {
            node.min = Math.min(num, tail.min);
            tail.next = node;
            node.prev = tail;
        }

        tail = node;
    }

    static int pop() {
        if (tail == null) {
            return -1;
        }
        Node node = tail;
        tail = node.prev;
        return node.val;
    }

    static int min() {
        return tail != null ? tail.min : -1;
    }

    static class Node {
        int val;
        Node next;
        Node prev;
        int min;

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        push(2);
        push(3);
        push(1);
        push(4);
        System.out.println(min());
        pop();
        System.out.println(min());
        pop();
        System.out.println(min());
        pop();
        System.out.println(min());
        pop();
        System.out.println(min());
    }
}
