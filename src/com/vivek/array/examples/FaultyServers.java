package com.vivek.array.examples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Count Faulty Servers (Hackerrank)
 *
 * There are n servers with ids s1, s2 ... sn, and an array of logs of size m. Log format is "<server_id> <success/error>".
 * If a particular server id logs an error for three consecutive requests, it is considered faulty and is replaced
 * with a new server with the same id.
 *
 * Example:
 * n = 2
 * logs = ["s1 error", "s1 error", "s2 error", "s1 error", "s1 error", "s2 success"]
 *
 * Output: 1
 * s1 was replaced one time.
 *
 * https://leetcode.com/discuss/interview-question/5465365/Salesforce-%3A-General-Software-Engineer-Assessment-(INDIA-ONLY)/
 */
public class FaultyServers {

    public static void main(String[] args) {
        int n = 2;
        List<String> logs = List.of("s1 error", "s1 error", "s2 error", "s1 error", "s1 error", "s2 success");
        System.out.println(countFaultyServers(n, logs));  // Output: 1
    }

    static int countFaultyServers(int n, List<String> logs) {
        int faultyCount = 0;
        Map<String, Integer> tracker = new HashMap<>();

        for (String log : logs) {
            String[] parts = log.split(" ");
            String serverId = parts[0];
            if (parts[1].equals("error")) {
                tracker.put(serverId, tracker.getOrDefault(serverId, 0) + 1);
                if (tracker.get(serverId) >= 3) {
                    faultyCount++;
                    tracker.put(serverId, 0);
                }
            } else {
                tracker.put(serverId, 0);
            }
        }
        return faultyCount;
    }

}
