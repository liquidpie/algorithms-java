package com.vivek.array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 Two pairs (a, b) and (c, d) are said to be symmetric if c is equal to b and a is equal to d.
 For example (10, 20) and (20, 10) are symmetric.
 Given an array of pairs find all symmetric pairs in it.

 It may be assumed that first elements of all pairs are distinct.

 Example:

 Input: arr[] = {{11, 20}, {30, 40}, {5, 10}, {40, 30}, {10, 5}, {5, 6}, {11, 12}, {5, 10}, {10, 5}, {10, 5}}
 Output: Following pairs have symmetric pairs
 (30, 40)
 (5, 10)
 */
public class FindSymmetricPairs {

    static Map<Pair, Integer> findSymmetricPairs(List<Pair> pairs) {
        Map<Pair, Integer> freqMap = new HashMap<>();
        Map<Pair, Integer> symmetricMap = new HashMap<>();

        for (Pair pair : pairs) {
            Pair symmetricPair = new Pair(pair.y, pair.x);
            if (!freqMap.containsKey(symmetricPair)) {
                putOrIncrement(pair, freqMap);
            } else {
                if (freqMap.get(symmetricPair) > 0) {
                    freqMap.put(symmetricPair, freqMap.get(symmetricPair) - 1);
                    Pair pairSortedByX = pair.x < symmetricPair.x ? pair : symmetricPair;
                    putOrIncrement(pairSortedByX, symmetricMap);
                }
            }
        }
        return symmetricMap;
    }

    static void putOrIncrement(Pair pair, Map<Pair, Integer> map) {
        if (map.containsKey(pair)) {
            map.put(pair, map.get(pair) + 1);
        } else {
            map.put(pair, 1);
        }
    }

    private static class Pair {
        int x;
        int y;

        Pair(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        static Pair of(int x, int y) {
            return new Pair(x, y);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", "[", "]")
                    .add("" + x)
                    .add("" + y)
                    .toString();
        }
    }

    public static void main(String[] args) {
        List<Pair> pairs = List.of(Pair.of(30, 40),
                                    Pair.of(5, 10),
                                    Pair.of(40, 30),
                                    Pair.of(10, 5),
                                    Pair.of(5, 6),
                                    Pair.of(11, 12),
                                    Pair.of(5, 10),
                                    Pair.of(10, 5),
                                    Pair.of(10, 5));

        System.out.println(findSymmetricPairs(pairs));
    }

}
