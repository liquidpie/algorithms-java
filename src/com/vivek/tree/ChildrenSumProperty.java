package com.vivek.tree;

/**
 * Check for Children Sum Property in a Binary Tree
 *
 * Given a binary tree, write a function that returns true if the tree satisfies below property:
 *  - For every node, data value must be equal to sum of data values in left and right children.
 *  - Consider data value as 0 for NULL children.
 */
public class ChildrenSumProperty {

    boolean isChildSumTree(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return true;

        int sum = 0;
        if (node.left != null) {
            sum += node.left.data;
        }
        if (node.right != null) {
            sum += node.right.data;
        }

        if (node.data != sum) {
            return false;
        }

        return isChildSumTree(node.left) && isChildSumTree(node.right);
    }

    public static void main(String[] args) {
        ChildrenSumProperty solution = new ChildrenSumProperty();
        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.left = new Node(2);

        System.out.println(solution.isChildSumTree(root));

    }

}
