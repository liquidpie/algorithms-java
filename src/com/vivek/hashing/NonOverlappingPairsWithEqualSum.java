package com.vivek.hashing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find two non-overlapping pairs having equal sum in an Array
 *
 * Input : {1,4,5,0,1,2}
 *
 * Output : {1,4}, {5,0}
 * Output : {1,5}, {4,2}
 * Output : {1,1}, {2,0}
 */
public class NonOverlappingPairsWithEqualSum {

    /**
     * Here we're traversing all the pairs and marking the visited numbers as -1 so that they are not considered again.
     * Another way to solve this is to keep index of the element in the Pair instead of number itself, so that we know they are unique
     */
    private static List<Pair> findOverlappingPairsByMarkingElements(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        Map<Integer, Pair> map = new HashMap<>();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] != -1 && arr[j] != -1) {
                    int sum = arr[i] + arr[j];
                    Pair pair = new Pair(arr[i], arr[j]);
                    Pair existing = map.get(sum);
                    if (existing == null) {
                        arr[i] = -1;
                        arr[j] = -1;
                        map.put(sum, pair);
                    } else {
                        return List.of(pair, existing);
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,5,0,1,2};
        System.out.println(findOverlappingPairsByMarkingElements(arr));
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" + x +
                    "," + y +
                    '}';
        }
    }

}
