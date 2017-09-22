package com.vivek.linkedlist;

public class ReversePrint {
	
	public void reversePrint(Node head) {
		if (head == null)
			return;
		
		reversePrint(head.next);
		System.out.println(head.data);
	}

}
