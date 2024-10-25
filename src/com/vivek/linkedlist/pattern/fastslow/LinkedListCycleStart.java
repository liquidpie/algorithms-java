package com.vivek.linkedlist.pattern.fastslow;

import com.vivek.linkedlist.Node;

/**
 * Start of LinkedList Cycle (medium)
 *
 * Given the head of a Singly LinkedList that contains a cycle, write a function to find the starting node of the
 * cycle.
 *
 * Solution #
 * If we know the length of the LinkedList cycle, we can find the start of the cycle through the following steps:
 * 1. Take two pointers. Let’s call them pointer1 and pointer2 .
 * 2. Initialize both pointers to point to the start of the LinkedList.
 * 3. We can find the length of the LinkedList cycle using the approach discussed in LinkedList Cycle.
 *    Let’s assume that the length of the cycle is ‘K’ nodes.
 * 4. Move pointer2 ahead by ‘K’ nodes.
 * 5. Now, keep incrementing pointer1 and pointer2 until they both meet.
 * 6. As pointer2 is ‘K’ nodes ahead of pointer1 , which means, pointer2 must have completed one loop in the cycle
 *    when both pointers meet. Their meeting point will be the start of the cycle.
 *
 * Time Complexity #
 * As we know, finding the cycle in a LinkedList with ‘N’ nodes and also finding the length of the cycle requires O(N ).
 * Also, as we saw in the above algorithm, we will need O(N ) to find the start of the cycle.
 * Therefore, the overall time complexity of our algorithm will be O(N).
 *
 * Space Complexity #
 * The algorithm runs in constant space O(1).
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Fast Slow
 */
public class LinkedListCycleStart {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + findCycleStart(head).data);
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + findCycleStart(head).data);
        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + findCycleStart(head).data);
    }

    static Node findCycleStart(Node node) {
        Node slow = node;
        Node fast = node;
        int cycleLength = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                cycleLength = findCycleLength(slow);
                break;
            }
        }
        return findStart(node, cycleLength);
    }

    static int findCycleLength(Node slow) {
        Node current = slow;
        int len = 0;
        do {
            current = current.next;
            len++;
        } while (current != slow);
        return len;
    }

    static Node findStart(Node head, int k) {
        Node ptr1 = head;
        Node ptr2 = head;
        while (k > 0) {
            ptr2 = ptr2.next;
            k--;
        }

        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }


}
