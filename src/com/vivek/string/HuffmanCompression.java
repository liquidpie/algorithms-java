package com.vivek.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by VJaiswal on 14/10/17.
 */
public class HuffmanCompression {

    TreeNode huffmanCoding(String text) {

        Map<Character, Integer> freq = new HashMap<>();     // map of character frequencies

        // populate map
        for (char c : text.toCharArray()) {
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c) + 1);
            } else {
                freq.put(c, 1);
            }
        }

        Queue<TreeNode> queue = new PriorityQueue<>();

        // add all nodes into priority queue with key as frequency
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            queue.add(new TreeNode(entry.getValue(), entry.getKey()));
        }

        while (queue.size() > 1) {
            TreeNode n1 = queue.remove();           // remove two min nodes
            TreeNode n2 = queue.remove();

            TreeNode node = new TreeNode(n1.key + n2.key); // insert a new node with
            node.left = n1;                                     // above nodes as children
            node.right = n2;

            queue.add(node);
        }

        TreeNode root = queue.remove();

        return root;
    }

    static class TreeNode implements Comparable<TreeNode> {
        Integer key;          // frequency of the character
        char value;
        TreeNode left;
        TreeNode right;

        TreeNode(Integer key) {
            this.key = key;
        }

        TreeNode(Integer key, char value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(TreeNode o) {
            return this.key.compareTo(o.key);
        }
    }

    private void printTree(TreeNode node) {
        if (node == null)
            return;

        System.out.println("char = " + node.value + ", freq = " + node.key);
        printTree(node.left);
        printTree(node.right);
    }


    public static void main(String[] args) {
        String text = "a fast runner need never be afraid of the dark";
        HuffmanCompression hc = new HuffmanCompression();
        hc.printTree(hc.huffmanCoding(text));
    }

}
