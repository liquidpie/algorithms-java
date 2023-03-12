package com.vivek.heap;

import java.util.*;
import java.util.PriorityQueue;

public class TopKHeavyHitters {

    public static void main(String[] args) {
        String[] events = {"A", "B", "A", "A", "C", "C", "D", "D", "D", "A"};
        System.out.println(topK(events, 3));
    }

    static List<HeavyHitter> topK(String[] events, int k) {
        Map<String, Integer> frequencyTable = new HashMap<>();
        for (String event : events) {
            frequencyTable.put(event, frequencyTable.getOrDefault(event, 0) + 1);
        }

        PriorityQueue<HeavyHitter> heap = new PriorityQueue<>(Comparator.comparing(HeavyHitter::getFrequency));

        for (Map.Entry<String, Integer> entry : frequencyTable.entrySet()) {
            heap.offer(new HeavyHitter(entry.getKey(), entry.getValue()));

            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<HeavyHitter> result = new ArrayList<>();
        while (heap.size() > 0) {
            result.add(heap.poll());
        }

        return result;
    }

    static class HeavyHitter {
        private final String identifier;
        private final int frequency;

        public HeavyHitter(String identifier, int frequency) {
            this.identifier = identifier;
            this.frequency = frequency;
        }

        public String getIdentifier() {
            return identifier;
        }

        public int getFrequency() {
            return frequency;
        }

        @Override
        public String toString() {
            return "{" + identifier + ", " + frequency + '}';
        }
    }

}
