package com.vivek.treap;

public class Traversal {

    static void inOrder(Node root) {
        if (root == null)
            return;

        inOrder(root.left);

        System.out.println(root);

        inOrder(root.right);

    }

}
