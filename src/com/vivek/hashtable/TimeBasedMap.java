package com.vivek.hashtable;

import com.vivek.hashtable.TimeBasedMap.TimeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Time Based Key-Value Store
 *
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps
 * and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 *
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 *
 *
 * Example 1:
 *
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 *
 * Explanation
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 *
 * https://leetcode.com/problems/time-based-key-value-store
 */
public class TimeBasedMap {

    public static void main(String[] args) {
        TimeMap map = new TimeMap();
        map.set("k1", "v1", 1);
        map.set("k1", "v2", 2);
        map.set("k1", "v5", 5);
        System.out.println(map.get("k1", 4));
        System.out.println(map.get("k1", 6));
    }

    static class TimeMap {

        private final Map<String, List<TimeValue>> map;
        public TimeMap() {
            map = new HashMap<>();
        }

        static class TimeValue {
            String value;
            int timestamp;

            TimeValue(String value, int timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }

        void set(String key, String value, int timestamp) {
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(new TimeValue(value, timestamp));
        }

        String get(String key, int timestamp) {
            List<TimeValue> value = map.get(key);
            if (value == null)
                return "";

            int low = 0;
            int high = value.size() - 1;

            while (low < high) {
                int mid = (low + high) / 2;
                if ((value.get(mid).timestamp == timestamp) ||
                        (value.get(mid).timestamp < timestamp && value.get(mid + 1).timestamp > timestamp)) {
                    return value.get(mid).value;
                }
                else if (value.get(mid).timestamp > timestamp) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return value.get(low).timestamp > timestamp ? "" : value.get(low).value;
        }

    }

}
