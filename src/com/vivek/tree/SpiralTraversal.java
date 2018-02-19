package com.vivek.tree;

import java.util.Stack;

/**
 * We usually use a queue for level order traversal of a tree.
 * If we want to use the same here, how will we change direction of traversal on every alternate level?
 * If we think logically then we will see that we need stack in place of queue.
 * Now again a single stack won't serve our purpose here, we need 2 stacks:
 * One stack for printing node from left to right and one stack for printing node from right to left.
 * Note that for printing node from left to right,right node has to be push first into stack.
 * All nodes of one level are put in either one of these stacks.
 */

public class SpiralTraversal {
	
	void spiralLevelOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		
		Stack<Node> stack1 = new Stack<>();
		Stack<Node> stack2 = new Stack<>();
		
		stack1.push(root);
		
		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			
			while (!stack1.isEmpty()) {
				Node temp = stack1.pop();
				System.out.print(temp.data + " ");
				
				if (temp.right != null) {
					stack2.push(temp.right);
				}
				
				if (temp.left != null) {
					stack2.push(temp.left);
				}
			}
			
			while (!stack2.isEmpty()) {
				Node temp = stack2.pop();
				System.out.print(temp.data + " ");
				
				if (temp.left != null) {
					stack1.push(temp.left);
				}
				
				if (temp.right != null) {
					stack1.push(temp.right);
				}
			}
		}
	}
}
