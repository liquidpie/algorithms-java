package com.vivek.linkedlist.pattern.fastslow;

import com.vivek.linkedlist.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * There are three ways to solve this problem
 * 1. Use Hashing
 * 
 * Traverse the list one by one and keep putting the node addresses 
 * in a Hash Table. At any point, if NULL is reached then return false and if next
 * of current node points to any of the previously stored nodes in Hash then return true.
 * 
 * 2. Mark Visited Nodes
 * 
 * This solution requires modifications to basic linked list data structure. 
 * Have a visited flag with each node. Traverse the linked list and keep marking visited nodes.
 * If you see a visited node again then there is a loop.
 * This solution works in O(n) but requires additional information with each node.
 * 
 * 3. Floyd's Cycle finding algortihm
 * 
 * This is the fastest method. Traverse linked list using two pointers. 
 * Move one pointer by one and other pointer by two. 
 * If these pointers meet at some node then there is a loop. 
 * If pointers do not meet then linked list doesn�t have loop.
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: Fast Slow
 */
public class LoopInLinkedList {

	public static void main(String... args) {
		Node head = new Node(1);
		Node node;
		node = new Node(2, head);
		node = new Node(3, node);
		node = new Node(4, node);
		node = new Node(5, node);
		Node node1 = new Node(6, node);
		node1.next = node;

		System.out.println(hashSaveNodes(head));
	}

	private static boolean floydCycleFindingAlgorithm(Node node) {
		Node slow = node;
		Node fast = node;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast)
				return true; // found the cycle
		}
		return false;
	}

	private static boolean hashSaveNodes(Node node) {
		if (node == null)
			return false;
		Set<Node> set = new HashSet<>();
		
		while (node != null) {
			if (set.contains(node)) {
				return true;
			}
			else {
				set.add(node);
			}
			node = node.next;
		}
		return false;
	}

}
