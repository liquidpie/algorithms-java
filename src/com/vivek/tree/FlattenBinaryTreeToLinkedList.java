package com.vivek.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 114. Flatten Binary Tree to Linked List
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 *     The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 *     The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Solution::
 * ----------
 * 2. Stack-Based Preorder Flattening
 * Intuition
 *
 * Simulate preorder traversal using a stack (root → left → right), and rearrange pointers on the fly. Classic iterative DFS style.
 * Approach
 *
 * Push root to the stack. While it’s not empty, pop the top node, push right then left children (if they exist), and update node.right = stack.peek(). Set node.left = null.
 * Complexity
 *
 *     Time complexity: O(n) — Each node is pushed and popped once.
 *     Space complexity: O(n) — Stack holds up to all nodes in worst case (skewed tree).
 *
 * Code
 *
 * class Solution {
 *     public void flatten(TreeNode root) {
 *         if (root == null) return;
 *
 *         Deque<TreeNode> stack = new ArrayDeque<>();
 *         stack.push(root);
 *
 *         while (!stack.isEmpty()) {
 *             TreeNode node = stack.pop();
 *
 *             if (node.right != null) stack.push(node.right);
 *             if (node.left != null) stack.push(node.left);
 *
 *             if (!stack.isEmpty()) node.right = stack.peek();
 *             node.left = null;
 *         }
 *     }
 * }
 *
 * 🔗 3. Morris Traversal Inspired In-Place Flattening
 * Intuition
 *
 * Inspired by Morris traversal, reuse tree's right pointers without extra space. Thread the tree using the rightmost node of the left subtree.
 * Approach
 *
 * Iterate with a cur pointer. If cur.left exists, find the rightmost node in that left subtree and link its right to cur.right. Then move cur.left to cur.right, and cur.left = null. Move to cur.right.
 * Complexity
 *
 *     Time complexity: O(n) — Each node and edge visited a constant number of times.
 *     Space complexity: O(1) — In-place, no recursion or stack.
 *
 * Code
 *
 * class Solution {
 *     public void flatten(TreeNode root) {
 *         TreeNode cur = root;
 *
 *         while (cur != null) {
 *             if (cur.left != null) {
 *                 TreeNode prev = cur.left;
 *                 while (prev.right != null) prev = prev.right;
 *                 prev.right = cur.right;
 *                 cur.right = cur.left;
 *             }
 *             cur.left = null;
 *             cur = cur.right;
 *         }
 *     }
 * }
 *
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/solutions/6907179/flatten-binary-tree-to-linked-list-3-elegant-solutions/
 *
 * Reference:
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
 */
public class FlattenBinaryTreeToLinkedList {

    private static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right = new Node(5);
        root.right.right = new Node(6);

        flatten(root);

        Node node = root;
        while (node != null) {
            System.out.print(node + " -> ");
            node = node.right;
        }
    }

    public static void flatten(Node root) {
        append(root);

        Node prev = queue.poll();
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            prev.right = current;
            prev.left = null;
            prev = current;
        }
    }

    private static void append(Node node) {
        if (node == null)
            return;

        queue.add(node);
        append(node.left);
        append(node.right);
    }

    public static void flattenIterative(Node root) {
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);

            if (!stack.isEmpty())
                node.right = stack.peek();

            node.left = null;
        }

    }
}
