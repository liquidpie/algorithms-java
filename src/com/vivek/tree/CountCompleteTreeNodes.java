package com.vivek.tree;

/**
 * 222. Count Complete Tree Nodes
 *
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 * Explanation
 *
 * The height of a tree can be found by just going left. Let a single node tree have height 0. Find the height h of the whole tree. If the whole tree is empty, i.e., has height -1, there are 0 nodes.
 *
 * Otherwise check whether the height of the right subtree is just one less than that of the whole tree, meaning left and right subtree have the same height.
 *
 *     If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1. So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
 *     If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2. So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.
 *
 * Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2).
 *
 * Here's an iterative version as well, with the benefit that I don't recompute h in every step.
 *
 * https://leetcode.com/problems/count-complete-tree-nodes/solutions/61958/concise-java-solutions-o-log-n-2/?envType=study-plan-v2&envId=top-interview-150
 *
 * Reference:
 * https://leetcode.com/problems/count-complete-tree-nodes
 */
public class CountCompleteTreeNodes {

    public int countNodes(Node root) {
        int nodes = 0, h = height(root);
        while (root != null) {
            if (height(root.right) == h - 1) {
                nodes += 1 << h;
                root = root.right;
            } else {
                nodes += 1 << h-1;
                root = root.left;
            }
            h--;
        }
        return nodes;
    }

    private int height(Node root) {
        return root == null ? -1 : 1 + height(root.left);
    }

}
