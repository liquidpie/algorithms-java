package com.vivek.tree;

/**
 * Construct Tree from given Inorder and Preorder traversals
 *
 * Let us consider the below traversals:
 * Inorder sequence: D B E A F C
 * Preorder sequence: A B D E C F
 *
 * In a Preorder sequence, the leftmost element is the root of the tree. So we know ‘A’ is the root for given sequences.
 * By searching ‘A’ in the Inorder sequence, we can find out all elements on the left side of ‘A’ is in the left subtree,
 * and elements on right in the right subtree. So we know the below structure now.
 *
 *                  A
 *                /   \
 *              /       \
 *            D B E     F C
 *
 * We recursively follow the above steps and get the following tree.
 *
 *                    A
 *                  /   \
 *                /       \
 *               B         C
 *              / \        /
 *            /     \    /
 *           D       E  F
 *
 * Algorithm: buildTree()
 * 1) Pick an element from Preorder. Increment a Preorder Index Variable (pIndex in below code) to pick the next element in the next recursive call.
 * 2) Create a new tree node tNode with the data as the picked element.
 * 3) Find the picked element’s index in Inorder. Let the index be iIndex.
 * 4) Call buildTree for elements before iIndex and make the built tree as a left subtree of tNode.
 * 5) Call buildTree for elements after iIndex and make the built tree as a right subtree of tNode.
 * 6) return node.
 *
 * Reference: https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal
 */
public class BuildTreeFromInorderPreorder {

    static int pIndex = 0;

    static Node buildTree(char[] preorder, char[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        Node node = new Node(preorder[pIndex++]);

        if (inStart == inEnd)
            return node;

        int iIndex = search(inorder, node.data, inStart, inEnd);

        node.left = buildTree(preorder, inorder, inStart, iIndex - 1);
        node.right = buildTree(preorder, inorder, iIndex + 1, inEnd);

        return node;
    }

    // we can optimize it by using a HashMap with value as character index
    private static int search(char[] inorder, char k, int start, int end) {
        int i;
        for (i = start; i <= end; i++) {
            if (inorder[i] == k)
                break;
        }
        return i;
    }

    private static class Node {
        private char data;
        private Node left, right;

        Node(char data) {
            this.data = data;
        }

        public Node(char data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        static void postOrder(Node root) {
            if (root == null) {
                return;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Without static index
    private Node buildTree(char[] preorder, char[] inorder, int pre, int l, int r) {
        if (r < l) return null;
        // in and pre are the indexes of root on inorder and preorder.
        int in;
        char val = preorder[pre];
        for(in = l; in <= r; in++)
            if (inorder[in] == val) break; // seek position of in

        // left subtree root is next preorder head, take left chunk of inorder
        // right subtree root in preorder is after left subtree size, take right chunk of inorder
        return new Node(val,
                buildTree(preorder, inorder, pre + 1, l, in - 1),
                buildTree(preorder, inorder, pre + 1 + in - l, in + 1, r)
        );
    }

    public static void main(String[] args) {
        char[] preorder = { 'A', 'B', 'D', 'E', 'C', 'F' };
        char[] inorder  = { 'D', 'B', 'E', 'A', 'F', 'C' };

        Node root = buildTree(preorder, inorder, 0, inorder.length - 1);
        Node.postOrder(root);
    }

}
