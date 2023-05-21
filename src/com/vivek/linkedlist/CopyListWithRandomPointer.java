package com.vivek.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Copy List with Random Pointer
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 *     val: an integer representing Node.val
 *     random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 *
 * Your code will only be given the head of the original linked list.
 *
 * Example 1:
 *
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * Example 2:
 *
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 *
 * Example 3:
 *
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 *
 *          * Approach: We maintain a map to store the mapping between the
 *          * old and new node. We then just iterate through the original
 *          * list and use the new nodes for mapping.
 *          *
 *          * Time Complexity: O(n)
 *          * Space Complexity: O(n)
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

    public static void main(String[] args) {
        Node head = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);
        head.next = node1;
        head.next.next = node2;
        head.next.next.next = node3;
        head.next.next.next.next = node4;

        node1.random = head;
        node2.random = node4;
        node3.random = node2;
        node4.random = head;

        Node result = copyRandomList(head);

        while (result != null) {
            String randomVal = result.random != null ? result.random.val + "" : "null";
            System.out.print("[" + result.val + ", " + randomVal + "] -> ");
            result = result.next;
        }
    }

    static Node copyRandomList(Node head) {
        if (head == null)
            return null;

        Map<Node, Node> map = new HashMap<>();
        Node run = head;
        while (run != null) {
            map.put(run, new Node(run.val));
            run = run.next;
        }

        run = head;
        while (run != null) {
            Node newNode = map.get(run);
            newNode.next = map.get(run.next);
            newNode.random = map.get(run.random);
            run = run.next;
        }

        return map.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
