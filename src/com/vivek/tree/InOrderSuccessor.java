package com.vivek.tree;

/**
 * To find the ‘next’ node (e.g., in-order successor) of a given node
 * in a binary search tree where each node has a link to its parent
 */
public class InOrderSuccessor {


    /**
     So, if we want to find X.nextNode(), we do the following:
     1. If X has a right child, then the successor must be on the right side of X (because of the order in which we visit nodes).
     Specifically, the left-most child must be the first node visited in that subtree.
     2. Else, we go to X’s parent (call it P).
     2.a. If X was a left child (P.left = X), then P is the successor of X
     2.b. If X was a right child (P.right = X), then we have fully visited P, so we call nextNode(P).
     */
    NodeX nextNode(NodeX node) {
        if (node == null)
            return null;

        if (node.right != null) {
            return leftMostNode((NodeX) node.right);
        } else {
            NodeX parent = node.parent;
            if (node == parent.left)
                return parent;
            else {
                return nextNode(parent);
            }
        }
    }

    NodeX leftMostNode(NodeX node) {
        if (node == null)
            return null;
        while (node.left != null) {
            node = (NodeX) node.left;
        }
        return node;
    }


    static class NodeX extends Node {
        NodeX parent;

        public NodeX(int data, NodeX parent) {
            super(data);
            this.parent = parent;
        }
    }

}
