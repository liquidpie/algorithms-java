package com.vivek.tree.dfs;

import com.vivek.tree.Node;

import java.util.*;

/**
 * 1110. Delete Nodes And Return Forest
 *
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 * Example 2:
 *
 * Input: root = [1,2,4,null,3], to_delete = [3]
 * Output: [[1,2,4]]
 *
 * Reference:
 * https://leetcode.com/problems/delete-nodes-and-return-forest/description/
 */
public class DeleteNodeReturnForest {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        int[] toDelete = {3, 5};

        DeleteNodeReturnForest helper = new DeleteNodeReturnForest();
        List<Node> forest = helper.delNodes(root, toDelete);

        for (Node node : forest) {
            System.out.println(node.data);
        }
    }

    public List<Node> delNodes(Node root, int[] toDelete) {
        List<Node> forest = new ArrayList<>();
        Set<Integer> deleteSet = new HashSet<>();
        for (int key : toDelete)
            deleteSet.add(key);

        root = dfs(root, deleteSet, forest);

        // If the root is not deleted, add it to the forest
        if (root != null)
            forest.add(root);

        return forest;
    }

    private Node dfs(Node node, Set<Integer> deleteSet, List<Node> forest) {
        if (node == null)
            return null;

        node.left = dfs(node.left, deleteSet, forest);
        node.right = dfs(node.right, deleteSet, forest);

        if (deleteSet.contains(node.data)) {
            if (node.left != null)
                forest.add(node.left);
            if (node.right != null)
                forest.add(node.right);

            // Return null to its parent to delete the current node
            return null;
        }

        return node;
    }

}
