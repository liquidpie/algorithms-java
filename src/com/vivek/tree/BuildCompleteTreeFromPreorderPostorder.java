package com.vivek.tree;


/**
 * Construct Full Binary Tree from given preorder and postorder traversals
 *
 * Given two arrays that represent preorder and postorder traversals of a full binary tree, construct the binary tree.
 * A Full Binary Tree is a binary tree where every node has either 0 or 2 children
 * Following are examples of Full Trees.
 *
 *         1
 *       /   \
 *     2       3
 *   /  \     /  \
 *  4    5   6    7
 *
 *
 *        1
 *      /   \
 *    2      3
 *         /   \
 *        4     5
 *            /   \
 *           6    7
 *
 *
 *           1
 *         /   \
 *       2       3
 *     /  \     /  \
 *    4    5   6    7
 *  /  \
 * 8    9
 *
 *
 * It is not possible to construct a general Binary Tree from preorder and postorder traversals (See this). But if know that the Binary Tree is Full, we can construct the tree without ambiguity. Let us understand this with the help of following example.
 * Let us consider the two given arrays as pre[] = {1, 2, 4, 8, 9, 5, 3, 6, 7} and post[] = {8, 9, 4, 5, 2, 6, 7, 3, 1};
 * In pre[], the leftmost element is root of tree. Since the tree is full and array size is more than 1. The value next to 1 in pre[], must be left child of root. So we know 1 is root and 2 is left child. How to find the all nodes in left subtree? We know 2 is root of all nodes in left subtree. All nodes before 2 in post[] must be in left subtree. Now we know 1 is root, elements {8, 9, 4, 5, 2} are in left subtree, and the elements {6, 7, 3} are in right subtree.
 *
 *                   1
 *                 /   \
 *                /      \
 *      {8, 9, 4, 5, 2}     {6, 7, 3}
 *
 *
 * We recursively follow the above approach and get the following tree.
 *
 *           1
 *         /   \
 *       2       3
 *     /  \     /  \
 *    4    5   6    7
 *   / \
 *  8   9
 *
 * Reference: https://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals
 */
public class BuildCompleteTreeFromPreorderPostorder {

    static int pIndex = 0;

    static Node buildTree(int[] preorder, int[] postorder, int postStart, int postEnd, int n) {
        if (postStart > postEnd || pIndex >= n)
            return null;

        Node node = new Node(preorder[pIndex++]);

        if (postStart == postEnd || pIndex >= n)
            return node;

        // Search the next element of pre[] in post[]
        int index = search(postorder, preorder[pIndex], postStart, postEnd);

        node.left = buildTree(preorder, postorder, postStart, index, n);
        node.right = buildTree(preorder, postorder, index + 1, postEnd - 1, n);

        return node;
    }

    private static int search(int[] postorder, int key, int start, int end) {
        int i;
        for (i = start; i <= end; i++) {
            if (postorder[i] == key)
                break;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] preorder  = { 1, 2, 4, 8, 9, 5, 3, 6, 7 };
        int[] postorder = { 8, 9, 4, 5, 2, 6, 7, 3, 1 };

        int n = preorder.length;

        Node root = buildTree(preorder, postorder, 0, n - 1, n);
        Traversal traversal = new Traversal();
        traversal.inOrder(root);
    }

}
