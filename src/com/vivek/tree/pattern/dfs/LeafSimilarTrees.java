package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 872. Leaf-Similar Trees
 *
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 * Example 1:
 *
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 *
 * Example 2:
 *
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 *
 * Solution:
 * https://leetcode.com/problems/leaf-similar-trees/solutions/152329/c-java-python-o-h-space/?envType=study-plan-v2&envId=leetcode-75
 *
 * Reference:
 * https://leetcode.com/problems/leaf-similar-trees
 */
public class LeafSimilarTrees {

    public boolean leafSimilarApproach1(Node root1, Node root2) {
        List<Integer> leafs1 = new ArrayList<>();
        List<Integer> leafs2 = new ArrayList<>();

        traversal(root1, leafs1);
        traversal(root2, leafs2);

        if (leafs1.size() != leafs2.size())
            return false;

        for (int i = 0; i < leafs1.size(); i++) {
            if (!leafs1.get(i).equals(leafs2.get(i)))
                return false;
        }
        return true;
    }

    private void traversal(Node node, List<Integer> leafs) {
        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            leafs.add(node.data);
            return;
        }

        traversal(node.left, leafs);
        traversal(node.right, leafs);
    }

    public boolean leafSimilarApproach2(Node root1, Node root2) {
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root1);
        s2.push(root2);

        while (!s1.isEmpty() && !s2.isEmpty()) {
            if (dfs(s1) != dfs(s2))
                return false;
        }
        return s1.isEmpty() && s2.isEmpty();
    }

    private int dfs(Stack<Node> stack) {
        while (true) {
            Node node = stack.pop();
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);

            if (node.left == null && node.right == null)
                return node.data;
        }
    }

}
