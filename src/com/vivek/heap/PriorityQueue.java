package com.vivek.heap;

public class PriorityQueue {

    private final Heap<Integer, Void> heap;

    public PriorityQueue() {
        heap = new Heap<>();
    }

    public int min() {
        return heap.min().key;
    }

    public int removeMin() {
        return heap.removeMin().key;
    }

    public void add(int data) {
        heap.insert(data, null);
    }

    public int size() {
        return heap.size();
    }

}
