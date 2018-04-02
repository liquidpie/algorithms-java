package com.vivek.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    private final int CACHE_SIZE;
    private final Map<K, Entry<K, V>> CACHE;

    private Entry<K, V> head, tail;

    public LRUCache() {
        this(16);       // default cache size as 16
    }

    public LRUCache(int CACHE_SIZE) {
        this.CACHE_SIZE = CACHE_SIZE;
        CACHE = new HashMap<>(CACHE_SIZE);
    }

    public V get(K key) {
        if (CACHE.containsKey(key)) {
            Entry<K, V> entry = CACHE.get(key);
            // remove the recently accessed entry from linkedlist
            remove(entry);
            // and move to top
            splayOnTop(entry);
            return entry.value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (CACHE.containsKey(key)) {
            Entry<K, V> entry = CACHE.get(key);
            entry.value = value;
            remove(entry);
            splayOnTop(entry);
        } else {
            Entry<K, V> entry = new Entry<>();
            entry.key = key;
            entry.value = value;
            // reached the cache size, evict the least recently used entry
            if (CACHE.size() == CACHE_SIZE) {
                CACHE.remove(tail.key);
                remove(tail);
            }
            // move the recently accessed entry to top
            splayOnTop(entry);
            CACHE.put(key, entry);
        }
    }

    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(CACHE.values());
    }

    public Set<K> keySet() {
        return CACHE.keySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        String delimiter = "";
        for (Entry<K, V> entry : entrySet()) {
            sb.append(delimiter).append(entry.key).append("=").append(entry.value);
            delimiter = ", ";
        }
        sb.append("}");
        return sb.toString();
    }

    private void splayOnTop(Entry<K, V> entry) {
        entry.next = head;
        if (head != null)           // when linkedlist not empty
            head.prev = entry;
        head = entry;
        if (tail == null)           // when first entry
            tail = head;
    }

    private void remove(Entry<K, V> entry) {
        if (entry.prev != null) {
            entry.prev.next = entry.next;
        } else {
            head = entry.next;
        }
        if (entry.next != null) {
            entry.next.prev = entry.prev;
        } else {
            tail = entry.prev;
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry prev;
        Entry next;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> lruCache = new LRUCache<>(4);
        lruCache.put(1, 11);
        lruCache.put(2, 12);
        lruCache.put(3, 13);
        lruCache.get(2);
        lruCache.put(4, 14);
        lruCache.get(3);

        System.out.println("Cache before eviction");
        System.out.println(lruCache);

        lruCache.put(5, 15);

        System.out.println("Cache after eviction");
        System.out.println(lruCache);
    }

}
