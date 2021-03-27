package com.vivek.tree.nary;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private final int data;
    private final List<Node> children;
    private final int n; // max number of children

    public Node(int data, int n) {
        this.data = data;
        this.n = n;
        this.children = new ArrayList<>(n);
    }

    public Node(int data, List<Node> children) {
        this.data = data;
        this.children = children;
        this.n = children.size();
    }

    public void addChildren(Node child) {
        if (children.size() == n) {
            throw new IllegalStateException("No more child can be added");
        }
        children.add(child);
    }

    public int getData() {
        return data;
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getChildrenCount() {
        return children.size();
    }

    public int getMaxChildren() {
        return n;
    }

}
