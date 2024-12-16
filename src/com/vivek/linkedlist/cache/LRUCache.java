package com.vivek.linkedlist.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement LRU cache using HashMap and Doubly Linked List
 *
 * As We need to keep track of Recently used entries, We will use a clever approach.
 * We will remove element from linkedlist and add element on start of LinkedList and
 * whenever any entry is accessed, it will be moved to top.
 * So that recently used entries will be on Top and Least used will be on Bottom.
 *
 * http://www.vivekjaiswal.me/2018/Building-LRU-Least-Recently-Used-Cache/
 *
 */
public class LRUCache<K, V> {

    private final Map<K, Entry> cache;
    private final DoublyLinkedList dll;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dll = new DoublyLinkedList();
    }

    public V get(K key) {
        if (!cache.containsKey(key))
            return null;

        Entry entry = cache.get(key);

        dll.removeNode(entry); // remove the recently accessed entry from DLL
        dll.addNodeToHead(entry); // and move to top
        return entry.value;
    }

    public void put(K key, V value) {
        if (capacity == 0) {
            return; // Do nothing if the cache capacity is zero
        }

        if (cache.containsKey(key)) {
            Entry entry = cache.get(key);
            entry.value = value; // Update value
            dll.removeNode(entry);
            dll.addNodeToHead(entry); // Move updated node to the head
        } else {
            if (cache.size() >= capacity) {
                Entry tailNode = dll.removeTail(); // Evict the least recently used node
                cache.remove(tailNode.key);
            }

            Entry newEntry = new Entry(key, value);
            cache.put(key, newEntry);
            dll.addNodeToHead(newEntry);
        }
    }

    public void resize(int newCapacity) {
        if (newCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }

        while (cache.size() > newCapacity) {
            Entry tailNode = dll.removeTail();
            cache.remove(tailNode.key);
        }

        this.capacity = newCapacity;
    }

    private class Entry {
        K key;
        V value;
        Entry prev, next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class DoublyLinkedList {
        Entry head, tail;

        DoublyLinkedList() {
            head = new Entry(null, null);
            tail = new Entry(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addNodeToHead(Entry entry) {
            Entry next = head.next;
            head.next = entry;
            entry.prev = head;
            entry.next = next;
            next.prev = entry;
        }

        void removeNode(Entry entry) {
            Entry prev = entry.prev;
            Entry next = entry.next;
            prev.next = next;
            next.prev = prev;
        }

        Entry removeTail() {
            if (tail.prev != head) {
                Entry entry = tail.prev;
                removeNode(entry);
                return entry;
            }
            return null;
        }
    }

    // Debugging method to display the current state of the cache
    public void displayCache() {
        System.out.println("Cache Contents:");
        Entry current = dll.head.next;
        while (current != dll.tail) {
            System.out.println(current.key + " -> " + current.value);
            current = current.next;
        }
    }


    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);

        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");

        System.out.println(lruCache.get(1)); // Access key 1 (move to head)
        lruCache.put(4, "D"); // Evicts key 2 (least recently used)

        System.out.println(lruCache.get(2)); // null (evicted)
        System.out.println(lruCache.get(3)); // C
        System.out.println(lruCache.get(4)); // D

        lruCache.resize(2); // Resize cache to capacity 2
        lruCache.put(5, "E"); // Evicts least recently used key

        lruCache.displayCache();
    }

}
