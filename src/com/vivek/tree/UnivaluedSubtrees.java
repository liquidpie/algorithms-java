package com.vivek.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * In this problem you are given a com.vivek.tree of N nodes.Each of the nodes
 * will be numbered from 0 to N-1 and each node i(node numbered i) is 
 * associated with a value vi.Assume the com.vivek.tree is rooted at node 0.A node Y
 * is said to be desecented of node X if X occurs in the path from node Y 
 * to node 0. A subtree rooted at node X is defined as set of all nodes 
 * which are decendants of X and edges Between them( including X). 
 * A subtree is called univalued if the value of all the nides in the subtree are equal. 
 * Now give the com.vivek.tree and the values associated with nodes in the com.vivek.tree .
 * You are required to find the number of rooted univalued subtrees in the com.vivek.tree.
 * 
 * Input: 
 * First line contains an interger N which is the number o0f nodes in the com.vivek.tree .
 * Next N lines contain an integer each which are values associated with nodes i.e, 
 * ith line contains the value associated  with node i-1.Next N-1 lines give the 
 * information of edges in the com.vivek.tree .Each line contains two Space separated integers
 *  X and Y which says that there is an edge between node X and node Y. 
 *  
 * Output: 
 * Given the input of com.vivek.tree in above format you have to print the number of Univalued
 * subtrees that are contained in the given com.vivek.tree.You just have tp print a single integer.
 * 
 * Sample Input:
 * 5 
 * 0 
 * 0 
 * 1 
 * 1 
 * 1 
 * 0 1 
 * 0 2 
 * 2 3 
 * 2 4
 * 
 * Sample Output: 
 * 4
 */
public class UnivaluedSubtrees {

	private static int count = 0;
	
	public static void main (String[] args) throws Exception
	{
		try(Scanner in = new Scanner(System.in)) {
			int n = in.nextInt();

			List<Node> nodeList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				UnivaluedSubtrees.Node node = new UnivaluedSubtrees.Node(in.nextInt());
			    nodeList.add(node);
			}
			
			for (int i = 0; i < n-1; i++) {
			    int n1 = in.nextInt();
			    int n2 = in.nextInt();

	            if (nodeList.get(n1).left == null) {
	                nodeList.get(n1).left = nodeList.get(n2);
	            }
			    else {
			        nodeList.get(n1).right = nodeList.get(n2);
			    }
			}
			
			Node root = nodeList.get(0);
			
			inOrderTraversal(root);
			
			countUnivaluedSubTrees(root);
			
			System.out.println(count);
		}
	}
	
	private static class Node {
	    int data;
	    Node left;
	    Node right;
	    Node (int val) {
	        data = val;
	    }
	}
	
	private static void inOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		
		inOrderTraversal(root.left);
		System.out.println(root.data);
		inOrderTraversal(root.right);
	}
	
	private static int countUnivaluedSubTrees(Node root) {
	    if (root == null) {
	        return Integer.MAX_VALUE;
	    }
	    
	    int leftCount = countUnivaluedSubTrees(root.left);
	    int rightCount = countUnivaluedSubTrees(root.right);
	    
	    if (leftCount == Integer.MAX_VALUE && rightCount == Integer.MAX_VALUE) {
	        count++;
	        return root.data;
	    }
	    else if (leftCount == Integer.MAX_VALUE || rightCount == Integer.MAX_VALUE) {
	        if (root.data == leftCount || root.data == rightCount) {
	            count++;
	            return root.data;
	        }
	        else {
	            return Integer.MIN_VALUE;
	        }
	    }
	    else {
	        if (root.data == leftCount && root.data == rightCount) {
	            count++;
	            return root.data;
	        }
	        else {
	            return Integer.MIN_VALUE;
	        }
	    }
	}
	
}
