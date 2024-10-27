package com.vivek.linkedlist.pattern.fastslow;

import com.vivek.linkedlist.Node;

import java.util.Stack;

/**
 * Function to check if a singly linked list is palindrome
 *
 * Reference:
 * 1. https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 * 2. Grokking the Coding Interview
 *    Pattern: Fast Slow Pointer
 */
public class LinkedListPalindrome {

    /**
     *  A simple solution is to use a stack of list nodes. This mainly involves three steps.
     *  Traverse the given list from head to tail and push every visited node to stack.
     *  Traverse the list again. For every visited node, pop a node from the stack and compare data of popped node with the currently visited node.
     *  If all nodes matched, then return true, else false.
     *
     * Time complexity #
     * The above algorithm will have a time complexity of O(N ) where ‘N’ is the number of nodes in the LinkedList.
     *
     * Space complexity #
     * The algorithm runs in constant space O(1).
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

    /**
     * Solution #
     * As we know, a palindrome LinkedList will have nodes values that read the same backward or forward.
     * This means that if we divide the LinkedList into two halves, the node values of the first half in the forward
     * direction should be similar to the node values of the second half in the backward direction.
     * As we have been given a Singly LinkedList, we can’t move in the backward direction. To handle this, we will perform the following steps:
     * 1. We can use the Fast & Slow pointers method similar to Middle of the LinkedList to find the middle node of the LinkedList.
     * 2. Once we have the middle of the LinkedList, we will reverse the second half.
     * 3. Then, we will compare the first half with the reversed second half to see if the LinkedList represents a palindrome.
     * 4. Finally, we will reverse the second half of the LinkedList again to revert and bring the LinkedList back to its original form.
     */
    static boolean isPalindromeUsingReverse(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node headSecondHalf = reverse(slow);
        Node node = headSecondHalf;

        while (head != null && node != null) {
            if (head.data != node.data)
                break; // not a palindrome
            head = head.next;
            node = node.next;
        }
        reverse(headSecondHalf); // revert the reverse of second half
        if (head == null || headSecondHalf == null)
            return true;
        return false;
    }

    static Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

}
