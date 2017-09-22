package com.vivek.tree;

public class TopView {

	void topView(Node root)
	{
	    Node temp = root.left;
	    
	    inorder(temp);
	    
	    System.out.print(root.data + " ");
	    temp = root.right;
	    while(temp != null) {
	        System.out.print(temp.data + " ");
	        temp = temp.right;
	    }
	}

	void inorder(Node root) {
	    if (root == null) {
	        return;
	    }
	    inorder(root.left);
	    System.out.print(root.data + " ");
	}
	
}
