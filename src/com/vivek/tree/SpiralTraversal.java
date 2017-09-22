package com.vivek.tree;

import java.util.Stack;

/**
 * We usually use a com.vivek.queue for level order traversal of a com.vivek.queue. If we want to use the same here, how will we change direction of traversal on every alternate level?
 * If we think logically then we will see that we need com.vivek.stack in place of com.vivek.queue. Now again a single com.vivek.stack won�t serve our purpose here, we need 2 stacks: One com.vivek.stack for printing node from left to right and one com.vivek.stack for printing node from right to left. Note that for printing node from left to right,right node has to be push first into com.vivek.stack. All nodes of one level are put in either one of these stacks.
 */
public class SpiralTraversal {
	
	void spiralLevelOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		
		Stack<Node> stackRL = new Stack<>();
		Stack<Node> stackLR = new Stack<>();
		
		stackRL.push(root);
		
		while (!stackRL.isEmpty() || !stackLR.isEmpty()) {
			
			while (!stackRL.isEmpty()) {
				Node temp = stackRL.pop();
				System.out.print(temp.data + " ");
				
				if (temp.right != null) {
					stackRL.push(temp.right);
				}
				
				if (temp.left != null) {
					stackRL.push(temp.left);
				}
			}
			
			while (!stackLR.isEmpty()) {
				Node temp = stackLR.pop();
				System.out.print(temp.data + " ");
				
				if (temp.left != null) {
					stackLR.push(temp.left);
				}
				
				if (temp.right != null) {
					stackLR.push(temp.right);
				}
			}
			
		}
	}

}
