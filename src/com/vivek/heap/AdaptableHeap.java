package com.vivek.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VJaiswal on 18/03/17.
 *
 * It could also be called as HeapAdaptablePriorityQueue
 */
public class AdaptableHeap {

    private List<Entry> heap = new ArrayList<>();

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }
    private boolean hasLeft(int i) { return left(i) < heap.size(); }
    private boolean hasRight(int i) { return right(i) < heap.size(); }

    private void swap(int i, int j) {
        Entry tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
        heap.get(i).setIndex(i);
        heap.get(j).setIndex(j);
    }

    private void upheap(int i) {
        while (i > 0) { // continue until reaching root
            int p = parent(i);
            if (heap.get(i).compareTo(heap.get(p)) >= 0) break; // com.vivek.heap propery defined
            swap(i, p);
            i = p;
        }
    }

    // restores the com.vivek.heap order property by moving the entry at index i upward/downward
    private void bubble(int i) {
        if (i > 0 && heap.get(i).compareTo(heap.get(parent(i))) < 0) {
            upheap(i);
        } else {
            downheap(i);
        }
    }

    private void downheap(int i) {
        while (hasLeft(i)) { // continue to bottom
            int leftIdx = left(i);
            int smallIdx = leftIdx;
            if (hasRight(i)) {
                int rightIdx = right(i);
                if (heap.get(leftIdx).compareTo(heap.get(rightIdx)) > 0) {
                    smallIdx = rightIdx; // right child is smaller
                }
            }
            if (heap.get(smallIdx).compareTo(heap.get(i)) >= 0)
                break; // com.vivek.heap property has been restored
            swap(i, smallIdx);
            i = smallIdx; // continue at position of the child
        }
    }

    public Entry insert(int value) {
        Entry elm = new Entry(value);
        elm.setIndex(heap.size());
        heap.add(elm); // add to the end of the list
        upheap(heap.size() - 1);
        return elm;
    }

    public Entry removeMin() {
        if (heap.isEmpty()) return null;
        Entry res = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return res;
    }

    public void remove(Entry elm) {
        int i = elm.getIndex();
        if (i == heap.size() - 1) { // entry is at last position
            heap.remove(i);
        } else {
            swap(i, heap.size() - 1); // swap entry to last position
            heap.remove(heap.size() -1);
            bubble(i);
        }
    }


    static class Entry implements Comparable<Entry> {
        private int value;
        private int index;

        public Entry(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(Entry o) {
            return this.value == o.value ? 0 : this.value > o.value ? 1 : -1;
        }
    }

}
