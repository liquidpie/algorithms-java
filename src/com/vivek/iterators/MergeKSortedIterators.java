package com.vivek.iterators;

import java.util.*;

/**
 * Given a list of k sorted iterators. Implement MergingIterator to merge them
 *
 * class MergingIterator implements Iterator<Integer> {
 * 	public MergingIterator(List<Iterator<Integer>> iterators) {
 * 	    }
 *
 * 	public boolean hasNext() {
 *    }
 *
 * 	public Integer next() {
 *    }
 * }
 *
 * Example:
 *
 * MergingIterator itr = new MergingIterator([[2, 5, 9], [], [4, 10]]);
 * itr.hasNext(); // true
 * itr.next(); // 2
 * itr.next(); // 4
 * itr.next(); // 5
 * itr.next(); // 9
 * itr.next(); // 10
 * itr.hasNext(); // false
 * itr.next(); // error
 *
 * Time Complexity:
 * constructor - O(K*logK) - where K - number of lists(iterators)
 * hasNext() - O(1) speed
 * next() - O(logK) - where K - number of lists(iterators)
 * Space Complexity: O(k) as at most k iterators are stored
 *
 * Reference:
 * https://leetcode.com/discuss/post/345744/google-onsite-merge-k-sorted-iterators-b-mmr7/
 */
public class MergeKSortedIterators {

    public static void main(String[] args) {
        List<Integer> list1 = List.of(2, 5, 9);
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = List.of(4, 10);
        MergingIterator itr = new MergingIterator(List.of(list1.iterator(), list2.iterator(), list3.iterator()));

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    static class MergingIterator implements Iterator<Integer> {

        private final Map<Iterator<Integer>, Integer> iterMap;
        private final PriorityQueue<Iterator<Integer>> heap;

        public MergingIterator(List<Iterator<Integer>> iterators) {
            this.iterMap = new HashMap<>();
            this.heap = new PriorityQueue<>(Comparator.comparing(this.iterMap::get));

            for (var iter : iterators) {
                if (iter.hasNext()) {
                    iterMap.put(iter, iter.next());
                    heap.add(iter);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !heap.isEmpty();
        }

        @Override
        public Integer next() {
            if (heap.isEmpty())
                throw new IllegalStateException();

            Iterator<Integer> iter = heap.poll();
            Integer val = iterMap.get(iter);
            if (iter.hasNext()) {
                iterMap.put(iter, iter.next());
                heap.add(iter);
            }
            return val;
        }
    }

}
