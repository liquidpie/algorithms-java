package com.vivek.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Heap<K extends Comparable<K>, V> {

    enum Type {
        MIN_HEAP, MAX_HEAP
    }

    private Type type = Type.MIN_HEAP;

    private final List<Entry<K, V>> heap = new ArrayList<>();

    public Heap() { }

    /**
     * Bottom-Up Heap Construction
     *
     * If we start with an initially empty heap, n successive calls to the insert operation will run in O(nlogn) time in the worst case.
     * However, if all n key-value pairs to be stored in the heap are given in advance, bottom-up construction method can be used that runs in O(n) time.
     *
     * With our array-based representation of a heap, if we initially store all n entries in arbitrary order within the array,
     * we can implement the bottom-up heap construction process with a single loop that makes a call to downheap from each position of the tree,
     * as long as those calls are ordered starting with the deepest level and ending with the root of the tree.
     * In fact, that loop can start with the deepest internal position, since there is no effect when down-heap is called at an external position.
     */
    public Heap(Map<K, V> elements) {
        for (Map.Entry<K, V> entry : elements.entrySet()) {
            heap.add(new Entry<>(entry.getKey(), entry.getValue()));
        }
        heapify();
    }

    public Heap(Type type) {
        this.type = type;
    }

    /**
     * Works as max() when type=MAX_HEAP
     */
    public Entry<K, V> min() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public void insert(K key, V value) {
        heap.add(new Entry<>(key, value));
        upheap(heap.size() - 1);
    }

    /**
     * Works as removeMax() when type=MAX_HEAP
     */
    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K, V> min = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return min;
    }

    private int parent(int j) {
        return (j - 1) / 2;
    }

    private int left(int j) {
        return 2 * j + 1;
    }

    private int right(int j) {
        return 2 * j + 2;
    }

    private boolean hasLeft(int j) {
        return left(j) < heap.size();
    }

    private boolean hasRight(int j) {
        return right(j) < heap.size();
    }

    private void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void upheap(int j) {
        while (j > 0) {
            int p = parent(j);

            if (heapOrderSatisfied(j, p)) {
                break;
            }
            swap(j, p);
            j = p;
        }
    }

    private void downheap(int j) {
        while (hasLeft(j)) {
            int leftIdx = left(j);
            int smallIdx = leftIdx;
            if (hasRight(j)) {
                int rightIdx = right(j);
                K k1 = heap.get(leftIdx).key;
                K k2 = heap.get(rightIdx).key;
                if (type == Type.MAX_HEAP) {
                    if (k2.compareTo(k1) > 0) {
                        smallIdx = rightIdx;
                    }
                } else {
                    if (k2.compareTo(k1) < 0) {
                        smallIdx = rightIdx;
                    }
                }
            }
            if (heapOrderSatisfied(smallIdx, j)) {
                break;
            }
            swap(smallIdx, j);
            j = smallIdx;
        }
    }

    private boolean heapOrderSatisfied(int child, int parent) {
        K k1 = heap.get(child).key;
        K k2 = heap.get(parent).key;

        if (type == Type.MAX_HEAP) {
            return k1.compareTo(k2) <= 0;
        } else {
            return k1.compareTo(k2) >= 0;
        }
    }

    /**
     * Performs bottom-up heap construction in linear time
     */
    private void heapify() {
        int startIdx = parent(size() - 1); // Start at the parent of last entry
        for (int j = startIdx; j >= 0; j--) { // loop until processing the root
            downheap(j);
        }
    }

    protected static class Entry<K extends Comparable<K>, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + ", " + value + "}";
        }
    }

    public static void main(String[] args) {
        Heap<Integer, Integer> heap = new Heap<>();
        heap.insert(13, 13);
        heap.insert(11, 11);
        heap.insert(7, 7);
        heap.insert(20, 20);
        heap.insert(6, 6);
        heap.insert(9, 9);
        heap.insert(25, 25);
        heap.insert(15, 15);
        heap.insert(4, 4);
        heap.insert(16, 16);
        heap.insert(5, 5);
        heap.insert(12, 12);
        heap.insert(14, 14);

        System.out.println(heap.min());
        heap.removeMin();
        System.out.println(heap.min());
        heap.removeMin();
        System.out.println(heap.min());
    }

}
