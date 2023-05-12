package com.vivek.linkedlist;

import java.util.Stack;

/**
 * Function to check if a singly linked list is palindrome
 *
 * https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 */
public class LinkedListPalindrome {

    /**
     *  A simple solution is to use a stack of list nodes. This mainly involves three steps.
     *  Traverse the given list from head to tail and push every visited node to stack.
     *  Traverse the list again. For every visited node, pop a node from the stack and compare data of popped node with the currently visited node.
     *  If all nodes matched, then return true, else false.
     */
    static boolean isPalindromeUsingStack(Node head) {
        Node slow = head;
        Stack<Integer> stack = new Stack<>();

        while (slow != null) {
            stack.push(slow.data);
            slow = slow.next;
        }

        while (head != null) {
            int data = stack.pop();
            if (data != head.data) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

}
