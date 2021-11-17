package com.vivek.linkedlist;

import java.util.Stack;

/**
 * Add two numbers represented by linked lists
 *
 * Given two numbers represented by two lists, write a function that returns the sum list. The sum list is a list representation of the addition of two input numbers.
 *
 * Input:
 * List1: 5->6->3 // represents number 563
 * List2: 8->4->2 // represents number 842
 * Output:
 * Resultant list: 1->4->0->5 // represents number 1405
 * Explanation: 563 + 842 = 1405
 *
 * Input:
 * List1: 7->5->9->4->6 // represents number 75946
 * List2: 8->4 // represents number 84
 * Output:
 * Resultant list: 7->6->0->3->0// represents number 76030
 * Explanation: 75946+84=76030
 *
 * Reference: https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists
 */
public class AddNumbers {

    /**
     * Time Complexity: O(n + m)
     * Space Complexity: O(n + m)
     */
    static Node usingStack(Node n1, Node n2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (n1 != null) {
            stack1.push(n1.data);
            n1 = n1.next;
        }

        while (n2 != null) {
            stack2.push(n2.data);
            n2 = n2.next;
        }

        int sum = 0;
        Node head = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            sum += stack1.pop() + stack2.pop();
            head = addBefore(head, sum % 10);
            sum /= 10;
        }

        while (!stack1.isEmpty()) {
            sum += stack1.pop();
            head = addBefore(head, sum % 10);
            sum /= 10;
        }

        while (!stack2.isEmpty()) {
            sum += stack2.pop();
            head = addBefore(head, sum % 10);
            sum /= 10;
        }

        if (sum == 1) {
            head = addBefore(head, sum);
        }

        return head;
    }

    /**
     * The steps are:
     *     - Traverse the two linked lists in order to add preceding zeros in case a list is having lesser digits than the other one.
     *     - Start from the head node of both lists and call a recursive function for the next nodes.
     *     - Continue it till the end of the lists.
     *     - Creates a node for current digits sum and returns the carry.
     */
    static Node usingRecursion(Node n1, Node n2, int len1, int len2) {
        // pad smaller linked list with 0
        if (len1 < len2) {
            n1 = padWithZeros(n1, len2 - len1);
        } else if (len2 < len1) {
            n2 = padWithZeros(n2, len1 - len2);
        }

        Node result = new Node(0);
        if (sumNodesRecursive(n1, n2, result) == 1) {
            result.next = addBefore(result.next,1);
        }
        return result.next;
    }

    private static int sumNodesRecursive(Node n1, Node n2, Node result) {
        if (n1 == null) // when we reached end of list
            return 0;

        int sum = n1.data + n2.data + sumNodesRecursive(n1.next, n2.next, result);
        Node node = new Node(sum % 10);
        node.next = result.next;
        result.next = node;
        return sum / 10;
    }

    private static Node padWithZeros(Node node, int diff) {
        while (diff > 0) {
            node = addBefore(node, 0);
            diff--;
        }
        return node;
    }

    private static Node addBefore(Node head, int val) {
        Node node = new Node(val);
        node.next = head;
        return node;
    }

    public static void main(String[] args) {
        Node n1 = new Node(9);
        n1.next = new Node(9);
        n1.next.next = new Node(9);

        Node n2 = new Node(1);

        Node.print(usingRecursion(n1, n2, 3, 1));
    }

}
