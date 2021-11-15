package com.vivek.linkedlist;

public class DoublyLinkedList<T> {

    DllNode<T> head, tail;
    int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public T getFirst() {
        return head != null ? head.data : null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public DllNode<T> append(T data) {
        DllNode<T> node = new DllNode<>(data);
        if (head == null) {
            head = node;
            tail = node;
            return head;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
        return node;
    }

    public T remove(DllNode<T> node) {
        if (node == null)
            return null;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        return node.data;
    }

}
