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

}
