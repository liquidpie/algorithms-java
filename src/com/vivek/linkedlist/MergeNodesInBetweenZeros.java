package com.vivek.linkedlist;

/**
 * 2181. Merge Nodes in Between Zeros
 *
 * You are given the head of a linked list, which contains a series of integers separated by 0's.
 * The beginning and end of the linked list will have Node.val == 0.
 *
 * For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the sum of all the merged nodes.
 * The modified list should not contain any 0's.
 *
 * Return the head of the modified linked list.
 *
 * Example 1:
 *
 * Input: head = [0,3,1,0,4,5,2,0]
 * Output: [4,11]
 * Explanation:
 * The above figure represents the given linked list. The modified list contains
 * - The sum of the nodes marked in green: 3 + 1 = 4.
 * - The sum of the nodes marked in red: 4 + 5 + 2 = 11.
 *
 * Example 2:
 *
 * Input: head = [0,1,0,3,0,2,2,0]
 * Output: [1,3,4]
 * Explanation:
 * The above figure represents the given linked list. The modified list contains
 * - The sum of the nodes marked in green: 1 = 1.
 * - The sum of the nodes marked in red: 3 = 3.
 * - The sum of the nodes marked in yellow: 2 + 2 = 4.
 *
 * Reference:
 * https://leetcode.com/problems/merge-nodes-in-between-zeros/description/
 */
public class MergeNodesInBetweenZeros {

    public Node mergeNodes(Node head) {
        if (head == null)
            return head;

        Node current = head.next;
        Node newHead = null;
        Node prevZeroNode = null;

        int sum = 0;
        while (current != null) {
            if (current.data == 0) {
                current.data += sum;
                sum = 0;
                if (newHead == null)
                    newHead = current;
                if (prevZeroNode != null)
                    prevZeroNode.next = current;

                prevZeroNode = current;
            } else {
                sum += current.data;
            }
            current = current.next;
        }

        return newHead;
    }

}
