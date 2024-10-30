package com.vivek.linkedlist.pattern.reverse;

import com.vivek.linkedlist.Node;

public class ReverseLinkedList {

	// Iterative solution for reversal
	public Node iterativeReverse(Node head) {
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

	// Recursive solution for reversal
	Node recursiveReverse(Node head) {
		/**
		 What is the reverse of null (the empty list)? null.
		 What is the reverse of a one element list? the element.
		 What is the reverse of an n element list? the reverse of the second element on followed by the first element.
		 */

		if (head == null) { // Null List
			return null;
		}

		if (head.next == null) {	// Single element list
			return head;
		}

		Node secondElem = head.next;

		// need to unlink list from the rest or you will get a cycle
		head.next = null;

		// then we reverese everything from the second element on
		Node reverseRest = recursiveReverse(secondElem);

		// then we join the two lists
		secondElem.next = head;

		return reverseRest;
	}

}
