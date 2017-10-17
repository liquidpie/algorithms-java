package com.vivek.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VJaiswal on 15/10/17.
 *
 * Searching into a balanced tree is significantly faster than searching into a non-balanced tree!
 * we will need to re-balance the tree on each insert and delete
 *
 * Construct balanced BST in O(n) time with minimum possible height. Below are steps:
 *
 * 1. Traverse given BST in inorder and store result in an array. This step takes O(n) time.
 * Note that this array would be sorted as inorder traversal of BST always produces sorted sequence.
 *
 * 2. Build a balanced BST from the above created sorted array using the recursive approach as discussed below:
 *
 * 2.1. Get the Middle of the array and make it root.
 * 2.2 Recursively do same for left half and right half.
 *      2.2.1. Get the middle of left half and make it left child of the root created in step 2.1.
 *      2.2.2. Get the middle of right half and make it right child of the root created in step 2.1.
 *
 * This step also takes O(n) time as we traverse every element exactly once and processing an element takes O(1) time.
 *
 */
public class BalancedBST {

    Node buildTree(Node root) {
        List<Node> nodes = new ArrayList<>();
        storeNodesInArray(root, nodes);

        int n = nodes.size();

        return buildTree(nodes, 0, n - 1);
    }

    private Node buildTree(List<Node> nodes, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        Node node = nodes.get(mid);

        // construct left and right subtrees
        node.left = buildTree(nodes, start, mid - 1);
        node.right = buildTree(nodes, mid + 1, end);

        return node;
    }


    // traverse BST in inorder and store in array
    private void storeNodesInArray(Node root, List<Node> nodes) {
        if (root == null)
            return;

        storeNodesInArray(root.left, nodes);
        nodes.add(root);
        storeNodesInArray(root.right, nodes);
    }

}
