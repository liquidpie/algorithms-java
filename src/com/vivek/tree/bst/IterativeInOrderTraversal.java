package com.vivek.tree.bst;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * In a BST context, **In-Order Traversal** means visiting nodes in a specific order: left child, parent, and then right child.
 * This order gives us sorted data for a BST. Let’s explore this with a brief step-by-step approach:
 *
 *     Start at the root.
 *     Go as far left as possible.
 *     Visit the node (print/store the value).
 *     Move to the right subtree.
 *     Repeat until all nodes are visited.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 *
 * Where n is the number of nodes in the tree, and h is the height of the tree.
 *
 * Ref: https://heycoach.in/blog/iterative-in-order-traversal-of-a-binary-search-tree/
 */
public class IterativeInOrderTraversal {

    public List<Integer> traversal(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.data);
            current = current.right;
        }

        return result;
    }

}
