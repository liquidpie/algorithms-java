package com.vivek.linkedlist;

import java.util.Arrays;
import java.util.Stack;

/**
 * Next Greater Node In Linked List
 *
 * You are given the head of a linked list with n nodes.
 *
 * For each node in the list, find the value of the next greater node. That is, for each node,
 * find the value of the first node that is next to it and has a strictly larger value than it.
 *
 * Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed).
 * If the ith node does not have a next greater node, set answer[i] = 0.
 *
 * Example 1:
 *
 * Input: head = [2,1,5]
 * Output: [5,5,0]
 *
 * Example 2:
 *
 * Input: head = [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 *
 *      * Approach: We use a stack to keep track of the elements encountered so far.
 *      * Once an element is encountered which is more than the top of the stack, that
 *      * element gets updated as the next greater element.
 *      *
 *      * Time Complexity: O(n)
 *      * Space Complexity: O(n)
 *
 * https://leetcode.com/problems/next-greater-node-in-linked-list/
 */
public class NextLargerNodes {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(7);
        head.next.next = new Node(5);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(9);
        head.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next = new Node(5);
        head.next.next.next.next.next.next.next = new Node(1);

        System.out.println(Arrays.toString(nextLargerNodes(head)));
    }

    static int[] nextLargerNodes(Node head) {
        if (head == null)
            return new int[0];

        int[] res = new int[getLen(head)];
        Stack<int[]> s = new Stack<>();
        Node curr = head;

        int i = 0;
        while (curr != null) {
            while (!s.isEmpty() && s.peek()[1] < curr.data) {
                int idx = s.pop()[0];
                res[idx] = curr.data;
            }
            s.push(new int[]{i, curr.data});
            i++;
            curr = curr.next;
        }
        return res;
    }

    private static int getLen(Node head) {
        int l = 0;
        Node curr = head;
        while (curr != null) {
            l++;
            curr = curr.next;
        }
        return l;
    }

}
