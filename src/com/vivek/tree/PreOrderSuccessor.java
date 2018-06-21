package com.vivek.tree;

import java.util.Stack;

/**
 * Created by VJaiswal on 21/06/18.
 */
public class PreOrderSuccessor {

    /**
     * Without using Parent Reference in Node
     * It is iterative and uses stack
     */

    Node withoutParentRef(Node node) {
        if (node == null)
            return null;

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();

            if (curr.right != null)
                stack.push(curr.right);

            if (curr.left != null)
                stack.push(curr.left);

            if (curr == node && !stack.isEmpty())
                return stack.pop();
        }
        return null;
    }


    /**
     * Using Parent Reference in Node
     */

    NodeX usingParentRef(NodeX node) {
        if (node == null)
            return null;
        if (node.left != null)
            return (NodeX) node.left;

        if (node.right != null)
            return (NodeX) node.right;

        return _findNextFromParent(node.parent);
    }

    private NodeX _findNextFromParent(NodeX node) {
        if (node == null || node.parent == null)
            return null;

        if (node.parent.left == node && node.parent.right != null)
            return (NodeX) node.parent.right;

        return _findNextFromParent(node.parent);
    }

    static class NodeX extends Node {
        NodeX parent;

        public NodeX(int data, NodeX parent) {
            super(data);
            this.parent = parent;
        }
    }

}
