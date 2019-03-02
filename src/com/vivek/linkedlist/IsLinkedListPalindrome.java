package com.vivek.linkedlist;

import java.util.Stack;

/**
 * Created by VJaiswal on 22/06/18.
 */
public class IsLinkedListPalindrome {

    static boolean isPalindromeUsingStack(Node head) {
        if (head == null)
            return false;

        Stack<Node> stack = new Stack<>();
        Node node = head;

        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        // traverse again
        node = head;
        while (node != null && !stack.isEmpty()) {
            if (stack.pop().data != node.data)
                return false;
            node = node.next;
        }

        if (node != null || !stack.isEmpty())
            return false;

        return true;
    }

    static boolean isPalindromeWithoutExtraSpace(Node head) {
        if (head == null)
            return false;

        // find middle element
        Node slowPointer = head;
        Node fastPointer = head;
        int count = 0;
        while (fastPointer != null) {
            count++;
            fastPointer = fastPointer.next;
            if ((count & 1) == 0) {
                slowPointer = slowPointer.next;
            }
        }
        Node middle = slowPointer;

        // reverse the second half
        ReverseLinkedList reverseUtil = new ReverseLinkedList();
        Node head2 = reverseUtil.iterativeReverse(middle.next);

        // Compare first half and second half
        Node node1 = head;
        Node node2 = head2;
        boolean result = true;
        while (node1 != middle && node2 != null) {
            if (node1.data != node2.data) {
                result = false;
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }

        if (node1 != middle || node2 != null)
            result = false;

        // reverse the second half again
        reverseUtil.iterativeReverse(head2);

        return result;
    }



    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        System.out.println(isPalindromeWithoutExtraSpace(head));
    }

}
