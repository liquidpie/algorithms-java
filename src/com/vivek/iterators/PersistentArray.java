package com.vivek.iterators;

import java.util.*;

/**
 * Design a persistent iterator over an array of elements
 *
 * A persistent iterator refers to an iterator that can traverse the elements of an array even though some of them can be deleted or inserted after the creation of the iterator.
 * Take as example the following sequence of operations:
 *
 * array: 4, 5, 2
 * create iterator1
 * delete 2
 * create iterator2
 * delete 4
 * add 7
 * create iterator3
 * iterator1.hasNext() returns true and iterator1.next() returns 4
 * iterator1.hasNext() returns true and iterator1.next() returns 5
 * iterator1.hasNext() returns true and iterator1.next() returns 2
 * iterator1.hasNext() returns false
 * iterator2.hasNext() returns true and iterator2.next() returns 4
 * iterator2.hasNext() returns true and iterator2.next() returns 5
 * iterator2.hasNext() returns false
 * iterator3.hasNext() returns true and iterator3.next() returns 5
 * iterator3.hasNext() returns true and iterator3.next() returns 7
 * iterator3.hasNext() returns false
 *
 * If an element has been deleted and there isn't any existing iterator that references the element, the memory allocated for the element will be freed.
 *
 * Solution:
 * Keep an internal timestamp for the PersistentArray that increments at every add() and delete() operation.
 * Every element's timestamp will be the current PersistentArray timestamp at the element's addition.
 * Also keep a reference count for every element that is shared with the iterators that are created.
 * Once a PersistentIterator is created, it will keep internally the current timestamp of the PersistentArray.
 * The PersistentIterator will only look for elements that have a timestamp less or equal to the iterator's timestamp
 * and aren't marked as deleted, let's call them the visible elements for an iterator.
 * Now, the iterator's constructor will increment the reference count for every visible element.
 * Its destructor will decrement the reference count for every visible element.
 * Once the reference count of an element reaches zero, its memory will be freed.
 *
 * https://leetcode.com/discuss/interview-question/object-oriented-design/125201/Design-a-persistent-iterator-over-an-array-of-elements
 */
public class PersistentArray implements Iterable<Integer> {

    private final List<Element> data;
    private final Map<Integer, Element> lookup;
    private int currentGeneration;

    public PersistentArray(List<Integer> init) {
        this.currentGeneration = 0;
        this.data = new ArrayList<>();
        init.forEach(x -> this.data.add(new Element(x)));
        this.lookup = new HashMap<>();
        for (var elem : this.data) {
            lookup.put(elem.val, elem);
        }
    }

    public void add(int num) {
        var element = new Element(num);
        data.add(element);
        lookup.put(num, element);
    }

    public void delete(int num) {
        var element = lookup.get(num);
        element.deletedAt = currentGeneration;
    }

    private void removeDeletedElements(int generation) {
        data.removeIf(element -> element.deletedAt <= generation);
    }

    @Override
    public Iterator<Integer> iterator() {
        this.currentGeneration++;
        return new PersistentArrayIterator();
    }

    private class PersistentArrayIterator implements Iterator<Integer> {

        private final int generation;
        private int nextIndex;

        PersistentArrayIterator() {
            this.generation = currentGeneration;
            this.nextIndex = 0;
        }

        @Override
        public boolean hasNext() {
            while (nextIndex < data.size() &&
                    (data.get(nextIndex).generation >= this.generation ||
                    data.get(nextIndex).deletedAt < this.generation)) {
                nextIndex++;
            }
            var hasNext = nextIndex != data.size();
            if (!hasNext)
                removeDeletedElements(this.generation);
            return hasNext;
        }

        @Override
        public Integer next() {
            var out = data.get(nextIndex).val;
            nextIndex++;
            return out;
        }
    }

    private class Element {
        int val;
        int deletedAt;
        int generation;

        Element(int val) {
            this.val = val;
            this.generation = currentGeneration;
            this.deletedAt = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        PersistentArray arr = new PersistentArray(List.of(4, 5, 2));
        Iterator<Integer> iter1 = arr.iterator();
        arr.delete(2);
        Iterator<Integer> iter2 = arr.iterator();
        arr.delete(4);
        arr.add(7);
        Iterator<Integer> iter3 = arr.iterator();

        System.out.println("Iterator 1 running");
        while (iter1.hasNext())
            System.out.println(iter1.next());

        System.out.println("Iterator 2 running");
        while (iter2.hasNext())
            System.out.println(iter2.next());

        System.out.println("Iterator 3 running");
        while (iter3.hasNext())
            System.out.println(iter3.next());
    }
}
