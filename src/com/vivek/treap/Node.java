package com.vivek.treap;

import java.util.Random;

public class Node {

    private static final Random random = new Random();

    public int key;
    public int priority;
    public Node left, right;

    public Node(int key) {
        this.key = key;
        this.priority = random.nextInt(100);
    }

    @Override
    public String toString() {
        return "(key=" + key + ", priority=" + priority + ')';
    }
}
