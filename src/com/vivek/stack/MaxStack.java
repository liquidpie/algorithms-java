package com.vivek.stack;

import java.util.*;

/**
 * 716. Max Stack
 *
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
 *
 * Implement the MaxStack class:
 *
 * - MaxStack() Initializes the stack object.
 * - void push(int x) Pushes element x onto the stack.
 * - int pop() Removes the element on top of the stack and returns it.
 * - int top() Gets the element on the top of the stack without removing it.
 * - int peekMax() Retrieves the maximum element in the stack without removing it.
 * - int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
 *
 * You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.
 *
 * Example 1:
 *
 * Input
 * ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
 * [[], [5], [1], [5], [], [], [], [], [], []]
 * Output
 * [null, null, null, null, 5, 5, 1, 5, 1, 5]
 *
 * Explanation
 * MaxStack stk = new MaxStack();
 * stk.push(5);   // [5] the top of the stack and the maximum number is 5.
 * stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
 * stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
 * stk.top();     // return 5, [5, 1, 5] the stack did not change.
 * stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
 * stk.top();     // return 1, [5, 1] the stack did not change.
 * stk.peekMax(); // return 5, [5, 1] the stack did not change.
 * stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
 * stk.top();     // return 5, [5] the stack did not change.
 *
 * Approach:
 *
 * - TreeMap:
 *      Use a TreeMap to maintain the frequency of elements in the stack. This will allow us to get the maximum element in O(log n) time.
 *      The TreeMap keeps elements sorted, and retrieving the maximum element is as simple as querying the last entry.
 * - Doubly Linked List:
 *      Use a Doubly Linked List to store the stack elements. This allows for efficient insertion, deletion, and traversal in O(1) for operations like pop() and push().
 *
 * Reference:
 * https://leetcode.com/problems/max-stack/description/
 */
public class MaxStack {

    private DoublyLinkedList dll;
    private TreeMap<Integer, List<Node>> map;

    public MaxStack() {
        dll = new DoublyLinkedList();
        map = new TreeMap<>();
    }

    // Push element onto the stack
    public void push(int x) {
        Node node = dll.add(x);
        map.computeIfAbsent(x, k -> new ArrayList<>()).add(node);
    }

    // Remove the element on top of the stack
    public int pop() {
        Node node = dll.pop();
        int value = node.value;
        removeFromMap(value, node);
        return value;
    }

    // Get the top element of the stack
    public int peek() {
        return dll.peek();
    }

    // Get the maximum element in the stack
    public int peekMax() {
        return map.lastKey();
    }

    // Remove and return the maximum element in the stack
    public int popMax() {
        int max = map.lastKey();
        Node node = map.get(max).get(map.get(max).size() - 1);
        removeFromMap(max, node);
        dll.remove(node);
        return max;
    }

    // Helper method to remove a value from the TreeMap
    private void removeFromMap(int value, Node node) {
        map.get(value).remove(node);
        if (map.get(value).isEmpty()) {
            map.remove(value);
        }
    }

    // Doubly Linked List Implementation
    private static class DoublyLinkedList {
        private Node head, tail;

        public DoublyLinkedList() {
            head = new Node(0); // Dummy head
            tail = new Node(0); // Dummy tail
            head.next = tail;
            tail.prev = head;
        }

        public Node add(int value) {
            Node node = new Node(value);
            node.next = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;
            return node;
        }

        public Node pop() {
            return remove(tail.prev);
        }

        public int peek() {
            return tail.prev.value;
        }

        public Node remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
    }

    private static class Node {
        int value;
        Node prev, next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(1);
        maxStack.push(3);
        maxStack.push(2);
        maxStack.push(5);
        maxStack.push(4);
        maxStack.push(2);
        maxStack.push(5);

        System.out.println(maxStack.peekMax());
        maxStack.push(6);
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.pop());
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.peekMax());
    }

}
