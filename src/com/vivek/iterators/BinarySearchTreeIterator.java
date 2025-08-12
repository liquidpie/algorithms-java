package com.vivek.iterators;

import com.vivek.tree.Node;

import java.util.Iterator;
import java.util.Stack;

/**
 * 173. Binary Search Tree Iterator
 *
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 *     BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 *     The pointer should be initialized to a non-existent number smaller than any element in the BST.
 *     boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 *     int next() Moves the pointer to the right, then returns the number at the pointer.
 *
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 *
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 *
 * Example 1:
 *
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 *
 * Reference: https://leetcode.com/problems/binary-search-tree-iterator
 *
 */
public class BinarySearchTreeIterator implements Iterator<Integer> {

    private final Stack<Node> stack;
    private Node current;

    public BinarySearchTreeIterator(Node root) {
        this.current = root;
        this.stack = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return current != null || !stack.isEmpty();
    }

    @Override
    public Integer next() {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        current = stack.pop();
        int value = current.data;
        current = current.right;
        return value;
    }

    public static void main(String[] args) {
        Node root = new Node(7);
        root.left = new Node(3);
        root.right = new Node(15);
        root.right.left = new Node(9);
        root.right.right = new Node(20);
        BinarySearchTreeIterator bstIterator = new BinarySearchTreeIterator(root);

        System.out.println(bstIterator.next());    // return 3
        System.out.println(bstIterator.next());    // return 7
        System.out.println(bstIterator.hasNext()); // return True
        System.out.println(bstIterator.next());    // return 9
        System.out.println(bstIterator.hasNext()); // return True
        System.out.println(bstIterator.next());    // return 15
        System.out.println(bstIterator.hasNext()); // return True
        System.out.println(bstIterator.next());    // return 20
        System.out.println(bstIterator.hasNext()); // return False
    }

}
