package com.vivek.tree.view;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeftOrRightViewUsingQueue {
	
	// https://tekmarathon.com/2013/05/27/algorithm-to-find-left-view-and-right-view-of-binary-tree-in-java/
	public void printLeftNRightViewOfBT(Node node) {
	    Queue<Node> queue = new LinkedList<Node>();
	    Node LS = new Node(0);
	    queue.add(node);
	    queue.add(LS);
	 
	    // left view and right view arrays
	    List<Node> leftView = new ArrayList<Node>();
	    List<Node> rightView = new ArrayList<Node>();
	    leftView.add(node);
	 
	    while (!queue.isEmpty()) {
	        Node currentNode = queue.remove();
	        if (!queue.isEmpty() && currentNode.equals(LS)) {
	            leftView.add(queue.peek());
	            queue.add(LS);
	        }
	        if (!queue.isEmpty() && queue.peek().equals(LS)) {
	            rightView.add(currentNode);
	        }
	        if (currentNode.left != null) {
	            queue.add(currentNode.left);
	        }
	        if (currentNode.right != null) {
	            queue.add(currentNode.right);
	        }
	    }
	 
	    // print views
//	    printViews(leftView);
//	    printViews(rightView);
	}

}
