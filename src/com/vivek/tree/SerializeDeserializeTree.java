package com.vivek.tree;

import java.util.List;

/**
 Serialization is to store tree in a file so that it can be later restored. The structure of tree must be maintained.
 Deserialization is reading tree back from file.

 A simple solution is to store both Inorder and Preorder traversals. This solution requires requires space twice the size of Binary Tree.
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

    static final int MARKER = -1;

    static void serialize(Node root, List<Integer> numberStream) {

        if (root == null) {
            numberStream.add(MARKER);
            return;
        }

        numberStream.add(root.data);

        serialize(root.left, numberStream);
        serialize(root.right, numberStream);
    }

    static void deserialize(List<Integer> numberStream, int idx, Node root) {

        if (idx >= numberStream.size() || numberStream.get(idx) == MARKER)
            return;

        root = new Node(numberStream.get(idx));

        deserialize(numberStream, idx + 1, root.left);
        deserialize(numberStream, idx + 1, root.right);
    }


}
