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

	@Override
	public String toString() {
		return "Node=" + data;
	}
}
