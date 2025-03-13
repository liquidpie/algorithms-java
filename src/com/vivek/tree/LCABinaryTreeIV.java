package com.vivek.tree;

/**
 * 1676. Lowest Common Ancestor of a Binary Tree IV
 *
 * Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes.
 * All the nodes will exist in the tree, and all values of the tree's nodes are unique.
 *
 * Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, ..., pn in a binary tree T
 * is the lowest node that has every pi as a descendant (where we allow a node to be a descendant of itself) for every valid i".
 * A descendant of a node x is a node y that is on the path from node x to some leaf node.
 *
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
 * Output: 2
 * Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
 * Output: 1
 * Explanation: The lowest common ancestor of a single node is the node itself.
 *
 * Example 3:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
 * Output: 5
 * Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.
 *
 * Solution:
 * This is exactly same as base LCA problem
 *
 * Reference:
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
 */
public class LCABinaryTreeIV {

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);
        root.right.left = new Node(0);
        root.right.right = new Node(8);

        Node[] nodes = new Node[2];
        nodes[0] = root.left.right.left;
        nodes[1] = root.left.right.right;

        System.out.println(lowestCommonAncestor(root, nodes));
    }

    static Node lowestCommonAncestor(Node root, Node[] nodes) {
        if (nodes == null || nodes.length == 0)
            return null;
        if (nodes.length == 1)
            return nodes[0];

        return lca(root, nodes);
    }

    private static Node lca(Node root, Node[] nodes) {
        if (root == null)
            return null;

        for (Node node : nodes) {
            if (root == node)
                return root;
        }

        Node left_lca = lca(root.left, nodes);
        Node right_lca = lca(root.right, nodes);

        if (left_lca != null && right_lca != null)
            return root;

        return (left_lca != null) ? left_lca : right_lca;
    }

}
