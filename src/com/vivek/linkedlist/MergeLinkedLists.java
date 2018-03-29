package com.vivek.linkedlist;

/**
 * You have two singly linked lists that are already sorted,
 * you have to merge them and return a the head of the new list
 * without creating any new extra nodes. The returned list should be sorted as well
 */
public class MergeLinkedLists {

    Node mergeRecursively(Node list1, Node list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.data < list2.data) {
            list1.next = mergeRecursively(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeRecursively(list2.next, list1);
            return list2;
        }
    }

    Node mergeIteratively(Node list1, Node list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        Node head;
        if (list1.data < list2.data) {
            head = list1;
        } else {
            head = list2;
            list2 = list1;
            list1 = head;
        }

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

}
