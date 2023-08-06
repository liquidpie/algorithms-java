package com.vivek.linkedlist.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LRUCacheTimestampBased<K, V> implements Cache<K, V> {

    private final Map<K, Entry<K, V>> map;
    private final TreeMap<Long, Entry<K, V>> tMap;
    private final int capacity;
    private long timestamp;

    public LRUCacheTimestampBased(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.tMap = new TreeMap<>();
        this.timestamp = 0L;
    }

    @Override
    public V get(K key) {
        if (map.containsKey(key)) {
            Entry<K, V> entry = map.get(key);
            tMap.remove(entry.timestamp);
            entry.timestamp = timestamp++;
            tMap.put(entry.timestamp, entry);
            return entry.value;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        if (!map.containsKey(key) && map.size() == capacity) {
            Map.Entry<Long, Entry<K, V>> entry = tMap.firstEntry();
            System.out.println("Evicted entry: " + entry.getValue());
            remove(entry.getValue().key);
        }
        add(new Entry<K, V>(key, value, timestamp++));
    }

    private void remove(K key) {
        if (map.containsKey(key)) {
            Entry<K, V> entry = map.get(key);
            tMap.remove(entry.timestamp);
        }
    }

    private void add(Entry<K, V> entry) {
        remove(entry.key);
        map.put(entry.key, entry);
        tMap.put(entry.timestamp, entry);
    }

    static class Entry<K, V> {
        K key;
        V value;
        long timestamp;

        public Entry(K key, V value, long timestamp) {
            this.key = key;
            this.value = value;
            this.timestamp = timestamp;
        }

        public String toString() {
            return "{" + key.toString() + ", " + value.toString() + "}";
        }
    }

    public static void main(String[] args) {
        Cache<Integer, Integer> map = new LRUCacheTimestampBased<>(3);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.get(1);
        map.get(2);
        map.put(4, 4);
    }
}
