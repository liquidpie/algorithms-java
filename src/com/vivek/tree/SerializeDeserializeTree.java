package com.vivek.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 Serialization is to store tree in a file so that it can be later restored. The structure of tree must be maintained.
 Deserialization is reading tree back from file.

 -> If given Tree is a Binary Search Tree?
    If the given Binary Tree is Binary Search Tree, we can store it by either storing preorder or postorder traversal.
    In case of Binary Search Trees, only preorder or postorder traversal is sufficient to store structure information.

 -> If given Binary Tree is Complete Tree?
    A Binary Tree is complete if all levels are completely filled except possibly the last level and all nodes of last
    level are as left as possible (Binary Heaps are complete Binary Tree). For a complete Binary Tree, level order
    traversal is sufficient to store the tree. We know that the first node is root, next two nodes are nodes of next level,
    next four nodes are nodes of 2nd level and so on.

 -> If given Binary Tree is Full Tree?
    A full Binary is a Binary Tree where every node has either 0 or 2 children. It is easy to serialize such trees as
    every internal node has 2 children. We can simply store preorder traversal and store a bit with every node to
    indicate whether the node is an internal node or a leaf node.

 A simple solution is to store both Inorder and Preorder traversals. This solution requires space twice the size of Binary Tree.
 We can save space by storing Preorder traversal and a marker for NULL pointers.

 Let the marker for NULL pointers be '-1'
 Input:
        12
       /
     13
 Output: 12 13 -1 -1 -1

 Input:
       20
      /  \
     8    22
 Output: 20 8 -1 -1 22 -1 -1

 https://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
 */
public class SerializeDeserializeTree {

    static String MARKER = "$";

    static String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node != null) {
                sb.append(node.data).append(" ");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                sb.append(MARKER).append(" ");
            }
        }

        return sb.toString();
    }

    static Node deserialize(String data) {
        String[] parts = data.split(" ");
        int index = 0;
        Node root = getNode(parts[index++]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node != null) {
                node.left = getNode(parts[index++]);
                node.right = getNode(parts[index++]);
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        return root;
    }

    private static Node getNode(String data) {
        return data.equals(MARKER) ? null : new Node(Integer.parseInt(data));
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        String str = serialize(root);
        System.out.println(str);
        Node res = deserialize(str);
        System.out.println(res.data == root.data);
    }

}
