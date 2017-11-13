package com.vivek.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Traversal {

	/* Pre Order */
	void preOrder(Node root) {
	    if (root == null) {
	        return;
	    }
	    System.out.print(root.data + " ");
	    preOrder(root.left);
	    preOrder(root.right);
	}
	
	/* Post Order */
	void postOrder(Node root) {
	    if (root == null) {
	        return;
	    }
	    postOrder(root.left);
	    postOrder(root.right);
	    System.out.print(root.data + " ");
	}
	
	/* In Order */
	void inOrder(Node root) {
	    if (root == null) {
	        return;
	    }
	    inOrder(root.left);
	    System.out.print(root.data + " ");
	    inOrder(root.right);
	}
	
	
	/* Level Order using Function */
	void LevelOrder(Node root)
    {
       int h = height(root);
       
       for (int i = 1; i <= h; i++) {
           printCurrentLevel(root, i);
       }
      
    }

    void printCurrentLevel(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.data + " ");
        }
        else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }

    int height(Node root) {
        if (root == null) {
            return 0;
        }
        int lheight = height(root.left);
        int rheight = height(root.right);
        
        if (lheight > rheight) {
            return lheight + 1;
        }
        return rheight + 1;
    }

    /* Level order traversal using Queue */
    /**
     * 1) Create an empty queue q
     * 2) temp_node = root //start from root
     * 3) Loop while temp_node is not NULL
		    a) print temp_node->data.
		    b) Enqueue temp_node�s children (first left then right children) to q
		    c) Dequeue a node from q and assign it�s value to temp_node
     */
    void printLevelOrder(Node root) 
    {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) 
        {
 
            /* poll() removes the present head.
            For more information on poll() visit 
            http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */
            Node tempNode = queue.poll();
            System.out.print(tempNode.data + " ");
 
            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
 
            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
}
