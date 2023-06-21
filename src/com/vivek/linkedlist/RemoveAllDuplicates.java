package com.vivek.linkedlist;

/**
 * Remove Duplicates from Sorted List II
 *
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 *
 * Example 1:
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 *
 * Example 2:
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 *
 * Approach:
 * The idea is to maintain a pointer (prev) to the node which just previous to the block of nodes we are checking for duplicates.
 * In the first example, the pointer prev would point to 23 while we check for duplicates for node 28.
 * Once we reach the last duplicate node with value 28 (name it current pointer),
 * we can make the next field of prev node to be the next of current and update current=current.next.
 * This would delete the block of nodes with value 28 which has duplicates.
 *
 * Time Complexity: O(n)
 * Auxiliary Space: O(1) because extra space is not required in removal of duplicates
 *
 * Solution: https://www.geeksforgeeks.org/remove-occurrences-duplicates-sorted-linked-list/
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii
 */
public class RemoveAllDuplicates {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(5);

        Node.print(removeDuplicates(head));

    }

    static Node removeDuplicates(Node head) {
        if (head == null)
            return null;

        Node fakeHead = new Node(-1);
        fakeHead.next = head;

        Node prev = fakeHead;
        Node current = head;

        while (current != null) {

            // Until the current and previous values
            // are same, keep updating current
            while (current.next != null && prev.next.data == current.next.data)
                current = current.next;

            // If current has unique value i.e current
            // is not updated, Move the prev pointer
            // to next node
            if (prev.next == current)
                prev = current;
            // When current is updated to the last
            // duplicate value of that segment, make
            // prev the next of current*/
            else
                prev.next = current.next;

            current = current.next;

        }

        head = fakeHead.next;
        return head;
    }

}
