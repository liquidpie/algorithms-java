package com.vivek.linkedlist;

import java.util.Objects;

public class SinglyLinkedList {

    private Node head;

    public SinglyLinkedList(Node head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node getHead() {
        return head;
    }

    public String first() {
        if (isEmpty())
            return null;

        return head.getData();
    }

    static class Node {
        private final String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(String data) {
            this(data, null);
        }

        public String getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

}
