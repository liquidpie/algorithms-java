package com.vivek.linkedlist;

/**
 * Merge sort is often preferred for sorting a linked list.
 * The slow random-access performance of a linked list makes some other algorithms (such as quicksort) perform poorly,
 * and others (such as heapsort) completely impossible.
 */
public class MergeSortLinkedList {

    static Node mergeSort(Node head) {
        if (head == null || head.next == null)
            return head;

        Node middle = middleNode(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    private static Node merge(Node list1, Node list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list2.data < list1.data) {
            Node temp = list1;
            list1 = list2;
            list2 = temp;
        }

        Node head = list1;

        while (list1.next != null) {
            if (list1.next.data > list2.data) {
                Node temp = list1.next;
                list1.next = list2;
                list2 = temp;
            }
            list1 = list1.next;
        }

        list1.next = list2;

        return head;
    }

    private static Node middleNode(Node head) {
        if (head == null)
            return null;

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.next = new Node(2);
        head.next.next = new Node(5);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(9);
        head.next.next.next.next.next.next = new Node(8);

        head = mergeSort(head);
        Node.print(head);
    }

}
