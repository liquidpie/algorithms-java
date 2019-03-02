package com.vivek.tree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 A node x is there in output if x is the topmost node at its horizontal distance.
 Horizontal distance of left child of a node x is equal to horizontal distance of x minus 1,
 and that of right child is horizontal distance of x plus 1.

 1
 /     \
 2       3
 /  \    / \
 4    5  6   7
 Top view of the above binary tree is
 4 2 1 3 7

 1
 /   \
 2       3
 \
 4
 \
 5
 \
 6
 Top view of the above binary tree is
 2 1 3 6
 */
public class TopView {

	void topView(Node root) {
		if (root == null)
			return;

		int horizontalDist = 0;
		root.horizontalDist = horizontalDist;

		Map<Integer, Integer> map = new TreeMap<>();

		Queue<Node> queue = new LinkedList<>();

		queue.add(root);

		while (!queue.isEmpty()) {

			Node node = queue.poll();
			horizontalDist = node.horizontalDist;

			if (!map.containsKey(horizontalDist)) {
				map.put(horizontalDist, node.data);
			}

			if (node.left != null) {
				node.left.horizontalDist = horizontalDist - 1;
				queue.add(node.left);
			}

			if (node.right != null) {
				node.right.horizontalDist = horizontalDist + 1;
				queue.add(node.right);
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getValue() + " ");
		}

	}

	static class Node {
		int data;
		Node left;
		Node right;
		int horizontalDist;
	}
	
}
