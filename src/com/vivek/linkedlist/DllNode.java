package com.vivek.linkedlist;

public class DllNode<T> {

    public T data;
    public DllNode<T> prev;
    public DllNode<T> next;

    public DllNode(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

}
