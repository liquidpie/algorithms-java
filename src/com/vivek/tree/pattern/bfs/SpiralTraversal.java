package com.vivek.tree.pattern.bfs;

import com.vivek.tree.Node;

import java.util.*;

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

	static void zigZagTraversal(Node root) {
		if (root == null)
			return;

		List<List<Integer>> result = new ArrayList<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		boolean leftToRight = true;

		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			List<Integer> currentLevel = new LinkedList<>();
			for (int i = 0; i < levelSize; i++) {
				Node node = queue.poll();

				if (leftToRight) {
					currentLevel.add(node.data);
				} else {
					currentLevel.add(0, node.data);
				}

				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			result.add(currentLevel);
			leftToRight = !leftToRight;
		}

		System.out.println(result);
	}

	void spiralLevelOrderTraversalUsingStack(Node root) {
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

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		zigZagTraversal(root);

	}
}
