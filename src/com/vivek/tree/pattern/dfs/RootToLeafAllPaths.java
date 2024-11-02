package com.vivek.tree.pattern.dfs;

import com.vivek.tree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Solution #
 * We can follow a similar approach to @see com.vivek.tree.pattern.dfs.RootToLeafPathSumAllPaths#findPaths().
 * We just need to remove the “check for the path sum”.
 *
 * Example:
 *          1
 *     7        9
 *   4   5    2   7
 *
 * Reference:
 * Grokking the Coding Interview
 * Pattern: DFS
 */
public class RootToLeafAllPaths {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(7);
        root.right = new Node(9);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(2);
        root.right.right = new Node(7);

        System.out.println(findPaths(root));
    }

    static List<List<Integer>> findPaths(Node root) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathsRecursive(root, paths, currentPath);
        return paths;
    }

    private static void findPathsRecursive(Node node, List<List<Integer>> paths, List<Integer> currentPath) {
        if (node == null)
            return;

        // add the current node to the path
        currentPath.add(node.data);

        // if the current node is a leaf and its value is equal to sum, save the current path
        if (node.left == null && node.right == null) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            findPathsRecursive(node.left, paths, currentPath);
            findPathsRecursive(node.right, paths, currentPath);
        }

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
    }
}
