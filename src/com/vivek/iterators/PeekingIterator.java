package com.vivek.iterators;

import java.util.Iterator;
import java.util.List;

/**
 * 284. Peeking Iterator
 *
 * Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
 *
 * Implement the PeekingIterator class:
 *
 *  - PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
 *  - int next() Returns the next element in the array and moves the pointer to the next element.
 *  - boolean hasNext() Returns true if there are still elements in the array.
 *  - int peek() Returns the next element in the array without moving the pointer.
 *
 * Note: Each language may have a different implementation of the constructor and Iterator,
 * but they all support the int next() and boolean hasNext() functions.
 *
 * Example 1:
 *
 * Input
 * ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 2, 2, 3, false]
 *
 * Explanation
 * PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
 * peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
 * peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
 * peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
 * peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
 * peekingIterator.hasNext(); // return False
 *
 * Reference:
 * https://leetcode.com/problems/peeking-iterator/description/
 */
public class PeekingIterator implements Iterator<Integer> {

    private final Iterator<Integer> iterator;
    private Integer last; // add a reference to the next element

	public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if (iterator.hasNext())
            last = iterator.next();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return last;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer val = last;
        last = iterator.hasNext() ? iterator.next() : null;
        return val;
    }

    @Override
    public boolean hasNext() {
        return last != null;
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        PeekingIterator peekingIterator = new PeekingIterator(list.iterator()); // [1,2,3]
        while (peekingIterator.hasNext()) {
            System.out.println(peekingIterator.peek());
            System.out.println(peekingIterator.next());
        }
    }

}
