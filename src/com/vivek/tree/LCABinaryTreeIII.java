package com.vivek.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * 1650. Lowest Common Ancestor of a Binary Tree III
 *
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the
 * lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * Solution:
 * 1. Store the path from p to the root.
 * 2. Traverse the path from q to the root, the first common point of the two paths is the LCA.
 *
 * The key detail to solve this that separates this problem from other binary tree problems is the "parent" attribute.
 * Once you see that piece of information it becomes clearer.
 *
 * If you don't pay attention to that attribute or read it carefully, you are really scratching your head in regards to
 * root traversal using DFS vs. BFS.
 *
 * Other aspects of this problem:
 *
 * root of the tree is not passed into the function. You are only passed 'p' and 'q'.
 * Return the ancestor node itself and not the "val" of the node.
 *
 * This is similar to Intersection of 2 Linked Lists. We can use any of Hashset / Height difference / Cycle approach.
 *
 * Reference:
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/description/
 */
public class LCABinaryTreeIII {

    public static void main(String[] args) {
        Node root = new Node(3, null);
        root.left = new Node(5, root);
        root.right = new Node(1, root);
        root.left.left = new Node(6, root.left);
        root.left.right = new Node(2, root.left);
        root.left.right.left = new Node(7, root.left.right);
        root.left.right.right = new Node(4, root.left.right);
        root.right.left = new Node(0, root.right);
        root.right.right = new Node(8, root.right);

        System.out.println(lowestCommonAncestor(root.left, root.left.right.right));
    }

    static Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> path = new HashSet<>();

        Node node = p;
        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        node = q;
        while (node != null) {
            if (path.contains(node))
                return node;
            node = node.parent;
        }

        return null;
    }

    /**
     * Based on the concept of two runners on the circle track
     *
     * Let us suppose the two nodes are at different distances from the root of the tree.
     * If they are not, that is also great because we will arrive at the answer in one go, as you will see.
     * In the first go, both the a and b pointers try to reach the root from p and q respectively.
     * The pointer that reaches the root first, then swaps its origin, now starting from q if it started from p earlier and vice versa.
     * The pointer that reaches earlier, will now start from the other origin and hence, have a longer distance to cover in the next run to the origin.
     * The pointer that reaches later, will then start from the other origin and hence, have a shorter distance to cover in the next run to the origin.
     * The reverse situation for the two pointers now equalizes their approach because the later reaching pointer, when it will start the second run, will be at the same height or distance from the origin as the other pointer.
     * Given the above, the second run results in the two pointers meeting at the lowest common ancestor.
     *
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/solutions/932914/java-in-6-lines/
     */
    static Node lowestCommonAncestorApproach2(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a == null? q : a.parent;
            b = b == null? p : b.parent;
        }
        return a;
    }

    private static class Node {
        private int val;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int val, Node parent) {
            this.val = val;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }

}
