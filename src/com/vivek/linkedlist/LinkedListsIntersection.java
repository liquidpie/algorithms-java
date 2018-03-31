package com.vivek.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * There are two singly linked lists in a system. By some programming error the end node of one of
 * the linked list got linked into the second list, forming a inverted Y shaped list.
 * Write a program to get the point where two linked list merge.
 */
public class LinkedListsIntersection {

    /**
     Use 2 nested for loops. Outer loop will be for each node of the 1st list and inner loop will be for 2nd list.
     In the inner loop, check if any of nodes of 2nd list is same as the current node of first linked list.
     Time complexity of this method will be O(mn) where m and n are the number of nodes in two lists.
     */
    Node usingTwoLoops(Node list1, Node list2) {
        if (list1 == null || list2 == null)
            return null;

        Node head = list2;
        while (list1 != null) {
            while (list2 != null) {
                if (list1 == list2)
                    return list1;
                list2 = list2.next;
            }
            list1 = list1.next;
            head = head.next;
            list2 = head;
        }
        return null;
    }

    Node usingHashMap(Node list1, Node list2) {
        if (list1 == null || list2 == null)
            return null;

        Set<Node> table = new HashSet<>();
        while(list1 != null) {
            table.add(list1);
            list1 = list1.next;
        }

        while (list2 != null) {
            if (table.contains(list2))
                return list2;
            list2 = list2.next;
        }

        return null;
    }


    /**
     1) Get count of the nodes in first list, let count be c1.
     2) Get count of the nodes in second list, let count be c2.
     3) Get the difference of counts d = abs(c1 – c2)
     4) Now traverse the bigger list from the first node till d nodes so that from here onwards both the lists have equal no of nodes.
     5) Then we can traverse both the lists in parallel till we come across a common node. (Note that getting a common node is done by comparing the address of the nodes)
     */
    Node usingNodeCountDifference(Node list1, Node list2) {

        class Util {
            int getCount(Node node) {
                Node current = node;
                int count = 0;

                while (current != null) {
                    count++;
                    current = current.next;
                }
                return count;
            }
        }

        Util util = new Util();

        int l1 = util.getCount(list1);
        int l2 = util.getCount(list2);

        int d = Math.abs(l1 - l2);

        Node current1 = list1;
        Node current2 = list2;

        if (l1 < l2) {
            current1 = list2;
            current2 = list1;
        }

        for (int i = 0; i < d; i++) {
            if (current1 == null) {
                return null;
            }
            current1 = current1.next;
        }
        while (current1 != null && current2 != null) {
            if (current1.data == current2.data) {
                return current1;
            }
            current1 = current1.next;
            current2 = current2.next;
        }
        return null;


    }

    /**
     This solution requires modifications to basic linked list data structure.
     Have a visited flag with each node. Traverse the first linked list and keep marking visited nodes.
     Now traverse second linked list, If you see a visited node again then there is an intersection point,
     return the intersecting node. This solution works in O(m+n) but requires additional information with each node.
     */
    Node usingVisitedFlag(CustomNode list1, CustomNode list2) {
        if (list1 == null || list2 == null)
            return null;

        while (list1 != null) {
            list1.visited = true;
            list1 = (CustomNode) list1.next;
        }

        while (list2 != null) {
            if (list2.visited)
                return list2;
            list2 = (CustomNode) list2.next;
        }
        return null;
    }

    static class CustomNode extends Node {
        boolean visited;

        public CustomNode(int data, boolean visited) {
            super(data);
            this.visited = visited;
        }
    }

}
