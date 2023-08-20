package com.vivek.hashtable;

import java.util.*;

/**
 * class SSMap {
 *     void put(String key, String value);
 *     String get(String key, int version);
 *     String get(String key);
 *     Integer createSnapshot();
 *     List<Integer> getSnapshots();
 * }
 *
 *
 * version 0: 1:"1",  3:"3",  2:"2"
 *
 * snapshot -> 1
 *
 * version 1: 1:"1",  3:"3",  2:"2"
 *
 * put(2,22)
 *
 * version 1: 1:"1",  3:"3",  2:"22"
 *
 * snapshot -> 2
 *
 * version 2: 1:"1",  3:"3",  2:"22"
 * put (3,33)
 *
 * version 2: 1:"1",  3:"33",  2:"22"
 *
 * Optimize for space + run time complexity for createSnapshot and get
 */
public class SnapshottableMapV2 {

    Map<String, TreeMap<Integer, String>> map;
    private int currentVersion;

    SnapshottableMapV2() {
        this.map = new HashMap<>();
        this.currentVersion = 0;
    }

    public void put(String key, String value) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(currentVersion, value);
    }

    public String get(String key, int version) {
        if (map.containsKey(key)) {
            Map.Entry<Integer, String> entry = map.get(key).floorEntry(version);
            return entry != null ? entry.getValue() : null;
        }
        return null;
    }

    public String get(String key) {
        return get(key, currentVersion);
    }

    public Integer createSnapshot() {
        currentVersion++;
        return currentVersion;
    }

    public List<Integer> getSnapshots() {
        List<Integer> snapshotIds = new ArrayList<>();
        for (int i = 0; i < currentVersion; i++)
            snapshotIds.add(i);
        return snapshotIds;
    }

    public static void main(String[] args) {
        SnapshottableMapV2 sMap = new SnapshottableMapV2();
        sMap.put("1", "1");
        sMap.put("2", "2");
        sMap.put("3", "3");
        System.out.println(sMap.get("2"));
        sMap.createSnapshot();
        sMap.put("2", "22");
        System.out.println(sMap.get("2"));
        System.out.println(sMap.get("2", 0));
    }

}
