package com.vivek.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Traversal {

	/* Pre Order */
	public void preOrder(Node root) {
	    if (root == null) {
	        return;
	    }
	    System.out.print(root.data + " ");
	    preOrder(root.left);
	    preOrder(root.right);
	}
	
	/* Post Order */
	public void postOrder(Node root) {
	    if (root == null) {
	        return;
	    }
	    postOrder(root.left);
	    postOrder(root.right);
	    System.out.print(root.data + " ");
	}
	
	/* In Order */
	public void inOrder(Node root) {
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
    public void printLevelOrder(Node root)
    {
        Queue<Node> queue = new LinkedList<>();
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

    /**
     * Given a binary search tree, design an algorithm which creates a linked list of all the
     nodes at each depth (eg, if you have a tree with depth D, you’ll have D linked lists).
     */
    void listNodesAtEachDepth(Node root) {
        if (root == null)
            return;

        List<LinkedList<Node>> result = new ArrayList<>();
        int level = 0;

        LinkedList<Node> list = new LinkedList<>();
        list.add(root);
        result.add(level, list);

        while (true) {
            if (!result.get(level).isEmpty()) {
                list = new LinkedList<>();
                for (int i = 0; i < result.get(level).size(); i++) {
                    Node node = result.get(level).get(i);
                    if (node.left != null)
                        list.add(node.left);
                    if (node.right != null)
                        list.add(node.right);
                }
                level++;
                result.add(level, list);
            }
            else {
                break;
            }
        }
    }

    /**
     * Euler tour of Binary Tree
     *
     * Given a binary tree where each node can have at most two child nodes, the task is to find the Euler tour of the binary tree.
     * Euler tour is represented by a pointer to the topmost node in the tree. If the tree is empty, then value of root is NULL.
     *
     * Euler tour is defined as a way of traversing tree such that each vertex is added to the tour when we visit it
     * (either moving down from parent vertex or returning from child vertex). We start from root and reach back to root after visiting all vertices.
     *
     * It requires exactly 2*N-1 vertices to store Euler tour.
     */
    static void eulerTour(Node root, List<Integer> eulerTour) {
        if (root == null)
            return;

        eulerTour.add(root.data);

        if (root.left != null) {
            eulerTour(root.left, eulerTour);
            eulerTour.add(root.data);
        }
        if (root.right != null) {
            eulerTour(root.right, eulerTour);
            eulerTour.add(root.data);
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

        List<Integer> tour = new ArrayList<>();
        eulerTour(root, tour);
        System.out.println(tour);

    }

}
