package com.vivek.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class SnapshottableMap {

    private final Map<Integer, Snapshot> snapshots;
    private Snapshot currentSnapshot;
    private int currentVersion;

    public SnapshottableMap() {
        this.snapshots = new HashMap<>();
        this.currentSnapshot = new Snapshot();
        this.currentVersion = 0;
    }

    public void put(String key, String value) {
        currentSnapshot.put(key, value);
    }

    public String get(String key, int version) {
        if (version < 0)
            throw new IllegalArgumentException("Version can't be negative");
        if (version > currentVersion)
            throw new IllegalArgumentException("No such version found");

        if (version == currentVersion)
            return get(key);
        else {
            Snapshot temp = snapshots.get(version);
            return temp.get(key);
        }
    }

    public String get(String key) {
        return currentSnapshot.get(key);
    }

    public Integer createSnapshot() {
        final Snapshot out = new Snapshot();
        for (Map.Entry<String, String> entry : currentSnapshot.getData().entrySet()) {
            out.put(entry.getKey(), entry.getValue());
        }
        snapshots.put(currentVersion, out);
        currentVersion++;
        return currentVersion;
    }

    public List<Integer> getSnapshots() {
        List<Integer> snapshotIds = new ArrayList<>(snapshots.keySet());
        return snapshotIds;
    }

    static class Snapshot {
        private final Map<String, String> data;

        public Snapshot() {
            this.data = new HashMap<>();
        }

        public String get(String key) {
            return data.get(key);
        }

        public void put(String key, String value) {
            data.put(key, value);
        }

        public Map<String, String> getData() {
            return data;
        }

    }

    public static void main(String[] args) {
        SnapshottableMap sMap = new SnapshottableMap();
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
