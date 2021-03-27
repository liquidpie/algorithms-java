package com.vivek.tree.nary;

public class TreeProperties {

    public int height(Node root) {
        if (root == null) {
            return 0;
        }
        int h = 0;
        for (Node child : root.getChildren()) {
            h = Math.max(h, 1 + height(child));
        }

        return h;
    }

    public void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.getData());
        for (Node child : node.getChildren()) {
            preOrder(child);
        }

    }

    public void postOrder(Node node) {
        if (node == null)
            return;

        for (Node child : node.getChildren()) {
            postOrder(child);
        }

        System.out.println(node.getData());

    }

    public static void main(String[] args) {
        Node root = new Node(0, 2);
        Node node1 = new Node(1, 3);
        Node node2 = new Node(2, 1);
        Node node3 = new Node(3, 4);
        Node node4 = new Node(4, 0);
        Node node5 = new Node(5, 0);
        Node node6 = new Node(6, 0);
        Node node7 = new Node(7, 0);
        Node node8 = new Node(8, 0);
        Node node9 = new Node(9, 0);
        Node node10 = new Node(10, 0);

        root.addChildren(node1);
        root.addChildren(node2);

        node1.addChildren(node3);
        node1.addChildren(node4);
        node1.addChildren(node5);

        node2.addChildren(node6);

        node3.addChildren(node7);
        node3.addChildren(node8);
        node3.addChildren(node9);
        node3.addChildren(node10);

        TreeProperties prop = new TreeProperties();
        System.out.println(prop.height(root));

        prop.preOrder(root);
    }

}
