package com.vivek.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 1123. Lowest Common Ancestor of Deepest Leaves
 *
 * Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
 *
 * Recall that:
 *
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
 * The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation: We return the node with value 2, colored in yellow in the diagram.
 * The nodes coloured in blue are the deepest leaf-nodes of the tree.
 * Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.
 *
 * Example 2:
 * Input: root = [1]
 * Output: [1]
 * Explanation: The root is the deepest node in the tree, and it's the lca of itself.
 *
 * Example 3:
 * Input: root = [0,1,3,null,2]
 * Output: [2]
 * Explanation: The deepest leaf node in the tree is 2, the lca of one node is itself.
 *
 * Solution:
 * Approach 1: Paint Deepest Nodes
 * Intuition -
 *
 * We try a straightforward approach that has two phases.
 *
 * The first phase is to identify the nodes of the tree that are deepest. To do this, we have to annotate the depth of each node.
 * We can do this with a depth first search.
 *
 * Afterwards, we will use that annotation to help us find the answer:
 * If the node in question has maximum depth, it is the answer.
 *
 * If both the left and right child of a node have a deepest descendant, then the answer is this parent node.
 * Otherwise, if some child has a deepest descendant, then the answer is that child.
 * Otherwise, the answer for this subtree doesn't exist.
 *
 * Algorithm -
 *
 * In the first phase, we use a depth first search dfs to annotate our nodes.
 * In the second phase, we also use a depth first search answer(node), returning the answer for the subtree at that node,
 * and using the rules above to build our answer from the answers of the children of node.
 * Note that in this approach, the answer function returns answers that have the deepest nodes of the entire tree, not just the subtree being considered.
 *
 * https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/editorial/
 *
 * Reference:
 * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/description/
 */
public class LCADeepestLeaves {

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

        System.out.println(lcaDeepestLeaves(root));
    }

    private static Map<Node, Integer> depths;
    private static int maxDepth;

    static Node lcaDeepestLeaves(Node root) {
        depths = new HashMap<>();
        maxDepth = -1;
        depths.put(null, -1);
        dfs(root, null);

        for (Integer depth : depths.values()) {
            maxDepth = Math.max(maxDepth, depth);
        }

        return findLCA(root);
    }

    private static void dfs(Node node, Node parent) {
        if (node != null) {
            depths.put(node, depths.get(parent) + 1);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    private static Node findLCA(Node node) {
        if (node == null)
            return null;

        if (depths.get(node) == maxDepth)
            return node;

        Node leftLCA = findLCA(node.left);
        Node rightLCA = findLCA(node.right);

        if (leftLCA != null && rightLCA != null)
            return node;

        return leftLCA != null ? leftLCA : rightLCA;
    }

}
