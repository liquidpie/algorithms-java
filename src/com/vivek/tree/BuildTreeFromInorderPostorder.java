package com.vivek.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given Postorder and Inorder traversals, construct the tree.
 *
 * Examples:
 *
 * Input:
 * in[]   = {2, 1, 3}
 * post[] = {2, 3, 1}
 *
 * Output: Root of below tree
 *       1
 *     /   \
 *    2     3
 *
 *
 * Input:
 * in[]   = {4, 8, 2, 5, 1, 6, 3, 7}
 * post[] = {8, 4, 5, 2, 6, 7, 3, 1}
 *
 * Output: Root of below tree
 *           1
 *        /     \
 *      2        3
 *    /    \   /   \
 *   4     5   6    7
 *     \
 *       8
 *
 * Approach:
 *
 * Let us see the process of constructing tree from in[] = {4, 8, 2, 5, 1, 6, 3, 7} and post[] = {8, 4, 5, 2, 6, 7, 3, 1}
 * 1) We first find the last node in post[]. The last node is “1”, we know this value is root as root always appear in the end of postorder traversal.
 * 2) We search “1” in in[] to find left and right subtrees of root. Everything on left of “1” in in[] is in left subtree and everything on right is in right subtree.
 *
 *          1
 *        /    \
 * [4, 8, 2, 5]   [6, 3, 7]
 *
 * 3) We recur the above process for following two.
 * ….b) Recur for in[] = {6, 3, 7} and post[] = {6, 7, 3}
 * …….Make the created tree as right child of root.
 * ….a) Recur for in[] = {4, 8, 2, 5} and post[] = {8, 4, 5, 2}.
 * …….Make the created tree as left child of root.
 *
 *
 * Reference: https://www.geeksforgeeks.org/construct-a-binary-tree-from-postorder-and-inorder
 */
public class BuildTreeFromInorderPostorder {

    static int pIndex;

    static Node buildTree(int[] postOrder, int[] inOrder, int inStart, int inEnd, int postStart, int postEnd) {
        Map<Integer, Integer> inOrderSet = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++)
            inOrderSet.put(inOrder[i], i);

        pIndex = postOrder.length - 1;
        return buildTree(postOrder, inOrderSet, inStart, inEnd, postStart, postEnd);
    }

    private static Node buildTree(int[] postOrder, Map<Integer, Integer> inOrder, int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd)
            return null;

        Node node = new Node(postOrder[postEnd]);

        if (inStart == inEnd)
            return node;

        int iIndex = inOrder.get(node.data);

        node.left = buildTree(postOrder, inOrder, inStart, iIndex - 1, postStart, postStart - inStart + iIndex - 1);
        node.right = buildTree(postOrder, inOrder, iIndex + 1, inEnd, postEnd - inEnd + iIndex, postEnd - 1);

        return node;
    }


    public static void main(String[] args) {
        int[] postorder = { 8, 4, 5, 2, 6, 7, 3, 1 };
        int[] inorder   = { 4, 8, 2, 5, 1, 6, 3, 7 };

        int n = inorder.length;

        Node root = buildTree(postorder, inorder, 0, n - 1, 0, n - 1);
        Traversal traversal = new Traversal();
        traversal.preOrder(root);
    }

}
