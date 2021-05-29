package com.vivek.linkedlist;

public class Node {
	
	public int data;
	public Node next;
	
	public Node(int data) {
		this(data, null);
	}
	
	public Node(int data, Node prev) {
		this.data = data;
		if (prev != null) {
			prev.next = this;
		}
	}

	static void print(Node head) {
		Node node = head;
		String delimiter = "";
		while (node != null) {
			System.out.print(delimiter + node.data);
			delimiter = " -> ";
			node = node.next;
		}
	}

	/**
	 * Given a pointer to a node to be deleted, delete the node. Note that we don’t have a pointer to the head node.
	 * @param node node to be deleted
	 */
	static void deleteNode(Node node) {
		Node temp = node.next;
		node.data = temp.data;
		node.next = temp.next;
		temp = null;
	}

}
