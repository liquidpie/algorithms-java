package com.vivek.linkedlist;

public class ReverseLinkedList {
	
	Node reverse(Node head) {
	    Node prev = null;
	    Node current = head;
	    Node next = null;
	    
	    while (current != null) {
	        next = current.next;
	        current.next = prev;
	        prev = current;
	        
	        current = next;
	    }
	    
	    return prev;
	}

}
