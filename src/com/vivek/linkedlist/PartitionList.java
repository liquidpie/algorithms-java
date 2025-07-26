package com.vivek.linkedlist;

/**
 * 86. Partition List
 *
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example 1:
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 * Example 2:
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 * Reference: https://leetcode.com/problems/partition-list
 */
public class PartitionList {

    public static void main(String[] args) {
        {
            Node head = new Node(1);
            head.next = new Node(4);
            head.next.next = new Node(3);
            head.next.next.next = new Node(2);
            head.next.next.next.next = new Node(5);
            head.next.next.next.next.next = new Node(2);

            PartitionList helper = new PartitionList();
            Node newHead = helper.partition(head, 3);

            Node.print(newHead);
        }

        {
            Node head = new Node(2);
            head.next = new Node(1);
            PartitionList helper = new PartitionList();
            Node newHead = helper.partition(head, 2);

            Node.print(newHead);
        }
    }

    public Node partition(Node head, int x) {
        if (head == null)
            return head;

        Node firstHead = new Node(-1);
        Node first = firstHead;
        Node secondHead = new Node(-1);
        Node second = secondHead;

        Node node = head;
        Node prev = null;
        while (node != null) {
            if (node.data < x) {
                first.next = node;
                first = first.next;
            } else {
                second.next = node;
                second = second.next;
            }
            prev = node;
            node = node.next;
            prev.next = null;
        }

        first.next = secondHead.next;

        return firstHead.next;
    }

}
